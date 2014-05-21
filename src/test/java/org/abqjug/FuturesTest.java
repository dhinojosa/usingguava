package org.abqjug;

import com.google.common.util.concurrent.*;
import org.testng.annotations.Test;

import java.util.concurrent.*;

/**
 * @author Daniel Hinojosa
 * @since 5/20/14 2:00 PM
 * url: <a href="http://www.evolutionnext.com">http://www.evolutionnext.com</a>
 * email: <a href="mailto:dhinojosa@evolutionnext.com">dhinojosa@evolutionnext.com</a>
 * tel: 505.363.5832
 */
public class FuturesTest {

    @Test(groups = {"unit", "async", "1"})
    public void testJavaUtilConcurrentFuturesBlocking() throws ExecutionException, InterruptedException {

        ExecutorService executorService =
                Executors.newCachedThreadPool();

        Callable<String> asynchronousTask
                = new Callable<String>() {
            @Override
            public String call() throws Exception {
                //something expensive
                Thread.sleep(1000);
                return "Asynchronous String Result";
            }
        };

        java.util.concurrent.Future<String> future =
                executorService.submit(asynchronousTask);
        System.out.println("Processing 1");
        System.out.println(future.get()); //waits if necessary
        System.out.println("Processing 2");
    }

    @Test(groups = {"unit", "async", "2"})
    public void testJavaUtilConcurrentFuturesAsynchronously() throws Exception {
        ExecutorService executorService =
                Executors.newFixedThreadPool(10);

        Callable<String> asynchronousTask = new Callable<String>() {
            @Override
            public String call() throws Exception {
                //something expensive
                Thread.sleep(1000);
                return "Asynchronous String Result";
            }
        };

        java.util.concurrent.Future<String> future =
                executorService.submit(asynchronousTask);

        System.out.println("Processing Asynchronously 1");
        while (!future.isDone()) {
            System.out.println(future.isDone());
            System.out.println("Doing something else");
        }
        System.out.println(future.get()); //waits if necessary
        System.out.println("Processing Asynchronously 2");
    }

    @Test(groups = {"unit","async", "3"})
    public void testGuavaConcurrent() throws Exception {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(
                Executors.newCachedThreadPool());

        ListenableFuture<String> listenableFuture = service.submit(() -> {
            //something expensive
            Thread.sleep(1000);
            return "Asynchronous String Result";
        });

        Futures.addCallback(listenableFuture,
                new FutureCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println(
                                "Got the result and the answer is? " + result);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        System.out.println("Things happened man. Bad things" + t.getMessage());
                    }
                }
        );

        Thread.sleep(5000);
    }
}
