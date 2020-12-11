package com.ssyh.mydemo.test.genericType;

// generics/Manipulation.java
// {WillNotCompile}

class HasF {
    public void f() {
        System.out.println("HasF.f()");
    }
}

// generics/Manipulator3.java

class Manipulator3 {
    private HasF obj;

    Manipulator3(HasF x) {
        obj = x;
    }

    public void manipulate() {
        obj.f();
    }
}

/*

 思考，那么 Manipulator<T extends HasF>   和  Manipulator3（直接内部持有 HasF  ） 有什么区别？？？？？

 这提出了很重要的一点：泛型只有在类型参数比某个具体类型（以及其子类）更加“泛化”—— 代码能跨多个类工作时才有用。
 */

class Manipulator<T extends HasF> {
    private T obj;

    Manipulator(T x) {
        obj = x;
    }

    // Error: cannot find symbol: method f():
    // 我们必须协助泛型类，给定泛型类一个边界，以此告诉编译器只能接受遵循这个边界的类型。这里重用了 extends 关键字。由于有了边界，下面的代码就能通过编译：
    public void manipulate() {
        obj.f();
    }
}

public class Manipulation {
    public static void main(String[] args) {
        HasF hf = new HasF();
        Manipulator<HasF> manipulator = new Manipulator<>(hf);
        manipulator.manipulate();
    }
}
