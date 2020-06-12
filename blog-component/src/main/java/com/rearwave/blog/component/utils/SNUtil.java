package com.rearwave.blog.component.utils;

import org.springframework.core.NamedThreadLocal;

/**
 * @author sunyi
 */
public class SNUtil {

    private static final ThreadLocal<Long> useTime = new NamedThreadLocal<>("request_time");

    private static final ThreadLocal<Long> serialNum = new NamedThreadLocal<>("serial_number");

    public static void setUseTime(long timestamp){
        useTime.set(timestamp);
    }

    public static Long getUseTime(){
        try {
            return useTime.get();
        } finally {
            useTime.remove();
        }

    }

    public static void set(long nanoTime){
        serialNum.set(nanoTime);
    }

    public static Long get(){
        try {
            return serialNum.get();
        }finally {
            serialNum.remove();
        }
    }
}
