package com.ssyh.mydemo.test.thread;

/**
 * 调用了新建的线程的start()方法后，并不会立即执行，而是会进入到就绪状态，
 * 可能会在接下来的某个时间获取CPU时间片得以执行，如果希望这个新线程必然性的立即执行，直接调用原来线程的sleep(1)即可。
 */
public class ThreadSleep {
    public static void main(String[] args) {

        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);

        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 30) {
                thread.start();
                //加上 下面的代码后，可以看到打印，线程立即在 循环到第30时执行了
                //而不加下面的代码，
                try {
                    Thread.sleep(1);   // 使得thread必然能够马上得以执行
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
        }
    }
}


