package com.rearwave.blog.component.utils;

import org.springframework.core.NamedThreadLocal;

/**
 * @author sunyi
 */
public class SnUtil {


    private static final ThreadLocal<Long> serialNum = new NamedThreadLocal<>("serial_number");


    public static void set(long nanoTime){
        serialNum.set(nanoTime);
    }

    public static Long get(){
        return serialNum.get();
    }

    public static void clear(){
        serialNum.remove();
    }
}
