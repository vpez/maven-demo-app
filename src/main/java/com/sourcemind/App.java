package com.sourcemind;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {

        // Basic usage
        final ApplicationServiceImpl service = new ApplicationServiceImpl();
        ApplicationService proxyInstance = (ApplicationService) Proxy.newProxyInstance(App.class.getClassLoader(),
                new Class[]{ApplicationService.class},
                (proxy, method, args1) -> {
                    System.out.println("Before");
                    Object result = method.invoke(service, args1);
                    System.out.println("After");
                    return result;
                }
        );

        ApplicationService proxyService = LoggingProxy.create(
          ApplicationService.class,
          service
        );

        proxyService.method1();
        proxyService.test();

        Map<String, String> proxyMap = LoggingProxy.create(
                Map.class,
                new HashMap<>()
        );

        proxyMap.put("1", "One");
        String value = proxyMap.get("1");
        System.out.println();
    }
}
