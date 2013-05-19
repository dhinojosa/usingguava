/**
 * Copyright 2010-2013 Daniel Hinojosa
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.abqjug;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.fest.assertions.Assertions.assertThat;

/**
 * User: Daniel Hinojosa (dhinojosa@evolutionnext.com)
 * Date: 11/12/12
 * Time: 7:49 AM
 */
public class EventBusTest {

    public class BroadcastEvent {
        private String message;

        public BroadcastEvent(String message) {
            this.message = message;
        }

        @Override
        public boolean equals(Object o) {
            return o instanceof BroadcastEvent && Objects.equals(message, ((BroadcastEvent) o).message);
        }

        @Override
        public int hashCode() {
            return message.hashCode();
        }

        @Override
        public String toString() {
            return "BroadcastEvent{" +
                    "message='" + message + '\'' +
                    '}';
        }

        public String getMessage() {
            return message;
        }
    }

    public class Broadcaster {
        private EventBus eventBus;

        public void setEventBus(EventBus eventBus) {
            this.eventBus = eventBus;
        }

        public void broadcastToAll() {
            this.eventBus.post(new BroadcastEvent("The Guava Revolution will not be televised"));
        }
    }

    public class Subscriber {
        private List<String> messages;

        public Subscriber() {
            this.messages = Lists.newArrayList();
        }

        @Subscribe
        public void eventOccured(BroadcastEvent event) {
            messages.add(event.getMessage());
        }

        public int getCount() {
            return messages.size();
        }

        public List<String> getMessages() {
            return ImmutableList.copyOf(messages);
        }
    }

    public class KlaxonEvent {
    }

    public class UninterestedSubscriber {
        private boolean klaxonReceived;

        @Subscribe
        public void eventOccured(KlaxonEvent event) {
            klaxonReceived = true;
        }

        public boolean isKlaxonReceived() {
            return klaxonReceived;
        }
    }

    @Test
    public void testBasicUse() {
        EventBus eventBus = new EventBus();
        Subscriber subscriber = new Subscriber();
        eventBus.register(subscriber);

        Broadcaster broadcaster = new Broadcaster();
        broadcaster.setEventBus(eventBus);

        broadcaster.broadcastToAll();
        broadcaster.broadcastToAll();
        broadcaster.broadcastToAll();

        assertThat(subscriber.getCount()).isEqualTo(3);
        assertThat(subscriber.getMessages().get(0)).isEqualTo("The Guava Revolution will not be televised");
        assertThat(subscriber.getMessages().get(1)).isEqualTo("The Guava Revolution will not be televised");
        assertThat(subscriber.getMessages().get(2)).isEqualTo("The Guava Revolution will not be televised");
    }

    @Test
    public void testUnregistering() {
        EventBus eventBus = new EventBus();
        Subscriber subscriber = new Subscriber();
        eventBus.register(subscriber);

        Broadcaster broadcaster = new Broadcaster();
        broadcaster.setEventBus(eventBus);

        broadcaster.broadcastToAll();
        broadcaster.broadcastToAll();
        broadcaster.broadcastToAll();

        assertThat(subscriber.getCount()).isEqualTo(3);
        assertThat(subscriber.getMessages().get(0)).isEqualTo("The Guava Revolution will not be televised");
        assertThat(subscriber.getMessages().get(1)).isEqualTo("The Guava Revolution will not be televised");
        assertThat(subscriber.getMessages().get(2)).isEqualTo("The Guava Revolution will not be televised");

        eventBus.unregister(subscriber);

        broadcaster.broadcastToAll();
        broadcaster.broadcastToAll();
        broadcaster.broadcastToAll();

        assertThat(subscriber.getCount()).isEqualTo(3);
    }

    @Test
    public void testUninterestedSubscriber() {
        EventBus eventBus = new EventBus();
        Subscriber subscriber = new Subscriber();
        eventBus.register(subscriber);

        UninterestedSubscriber uninterestedSubscriber = new UninterestedSubscriber();
        eventBus.register(uninterestedSubscriber);

        Broadcaster broadcaster = new Broadcaster();
        broadcaster.setEventBus(eventBus);

        broadcaster.broadcastToAll();
        broadcaster.broadcastToAll();
        broadcaster.broadcastToAll();

        assertThat(subscriber.getCount()).isEqualTo(3);
        assertThat(subscriber.getMessages().get(0)).isEqualTo("The Guava Revolution will not be televised");
        assertThat(subscriber.getMessages().get(1)).isEqualTo("The Guava Revolution will not be televised");
        assertThat(subscriber.getMessages().get(2)).isEqualTo("The Guava Revolution will not be televised");
        assertThat(uninterestedSubscriber.isKlaxonReceived()).isFalse();
    }


    public class ParameterizedSubscriber {

        private List<List<String>> receivedEvents;

        public ParameterizedSubscriber() {
            this.receivedEvents = new ArrayList<>();
        }

        @Subscribe
        public void eventOccured(List<String> groceries) {
            this.receivedEvents.add(groceries);
        }

        public List<List<String>> getReceivedEvents() {
            return receivedEvents;
        }
    }

    @Test
    public void testErasureIssue() {
        EventBus eventBus = new EventBus();

        ParameterizedSubscriber parameterizedSubscriber = new ParameterizedSubscriber();
        eventBus.register(parameterizedSubscriber);

        eventBus.post(Lists.newArrayList("Eggs", "Milk", "Tequila"));
        eventBus.post(Lists.newArrayList(1, 2, 3, 4));

        assertThat(parameterizedSubscriber.getReceivedEvents().size()).isEqualTo(2);
        assertThat(parameterizedSubscriber.getReceivedEvents().get(0)).isEqualTo(Lists.newArrayList("Eggs", "Milk", "Tequila"));
        assertThat(parameterizedSubscriber.getReceivedEvents().get(1)).isEqualTo(Lists.newArrayList(1, 2, 3, 4));
    }
}
