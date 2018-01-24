package com.javarush.task.task28.task2805;

public class MyThread extends Thread {
    static private int priority = 1;
    private int priorityThread = priority % 10 ==0 ? 10 : priority % 10;


    public MyThread() {
        priority++;
        setPriority(priorityThread);

    }

    public MyThread(Runnable target) {
        super(target);
        priority++;
        setPriority(priorityThread);
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        priority++;
        if (priorityThread > group.getMaxPriority()) setPriority(group.getMaxPriority());
        else setPriority(priorityThread);
    }

    public MyThread(String name) {
        super(name);
        priority++;
        setPriority(priorityThread);
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        priority++;
        if (priorityThread > group.getMaxPriority()) setPriority(group.getMaxPriority());
        else setPriority(priorityThread);
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        priority++;
        setPriority(priorityThread);
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        priority++;
        if (priorityThread > group.getMaxPriority()) setPriority(group.getMaxPriority());
        else setPriority(priorityThread);
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        priority++;
        if (priorityThread > group.getMaxPriority()) setPriority(group.getMaxPriority());
        else setPriority(priorityThread);
    }
}
