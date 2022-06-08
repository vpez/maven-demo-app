package com.sourcemind.multithreading;

public class BlockedExample {
    private final Object lock = new Object();

    void safeMethod() {
        System.out.println(Thread.currentThread().getName() + " start");
        while (true) {
            synchronized (lock) {
                // do work
                System.out.println(Thread.currentThread().getName());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        var example = new BlockedExample();

        Thread a = new Thread(example::safeMethod);
        a.start();

        Thread b = new Thread(example::safeMethod);
        b.start();

        a.join();
        b.join();
        System.out.println("Threads are done");
    }
}
