package com.mp;

import java.util.concurrent.CountDownLatch;

/**
 * 特有方法：
 * public CountDownLatch(int count); //指定计数的次数，只能被设置1次
 * public void countDown();          //调用此方法则计数减1
 * public void await() throws InterruptedException   //调用此方法会一直阻塞当前线程，直到计时器的值为0，除非线程被中断。
 * Public Long getCount();           //得到当前的计数
 * Public boolean await(long timeout, TimeUnit unit) //调用此方法会一直阻塞当前线程，直到计时器的值为0，除非线程被中断或者计数器超时，返回false代表计数器超时。
 * From Object Inherited：
 * Clone、equals、hashCode、notify、notifyALL、wait等。
 */
public class App {
    public static void main(String[] args) throws Exception {
        final CountDownLatch latch = new CountDownLatch(2);
        new Thread() {
            @Override
            public void run() {
                System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
                latch.countDown();
            }

            ;
        }.start();
        new Thread() {
            @Override
            public void run() {
                System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
                latch.countDown();
            }

            ;
        }.start();
        System.out.println("等待 2 个子线程执行完毕...");
        latch.await();//调用此方法会一直阻塞当前线程，直到计时器的值为0，除非线程被中断。
        System.out.println("2 个子线程已经执行完毕");
        System.out.println("继续执行主线程");
    }
}
