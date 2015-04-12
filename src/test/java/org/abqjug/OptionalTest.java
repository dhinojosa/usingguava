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

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import org.testng.annotations.Test;

import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;

public class OptionalTest {


    public Optional<String> getMiddleName(String fullName) {
        String[] parts = fullName.split(" ");
        if (parts.length <= 2) return Optional.absent();
        return Optional.of(parts[1]);
    }

    public Optional<String> getValueFromInternalMap(String key) {
        Map<String, String> maps = ImmutableMap.of("One", "1",
                 "Two", "2", "Three", "3");
        return Optional.fromNullable(maps.get(key));
    }

    @Test
    public void testOptional() {
        assertThat(getMiddleName("George Washington")).isEqualTo(Optional.absent());
        assertThat(getMiddleName("John Fitzgerald Kennedy")).isEqualTo(Optional.of("Fitzgerald"));
    }

    @Test
    public void testOptionalFromNullable() {
        assertThat(getValueFromInternalMap("Nine")).isEqualTo(Optional.absent());
        try {
            getValueFromInternalMap("Nine").get();
        } catch (IllegalStateException e) {
            assertThat(e).hasMessage("Optional.get() cannot be called on an absent value");
        }
        assertThat(getValueFromInternalMap("One").isPresent()).isTrue();
    }

    @Test
    public void testChainOptional() {
        int called =0;
        System.out.println(Optional.absent().or(Optional.of(++called)).or(Optional.absent()).or(Optional.of(++called)).or(Optional.absent()));
        System.out.println(called);
    }

    @Test
    public void testChainOptionalWithSupplier() {
        int called =0;
        System.out.println(Optional.absent().or(Optional.of(++called)).or(Optional.absent()).or(Optional.of(++called)).or(Optional.absent()));
        System.out.println(called);
    }

    @Test
    public void testWithJava8Optional() {
        int called = 0;
        System.out.println(java.util.Optional.empty().flatMap(a ->
                java.util.Optional.of(3).map(b ->
                        java.util.Optional.of(5).map(c ->
                                        java.util.Optional.empty()
                        ))));
        System.out.println(called);
    }
}
