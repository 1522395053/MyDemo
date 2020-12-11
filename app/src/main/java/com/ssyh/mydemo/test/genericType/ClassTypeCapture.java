package com.ssyh.mydemo.test.genericType;

// generics/ClassTypeCapture.java

class Building {
}

class House extends Building {
}

public class ClassTypeCapture<T> {
    Class<T> kind;

    public ClassTypeCapture(Class<T> kind) {
        this.kind = kind;
    }

    public boolean f(Object arg) {
        //arg 是 kind的 子类 即可
        return kind.isInstance(arg);
    }

    public static void main(String[] args) {
        ClassTypeCapture<Building> ctt1 = new ClassTypeCapture<>(Building.class);

        System.out.println(ctt1.f(new Building()));
        System.out.println(ctt1.f(new House()));


        ClassTypeCapture<House> ctt2 = new ClassTypeCapture<>(House.class);

        System.out.println(ctt2.f(new Building()));
        System.out.println(ctt2.f(new House()));
    }
}
/* Output:
true
true
false
true
*/
