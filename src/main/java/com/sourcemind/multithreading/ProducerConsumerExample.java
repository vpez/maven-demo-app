package com.sourcemind.multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerExample {
    public static void main(String[] args) {
        var app = new ProducerConsumerExample();
        new Thread(app::produce).start();
        new Thread(app::consume).start();
    }

    long number = 1;
    Object lock = new Object();
    boolean ready = false;

    void produce() {
        while (true) {
            synchronized (lock) {
                number++;
                ready = true;
                lock.notify();
            }
        }
    }

    void consume() {
        while (true) {
            synchronized (lock) {
                while (!ready) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("number: " + number);
                ready = false;
            }
        }
    }
}
