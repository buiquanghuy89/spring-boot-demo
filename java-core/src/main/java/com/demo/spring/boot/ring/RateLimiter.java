package com.demo.spring.boot.ring;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by HuyBQ on 9/29/2019.
 */
public class RateLimiter {
    private int tpsPointer = 0;
    //private int size=1;
    private int period = 1;
    private long[] tpsRingBuffer = null;
    private boolean isLow = false;
    private int tpsHigh;
    private int tpsLow;
    public HashMap<Integer, Integer> map = new HashMap<>();

    public RateLimiter(int iperiod, int tpsHigh, int tpsLow) {
        this.period = iperiod;
        this.tpsHigh = tpsHigh;
        this.tpsLow = tpsLow;
        tpsRingBuffer = new long[tpsHigh];
    }

    public synchronized boolean testTPS() {
        if (getLimit() > 0) {
            int nextPointer = (tpsPointer + 1) % getLimit();

            long lastestTime = tpsRingBuffer[nextPointer];

            long currentTime = System.currentTimeMillis();

            if (lastestTime + getPeriod() * 1000 < currentTime) {
                tpsPointer = nextPointer;
                tpsRingBuffer[nextPointer] = currentTime;
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    public synchronized void acquireTPS(int msgNumber) throws InterruptedException {
        if (getLimit() > 0) {
            System.out.println("----------------------------------------------------");
            int offset = 0;
            if (isLow) {
                while (getLimit() % tpsLow > 0) {
                    tpsLow--;
                }
                offset = getLimit() / tpsLow;
            }
            int nextPointer = (tpsPointer + offset + msgNumber) % getLimit();

            long lastestTime = tpsRingBuffer[nextPointer];

            Calendar cal = Calendar.getInstance();

            System.out.println("nextPointer=" + nextPointer + ",    lastestTime=" + lastestTime + ",    currentTime=" + cal.getTimeInMillis() + " (" + cal.get(Calendar.SECOND) + ")");

            long wait = lastestTime + getPeriod() * 1000 - cal.getTimeInMillis();
            System.out.println("wait " + wait);
            if (wait > 0) {
                System.out.println("---------------------------------------------------->" + wait);
                TimeUnit.MILLISECONDS.sleep(wait);
            }

            tpsPointer = nextPointer;
            cal = Calendar.getInstance();
            tpsRingBuffer[nextPointer] = cal.getTimeInMillis();
            int second = cal.get(Calendar.SECOND);
            System.out.println("tpsRingBuffer[" + nextPointer + "]=" + tpsRingBuffer[nextPointer] + " (" + second + ")");
            System.out.println("send " + msgNumber + " sms");
            Integer num = map.get(second);
            if (num == null) {
                num = 0;
            }
            num += msgNumber;
            map.put(second, num);
            return;
        } else {
            return;
        }
    }

    public int getPeriod() {
        return period;
    }

    public int getLimit() {
        return tpsRingBuffer == null ? 0 : tpsRingBuffer.length;
    }

    public void setLow(boolean low) {
        isLow = low;
    }

    public boolean isLow() {
        return isLow;
    }

    public void changeRing() throws InterruptedException {
        long wait = tpsRingBuffer[tpsPointer] == 0 ? 1000 : tpsRingBuffer[tpsPointer] + 1000 - new Date().getTime();
        tpsPointer = 0;
        if (isLow) {
            tpsRingBuffer = new long[tpsLow];
        } else {
            tpsRingBuffer = new long[tpsHigh];
        }
        System.out.println("---------------------------------------------------->" + wait);
        TimeUnit.MILLISECONDS.sleep(wait);
    }

    public static void main(String[] args) throws InterruptedException {
        RateLimiter rateLimiter = new RateLimiter(1, 6, 3);
//        rateLimiter.setLow(true);
//        rateLimiter.changeRing();
        for (int i = 0; i < 10; i++) {
//            Random rand = new Random();
//            int val = rand.nextInt(22);
//            if (val % 7 == 0) {
//                if (rateLimiter.isLow()) {
//                    rateLimiter.setLow(false);
//                    rateLimiter.changeRing();
//                } else {
//                    rateLimiter.setLow(true);
//                    rateLimiter.changeRing();
//                }
//            }
            rateLimiter.acquireTPS(1);
        }
        rateLimiter.acquireTPS(3);
        rateLimiter.acquireTPS(1);
        rateLimiter.acquireTPS(1);
        rateLimiter.acquireTPS(1);
        rateLimiter.acquireTPS(1);
        rateLimiter.acquireTPS(1);
        rateLimiter.acquireTPS(1);
        rateLimiter.acquireTPS(1);
        System.out.println(rateLimiter.map);
    }
}
