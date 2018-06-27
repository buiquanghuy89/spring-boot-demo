package com.demo.spring.boot.core.thread;

/**
 * Created by HuyBQ on 9/16/2016.
 */
public class JoinTest {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("\n==> Main thread starting..\n");

        Thread joinThreadA = new JoinThread("A*", 6);
        Thread joinThreadB = new JoinThread("B*", 3);

        // Thread thông thường, sẽ không sử dụng join().
        Thread noJoinThreadC = new JoinThread("C", 8);

        joinThreadA.start();
        joinThreadB.start();
        noJoinThreadC.start();
        // Sử dụng join()
        System.out.println("Thread A Join");
        joinThreadA.join();
        System.out.println("Thread B Join");
        joinThreadB.join();

        // Đoạn code dưới đây sẽ phải chờ cho tới khi 2
        // joinThread A,B hoàn thành, mới được chạy tiếp.
        System.out.println("Hello from main thread...");

        System.out.println("Thread A isLive? " + joinThreadA.isAlive());
        System.out.println("Thread B isLive? " + joinThreadB.isAlive());
        System.out.println("Thread C isLive? " + noJoinThreadC.isAlive());

        System.out.println("\n==> Main Thread end!\n");
    }
}
