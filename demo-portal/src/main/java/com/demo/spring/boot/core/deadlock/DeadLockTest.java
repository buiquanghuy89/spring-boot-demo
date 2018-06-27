package com.demo.spring.boot.core.deadlock;

/**
 * Created by HuyBQ on 9/27/2016.
 */
public class DeadLockTest {
    class A {
        public A(int x) {
            this.x = x;
        }

        private int x;

        public int getX() {
            return x;
        }
    }

    public static void main(String[] args) {
        try {
            DeadLockTest deadLockTest = new DeadLockTest();
            A a = deadLockTest.new A(1);
            A b = deadLockTest.new A(2);

            Runnable block1 = new Runnable() {
                @Override
                public void run() {
                    synchronized (a) {
                        try {
                            // Dùng phương thức sleep để Thread-1 tạm dừng lại
                            System.out.println("block1 " + a.getX());
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        // Thread-1 chiếm giữ A nhưng muốn có B
                        synchronized (b) {
                            System.out.println("block1 " + b.getX());
                        }
                    }
                }
            };

            Runnable block2 = new Runnable() {
                @Override
                public void run() {
                    synchronized (b) {
                        // Thread-2 chiếm giữ B nhưng cũng muốn có A
                        System.out.println("block2 " + b.getX());
                        synchronized (a) {
                            System.out.println("block2 " + a.getX());
                        }
                    }
                }
            };

            new Thread(block1).start();
            new Thread(block2).start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
