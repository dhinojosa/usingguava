/**
 * Copyright 2010-2013 Daniel Hinojosa
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.abqjug;

import org.testng.annotations.Test;

import java.lang.ref.*;

public class ReferencesTest {
    public class RunWeakReference extends Thread {

        private ReferenceQueue<Integer> queue;

        public RunWeakReference(ReferenceQueue<Integer> queue) {
            this.queue = queue;
        }

        public void run() {
            WeakReference<Integer> weakReference = new WeakReference<Integer>(4, queue);
            System.out.println("Hooray! I used weak reference" + weakReference.get());
            weakReference = null;
        }
    }

    public class QueueRunner extends Thread {
        private ReferenceQueue<Integer> queue;

        public QueueRunner(ReferenceQueue<Integer> queue) {
            this.queue = queue;
        }

        private boolean done;

        public void run() {
            while (!done) {
                Reference<? extends Integer> candidate = queue.poll();
                if (candidate != null) {
                    System.out.println(candidate.get() + "has been garbage collected");
                }
            }
        }

        public void turnOff() {
            done = true;
        }

    }


    @Test(groups = "references")
    public void testPhantomReference() throws InterruptedException {
        ReferenceQueue<Integer> queue = new ReferenceQueue<Integer>();
        QueueRunner runner = new QueueRunner(queue);
        runner.start();

        PhantomReference<Integer> integerPhantomReference = new PhantomReference<Integer>(2, queue);
        integerPhantomReference = null;
        Thread.yield();
        Thread.sleep(10000);
        runner.turnOff();
    }

    @Test(groups = "references")
    public void testWeakReference() throws InterruptedException {
        Integer f = 10;
        WeakReference<Integer> weakReference = new WeakReference<>(f);
        System.out.println(weakReference.get());
        f = null;
        System.gc();
        Thread.sleep(10000);
        System.out.println(weakReference.get());
    }

    @Test(groups = "references")
    public void testSoftReference() throws InterruptedException {
        SoftReference<Object> softReference = new SoftReference<>(new Object());
        System.out.println(softReference.get());
        System.gc();
        Thread.sleep(10000);
        System.out.println(softReference.get());
    }
}
