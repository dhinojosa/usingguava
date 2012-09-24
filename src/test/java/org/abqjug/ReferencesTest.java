package org.abqjug;

import org.testng.annotations.Test;

import java.lang.ref.*;
import java.util.WeakHashMap;

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
        WeakReference<Integer> weakReference = new WeakReference<Integer>(f);
        System.out.println(weakReference.get());
        f = null;
        System.gc();
        System.out.println(weakReference.get());
    }

     @Test(groups = "references")
    public void testSoftReference() throws InterruptedException {
        Integer f = 10;
        SoftReference<Integer> softReference = new SoftReference<Integer>(f);
        System.out.println(softReference.get());
        f = null;
        System.gc();
        System.out.println(softReference.get());
    }
}
