package com.ssyh.mydemo.test.java;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Student{

    public void getStudent() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, CloneNotSupportedException, IOException, ClassNotFoundException {
        Student student = Student.class.newInstance();


        Constructor<Student> constructor = Student.class.getConstructor(Integer.class);

        Student student1 = constructor.newInstance(123);


        Student student2 = (Student) student1.clone();

        //clone 方法不会调用构造方法

        //反序列化也不会调用构造方法

        //使用newInstance方法的这两种方式创建对象使用的就是Java的反射机制，事实上Class的newInstance方法内部调用的也是Constructor的newInstance方法。

        /*实际上，如果我们对实例变量直接赋值或者使用实例代码块赋值，
        那么编译器会将其中的代码放到类的构造函数中去，

        并且这些代码会被放在对超类构造函数的调用语句之后

                (还记得吗？Java要求构造函数的第一条语句必须是超类构造函数的调用语句)，构造函数本身的代码之前。*/

        //序列化：

        //写对象
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("student.bin"));
        outputStream.writeObject(student);
        outputStream.close();


        //读对象
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("student.bin"));
        Student student3 = (Student) objectInputStream.readObject();
        objectInputStream.close();

        System.out.println(student3);
    }




}

// 当一个对象被创建时，虚拟机就会为其分配内存来存放对象自己的实例变量及其从父类继承过来的实例变量(即使这些从超类继承过来的实例变量有可能被隐藏也会被分配空间)。在为这些实例变量分配内存的同时，
//
//  这些实例变量也会被赋予默认值(零值)。
//
// 在内存分配完成之后，
//
// Java虚拟机就会开始对新创建的对象按照程序猿的意志进行初始化。
//
//  在Java对象初始化过程中，主要涉及三种执行对象初始化的结构，分别是 实例变量初始化、实例代码块初始化 以及 构造函数初始化。
//

class InstanceInitializer {
    //Java是按照编程顺序来执行实例变量初始化器和实例初始化器中的代码的，并且不允许顺序靠前的实例代码块初始化在其后面定义的实例变量
    //无法通过编译 但是我们仍然有办法绕过这种检查
    private int j = getI();


    private int i = 1;

    public InstanceInitializer() {
        i = 2;
    }

    private int getI() {
        return i;
    }

    public static void main(String[] args) {
        InstanceInitializer ii = new InstanceInitializer();
        System.out.println(ii.j);

        //如果我们执行上面 这段代码，那么会发现打印的结果是0。因此我们可以确信，变量j被赋予了i的默认值0，这一动作发生在实例变量i初始化之前和构造函数调用之前。
    }
}

 class ConstructorExample {
    private int i;

    ConstructorExample() {

        //下面的this会报错，因为 ConstructorExample（int i） 也默认会有  super，这么用的话  ConstructorExample（int i）中的super  就不是第一行了
        super();
        //this(1);  // Error:Constructor call must be the first statement in a constructor

    }

    ConstructorExample(int i) {
        super();
        this.i = i;

    }
}