package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread {
    Thread thread;
    LoggingStateThread (Thread thread){
        this.thread = thread;

    }

    @Override
    public void run() {
        State state = null;

        while (true) {
            if (state != thread.getState()){
                state = thread.getState();
                System.out.println(state);
            }
            if (thread.getState() == State.TERMINATED) return;
        }
    }
}
