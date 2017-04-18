package org.uit.rit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;


public class EntityGson<T> {
    private static final String dateFormat = "yyyy-MM-dd'T'HH:mm:ss";//"dd.MM.yyyy";
    private Gson gson = null;
    private Type type = new TypeToken<T>() {
    }.getType();

    public EntityGson() {
        GsonBuilder builder = new GsonBuilder();
        builder.setDateFormat(dateFormat);
        gson = builder.create();
    }


    public String toJson(T item) {
        return gson.toJson(item);
    }


    public T fromJson(String json) throws JsonSyntaxException {
        return gson.fromJson(json, type);
    }

}
