package ga.nullcraft.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Reflect {
    public static <T>T getField(Object obj, String name) {
        return getField(obj.getClass(), obj, name);
    }

    public static <T>T getField(Class<?> c, String name) {
        return getField(c, null, name);
    }

    public static <T>T getField(Class<?> c, Object obj, String name) {
        try {
            Field field = getDeclaredField(c, name);

            return (T) field.get(obj);
        } catch (NullPointerException | IllegalAccessException e) {
            System.out.println("cannot access " + name + " in " + c.getName());
        }

        return null;
    }

    public static void setField(Class<?> c, Object obj, String name, Object value) {
        try {
            Field field = getDeclaredField(c, name);

            field.set(obj, value);
        } catch (NullPointerException | IllegalAccessException e) {
            System.out.println("cannot access " + name + " field in " + c.getName());
        }
    }

    public static void setField(Object obj, String name, Object value) {
        setField(obj.getClass(), obj, name, value);
    }

    public static void setField(Class<?> c, String name, Object value) {
        setField(c, null, name, value);
    }

    private static Field getDeclaredField(Class<?> c, String name) {
        try {
            Field field = c.getDeclaredField(name);
            field.setAccessible(true);

            return field;
        } catch (NoSuchFieldException e) {
            System.out.println(name + " field in " + c.getName() + " not found");
        }

        return null;
    }

    public static <T>T invokeMethod(Object obj, String name, Object... params) {
        return invokeMethod(obj.getClass(), obj, name, params);
    }

    public static <T>T invokeMethod(Class<?> c, String name, Object... params) {
        return invokeMethod(c, null, name, params);
    }

    public static <T>T invokeMethod(Class<?> c, Object obj, String name, Object... params) {
        try {
            Class<?>[] classes = new Class<?>[params.length];

            for (int i = 0; i < params.length; i++){
                classes[i] = params.getClass();
            }

            Method method = getDeclaredMethod(c, name, classes);

            return (T) method.invoke(obj, params);

        } catch (NullPointerException | IllegalAccessException | InvocationTargetException e) {
            System.out.println("cannot access " + name + " method in " + obj.getClass().getName());
        }

        return null;
    }

    private static Method getDeclaredMethod(Class<?> c, String name, Class<?>... classes) {
        try {
            Method method = c.getDeclaredMethod(name, classes);
            method.setAccessible(true);

            return method;
        } catch (NoSuchMethodException e) {
            System.out.println(name + " method in " + c.getName() + " not found");
        }

        return null;
    }
}
