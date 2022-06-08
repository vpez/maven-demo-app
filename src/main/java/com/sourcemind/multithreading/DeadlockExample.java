package com.sourcemind.multithreading;

public class DeadlockExample {

    private Object lock1 = new Object();
    private Object lock2 = new Object();

    void methodA() {
        synchronized (lock1) {
            System.out.println("method a started");
            methodB();
            System.out.println("method a finished");
        }
    }

    void methodB() {
        synchronized (lock2) {
            System.out.println("method b started");
            synchronized (lock1) {
                System.out.println("processing in method b");
            }
            System.out.println("method b finished");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DeadlockExample example = new DeadlockExample();

        Thread thread1 = new Thread(example::methodA);
        Thread thread2 = new Thread(example::methodB);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("program end");
    }
}
