package com.ssyh.mydemo.test.genericType;

// generics/Erased.java
// {WillNotCompile}

//因为擦除，我们将失去执行泛型代码中某些操作的能力。无法在运行时知道确切类型：

//有时，我们可以对这些问题进行编程，但是有时必须通过引入类型标签来补偿擦除。这意味着为所需的类型显式传递一个 Class 对象，以在类型表达式中使用它。

public class Erased<T> {
    private final int SIZE = 100;

    public void f(Object arg) {
        /**
         * 尝试使用 instanceof 将会失败。类型标签可以使用动态 isInstance() ：
         * 使用 Class 的  isInstance 替代
         * {@link ClassTypeCapture} 查看 ClassTypeCapture 的方式，通过 Class 达到 instanceof的效果
         */
        // error: illegal generic type for instanceof
//        if (arg instanceof T) {
//        }


        /**
         * 参考 InstantiateGenericType 实现，实现 new T()效果
         * {@link InstantiateGenericType}
         * InstantiateGenericType
         */
        // error: unexpected type
//        T var = new T();


        // error: generic array creation   Type parameter 'T' cannot be instantiated directly 翻译： 无法直接实例化类型参数“T”
//        T[] array = new T[SIZE];
        // warning: [unchecked] unchecked cast
        T[] array2 = (T[]) new Object[SIZE];

    }
}

