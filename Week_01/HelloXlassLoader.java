package com.newProject;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @version 1.0
 * @program: ATestProject
 * @description: TODO
 * @author: Philip.Zhang
 * @date: 2021/8/7 6:19 PM
 */
public class HelloXlassLoader extends ClassLoader {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String fileName = "Hello.xlass";
        String methodName = "hello";

        ClassLoader helloXlassLoader = new HelloXlassLoader();
        Class<?> helloClass = helloXlassLoader.loadClass(fileName);
        for (Method method : helloClass.getDeclaredMethods()) {
            System.out.println("Method: " + method.getName());
        }
        for (Field field : helloClass.getDeclaredFields()) {
            System.out.println("Field: " + field.getName());
        }

        Object obj = helloClass.getDeclaredConstructor().newInstance();
        Method method = helloClass.getMethod(methodName);
        method.invoke(obj);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File file = new File(this.getClass().getClassLoader().getResource(name).getPath());
        byte[] bytes;
        try (InputStream in = new FileInputStream(file)) {
            bytes = in.readAllBytes();
            decode(bytes);

            return defineClass(name.split("\\.")[0], bytes, 0, bytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException(name, e);
        }
    }

    private void decode(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = ((byte) (255 - bytes[i]));
        }
    }


}
