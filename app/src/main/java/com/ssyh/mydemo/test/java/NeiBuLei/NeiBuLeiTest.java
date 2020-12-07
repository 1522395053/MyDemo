package com.ssyh.mydemo.test.java.NeiBuLei;

public class NeiBuLeiTest {

    public static void main(String[] args)  {
        //成员内部类测试
        //第一种方式：
        Outter outter = new Outter();
        Outter.Inner inner = outter.new Inner();  //必须通过Outter对象来创建


        Outter.Inner inner1 = outter.new Inner();

        //第二种方式：
        Outter.Inner inner2 = outter.getInnerInstance();
    }
}

/**
 * 成员内部类
 */
class Outter {
    private Inner inner = null;
    public Outter() {

    }

    public Inner getInnerInstance() {
        if(inner == null)
            inner = new Inner();
        return inner;
    }

    class Inner {
        public Inner() {


        }
    }
}

/**
 * 局部内部类
 */
class People{
    public People() {

    }
}

class Man{
    public Man(){

    }

    /**
     * 局部内部类创建在方法中
     * 局部内部类就像是方法里面的一个局部变量一样，是不能有public、protected、private以及static修饰符的。
     * @return
     */
    public People getWoman(){
        class Woman extends People{   //局部内部类
            int age =0;
        }
        return new Woman();
    }
}
