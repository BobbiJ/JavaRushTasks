package com.javarush.task.task25.task2508;

public class TaskManipulator implements CustomThreadManipulator, Runnable {
    Thread thread;
    @Override
    public void run() {
        while (true) {
            try {
                System.out.println(Thread.currentThread().getName());
                thread.sleep(100);
            } catch (InterruptedException e) {
               return;
            }
        }

    }

    @Override
    public void start(String threadName) {
        thread = new Thread(new TaskManipulator());
        thread.setName(threadName);
        thread.start();

    }

    @Override
    public void stop() {
        thread.interrupt();
    }
}
