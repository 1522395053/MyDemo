package com.ssyh.mydemo.test.threadlocal;

class Test {
    ThreadLocal<Long> longThreadLocal = new ThreadLocal<>();
    ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();

    public void set(){
        longThreadLocal.set(Thread.currentThread().getId());
        stringThreadLocal.set(Thread.currentThread().getName());
    }

    public Long getLong(){
        return longThreadLocal.get();
    }

    public String getString(){
        return stringThreadLocal.get();
    }

    public static void main(String[] args){
        final Test test = new Test();

//        test.set();//不先set会报空指针异常??? 其实是 调用 getLong 方法 返回类型写的是基本数据类型

//        longThreadLocal.get()返回 null ，而null 在自动拆箱，及强制类型转换时，是不能转换的，自然就报错了，返回值写Long即可

        System.out.println("11111："+test.getLong());
        System.out.println("11111："+test.getString());


        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                test.set();

                System.out.println("22222："+test.getLong());
                System.out.println("22222："+test.getString());
            }
        };

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("33333："+test.getLong());
        System.out.println("33333："+test.getString());
    }
}
