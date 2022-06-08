package com.sourcemind;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.time.LocalDate;

public class ClassLoaderTest {
    public static void main(String[] args) throws IOException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String current = new File(".").getCanonicalPath();
        URL currentUrl = new URL(String.format("file://%s/", current));
        URLClassLoader classLoader = new URLClassLoader(new URL[]{currentUrl});
        try {
            Class<?> clazz = classLoader.loadClass("com.sourcemind.ClassLoaderTest");
            System.out.println("Loaded " + clazz.getSimpleName());

            ClassLoaderTest o = (ClassLoaderTest) clazz.getDeclaredConstructor(String.class).newInstance("a");

            clazz = Class.forName("com.sourcemind.ClassLoaderTest");
            System.out.println("Loaded from " + clazz.getClassLoader().getClass().getCanonicalName());

        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public ClassLoaderTest(String a) {

    }

    public void method1() {

    }

    public LocalDate method2(String date) {
        return null;
    }
}
