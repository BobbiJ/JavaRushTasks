package com.javarush.task.task28.task2807;

import java.util.concurrent.*;

/* 
Знакомство с ThreadPoolExecutor
*/
public class Solution {
    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue <Runnable> queue = new LinkedBlockingQueue<>();
        for (int i = 1; i < 11; i++) {
            final Integer i1 = i;
            queue.add(new Runnable() {
                @Override
                public void run() {
                    doExpensiveOperation(i1);
                }
            });
        }


        ThreadPoolExecutor executor = new ThreadPoolExecutor(3,5,1000L,TimeUnit.MILLISECONDS,queue);
        executor.prestartAllCoreThreads();
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
        /* output example
pool-1-thread-2, localId=2
pool-1-thread-3, localId=3
pool-1-thread-1, localId=1
pool-1-thread-3, localId=5
pool-1-thread-2, localId=4
pool-1-thread-3, localId=7
pool-1-thread-1, localId=6
pool-1-thread-3, localId=9
pool-1-thread-2, localId=8
pool-1-thread-1, localId=10
         */
    }

    private static void doExpensiveOperation(int localId) {
        System.out.println(Thread.currentThread().getName() + ", localId=" + localId);

    }
}
