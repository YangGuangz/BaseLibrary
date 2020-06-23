package com.ygz.baselibrary.util;

import java.lang.reflect.ParameterizedType;

/**
 * @Date: 2019/10/16 19:48
 * @Author: YGZ
 * @Description: 泛型实例化工具类：通过这个类我们可以传入一个对象，通过这个对象与泛型所在位置实例化出一个泛型的对象。
 * @Version:
 */
public class ClassReflectHelper {

    /**
     * getClass().getGenericSuperclass():
     * 返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type，然后将其转换ParameterizedType。
     * getActualTypeArguments():
     * 返回表示此类型实际类型参数的 Type 对象的数组。[0]就是这个数组中第一个了。简而言之就是获得超类的泛型参数的实际类型。
     */
    public static <T> T getT(Object o, int i) {
        try {
            return ((Class<T>) ((ParameterizedType) (o.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[i])
                    .newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Class<?> forName(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}

