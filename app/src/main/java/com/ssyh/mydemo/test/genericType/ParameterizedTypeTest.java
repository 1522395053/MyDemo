package com.ssyh.mydemo.test.genericType;

import com.google.gson.annotations.SerializedName;
import com.ssyh.mydemo.test.entity.User;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.List;

public class ParameterizedTypeTest<T> {

    private T t;//TypeVariable
    private List<T> tlist;//ParameterizedType
    private List<List<T>> tlistList;//ParameterizedType
    private List<List<List<T>>> tlistListList;//ParameterizedType
    private T[] tArr;           //GenericArrayType
    private List<T>[] tListArr;//GenericArrayType
    private List<String>[] stringListArr;//GenericArrayType
    private List<? extends ParameterizedTypeTest>[] q_extends_ListArr;//GenericArrayType
    private List<? super ParameterizedTypeTest> q_super_List;//ParameterizedType <> 中的 ? super ParameterizedTypeTest  就是 WildcardType   Wildcard 翻译：通配符
    private List<? extends String > q_extends_List;//ParameterizedType <> 中的 ? extends String 就是 WildcardType
    private List<? extends List<String>> q_extends_stringList;//ParameterizedType <> 中的 ? extends List<String> 就是 WildcardType

    @SerializedName("ddd")
    public <E extends ParameterizedTypeTest & Serializable> List<User> method(){

        return null;
    }

    public static void main(String[] args) throws NoSuchFieldException {
        Method[] declaredMethods = ParameterizedTypeTest.class.getDeclaredMethods();

        for (Method declaredMethod : declaredMethods) {
            Type genericReturnType = declaredMethod.getGenericReturnType();
            System.out.println("genericReturnType:  "+genericReturnType + "  ，getName : "+declaredMethod.getName());
            System.out.println(declaredMethod.getAnnotations().length);
            if (declaredMethod.getAnnotations().length > 0){
                System.out.println(declaredMethod.getAnnotations()[0]);
            }
        }


        Field[] declaredFields = ParameterizedTypeTest.class.getDeclaredFields();

        for (Field declaredField : declaredFields) {

            System.out.println("字段名：" + declaredField.getName() +", 字段带泛型类型：" + declaredField.getGenericType());
            System.out.println("字段名：" + declaredField.getName() +", 字段类型：" + declaredField.getType());

            Type genericType = declaredField.getGenericType();

            if (genericType instanceof ParameterizedType){
                System.out.println("instanceof ParameterizedType:\n");

                System.out.println("getName: "+genericType.getClass().getName());

                System.out.println("ParameterizedType 的方法=======================================");
                Type actualTypeArgument = ((ParameterizedType) genericType).getActualTypeArguments()[0];
                System.out.println("getActualTypeArguments [0]: "+actualTypeArgument);

                System.out.println("getActualTypeArguments [0]: getName: "+(actualTypeArgument.getClass().getName()));
                System.out.println("getOwnerType: "+((ParameterizedType) genericType).getOwnerType());
                System.out.println("getRawType: "+((ParameterizedType) genericType).getRawType());




                if (actualTypeArgument instanceof WildcardType){
                    System.out.println("===========================actualTypeArgument instanceof WildcardType : true");
                    WildcardType wildcardType = (WildcardType) actualTypeArgument;

                    if (wildcardType.getLowerBounds().length > 0){
                        System.out.println("getLowerBounds: 下边界：  "+ wildcardType.getLowerBounds()[0]);
                    }

                    if (wildcardType.getUpperBounds().length > 0){
                        System.out.println("getLowerBounds: 上边界：  "+ wildcardType.getUpperBounds()[0]);
                    }

                    System.out.println("\n");
                }



                System.out.println("\n");
            }

            if (genericType instanceof Class){
                System.out.println("instanceof Class");
                System.out.println("\n");
            }




            if (genericType instanceof TypeVariable){
                System.out.println("instanceof TypeVariable");
                System.out.println("\n");
            }
            if (genericType instanceof GenericArrayType){
                System.out.println("instanceof GenericArrayType");
                GenericArrayType genericArrayType = (GenericArrayType) genericType;

                System.out.println("getGenericComponentType: "+ genericArrayType.getGenericComponentType());
                System.out.println("\n");
            }



            System.out.println("*********************************************************************************************************");
        }



        getRealParamsTypeByChildClass();


    }

    private static void getRealParamsTypeByChildClass(){
        System.out.println("==============通过创建 带泛型参数类 的子类 来获取传入的泛型参数的具体实例类型=======================================");
        System.out.println("1.不传入泛型实际参数（实际上List<T> 的 T 应该是被当作Object了）");
        Type genericSuperclass = new ParameterizedTypeTest() {
        }.getClass().getGenericSuperclass();
        System.out.println("instanceof ParameterizedType: "+(genericSuperclass instanceof ParameterizedType)
                + "， intanceof Class： "+ (genericSuperclass instanceof Class) + "， 说明仅仅是一个普通Class");
        System.out.println("2.传入泛型实际参数 List<String>");
        Type genericSuperclass2 = new ParameterizedTypeTest<List<String>>() {
        }.getClass().getGenericSuperclass();
        System.out.println("instanceof ParameterizedType: "+ (genericSuperclass2 instanceof ParameterizedType));
        System.out.println("getName: "+ genericSuperclass2.getClass().getName());
        System.out.println("getRawType: "+((ParameterizedType)genericSuperclass2).getRawType());
        System.out.println("getOwnerType: "+ ((ParameterizedType)genericSuperclass2).getOwnerType());
        System.out.println("getActualTypeArguments[0]: "+((ParameterizedType)genericSuperclass2).getActualTypeArguments()[0]);
        System.out.println("getActualTypeArguments[0] getName : "+((ParameterizedType)genericSuperclass2).getActualTypeArguments()[0].getClass().getName());
    }

}
