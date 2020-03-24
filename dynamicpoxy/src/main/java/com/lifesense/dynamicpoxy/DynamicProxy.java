package com.lifesense.dynamicpoxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/*
 *  @项目名：  gank-io
 *  @包名：    com.lifesense.dynamicpoxy
 *  @文件名:   DynamicProxy
 *  @创建者:   lz
 *  @创建时间:  2020/3/18 13:47
 *  @描述：
 */
public class DynamicProxy implements InvocationHandler {

    public DynamicProxy() {
    }
    public Object getProxyInstance(Object target){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
                this);
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        // TODO: 2020/3/18 循环引用
        System.out.println(o.getClass().getName());
        System.out.println(Arrays.toString(objects));
        System.out.println(method.getName());
        Object invoke = method.invoke(o, objects);
        System.out.println(invoke.getClass().getName());
        return invoke;
    }
}
