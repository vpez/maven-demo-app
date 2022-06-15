package com.sourcemind;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LoggingProxy implements InvocationHandler {

    private final Object target;

    // Generic factory method to apply the proxy to any type
    @SuppressWarnings("unchecked")
    public static <T> T create(Class<T> interfaceType, T realObject) {

        // Run-time checking
        if (!interfaceType.isAssignableFrom(realObject.getClass())) {
            throw new IllegalArgumentException();
        }

        return (T) Proxy.newProxyInstance(realObject.getClass().getClassLoader(), new Class[]{interfaceType}, new LoggingProxy(realObject));
    }

    // Can make this private
    public LoggingProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Called method " + method.getName() + " on " + target.getClass().getName());
        Object result = method.invoke(target, args);
        return result;
    }
}
