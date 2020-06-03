package com.rearwave.blog.component.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * 序列化工具类，
 * @author sunyi
 */
public final class GSON {

    private static Gson filterNullGson;
    private static Gson nullableGson;
    static {
        nullableGson = new GsonBuilder()
                .enableComplexMapKeySerialization()
                .serializeNulls()
                .setDateFormat("yyyy/MM/dd HH:mm:ss")
                .create();
        filterNullGson = new GsonBuilder()
                .enableComplexMapKeySerialization()
                .setDateFormat("yyyy/MM/dd HH:mm:ss")
                .create();
    }



    /**
     * 根据对象返回json   不过滤空值字段
     */
    public static String toJSONStringWithNullValue(Object obj){
        return nullableGson.toJson(obj);
    }

    /**
     * 根据对象返回json  过滤空值字段
     */
    public static String toJSONString(Object obj){
        return filterNullGson.toJson(obj);
    }

    /**
     * 将json转化为对应的实体对象
     * new TypeToken<HashMap<String, Object>>(){}.getType()
     */
    public static <T>  T parseObject(String json, Type type){
        return nullableGson.fromJson(json, type);
    }

    /**
     * 将json转换成集合
     * @param json json字符串
     * @param clazz 转换的对象
     * @return 目标对象集合
     */
    public static <T> List<T> parseArray(String json, Class<T> clazz){
        return nullableGson.fromJson(json, TypeToken.getParameterized(List.class,clazz).getType());
    }

    /**
     * 将对象值赋值给目标对象
     * @param source 源对象
     * @param <T> 目标对象类型
     * @return 目标对象实例
     */
    public static <T> T convert(Object source, Class<T> clz){
        String json = toJSONStringWithNullValue(source);
        return parseObject(json, clz);
    }

}
