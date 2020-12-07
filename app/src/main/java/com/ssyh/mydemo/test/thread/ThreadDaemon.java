package com.ssyh.mydemo.test.thread;

/**
 *
 *
 * 后台线程（Daemon Thread）
 *
 * 后台线程的生命周期与前台线程生命周期有一定关联。
 *
 * 主要体现在：当所有的前台线程都进入死亡状态时，后台线程会自动死亡
 *
 * 判断线程是否是后台线程：调用thread对象的isDeamon()方法。
 *
 * main线程默认是前台线程，前台线程创建中创建的子线程默认是前台线程，后台线程中创建的线程默认是后台线程。
 *
 * 调用setDeamon(true)方法将前台线程设置为后台线程时，需要在start()方法调用之前。前天线程都死亡后，JVM通知后台线程死亡，但从接收指令到作出响应，需要一定的时间。
 */
class ThreadDaemon {
    public static void main(String[] args) {
        Thread myThread = new MyThread();
        for (int i = 0; i < 100; i++) {
            System.out.println("main thread i = " + i);
            if (i == 20) {
                myThread.setDaemon(true);
                myThread.start();
            }
        }
    }

    static class MyThread extends Thread {

        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println("i = " + i);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
