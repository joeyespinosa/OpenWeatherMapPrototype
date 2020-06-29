package com.axelia.openweathermapprototype.model;

import android.location.Location;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class UtilConverters {

    private static Gson gson = new Gson();

    @TypeConverter
    public static String fromWeatherList(List<Weather> items) {
        return gson.toJson(items);
    }

    @TypeConverter
    public static List<Weather> toWeatherList(String items) {
        if (items == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Weather>>() {}.getType();
        return gson.fromJson(items, listType);
    }

    @TypeConverter
    public static String fromCoord(Coord item) {
        return gson.toJson(item);
    }

    @TypeConverter
    public static Coord toCoord(String item) {
        Type itemType = new TypeToken<Coord>() {}.getType();
        return gson.fromJson(item, itemType);
    }

    @TypeConverter
    public static String fromMain(Main item) {
        return gson.toJson(item);
    }

    @TypeConverter
    public static Main toMain(String item) {
        Type itemType = new TypeToken<Main>() {}.getType();
        return gson.fromJson(item, itemType);
    }

    @TypeConverter
    public static String fromWind(Wind item) {
        return gson.toJson(item);
    }

    @TypeConverter
    public static Wind toWind(String item) {
        Type itemType = new TypeToken<Wind>() {}.getType();
        return gson.fromJson(item, itemType);
    }

    @TypeConverter
    public static String fromSys(Sys item) {
        return gson.toJson(item);
    }

    @TypeConverter
    public static Sys toSys(String item) {
        Type itemType = new TypeToken<Sys>() {}.getType();
        return gson.fromJson(item, itemType);
    }

    @TypeConverter
    public static String fromClouds(Clouds item) {
        return gson.toJson(item);
    }

    @TypeConverter
    public static Clouds toClouds(String item) {
        Type itemType = new TypeToken<Clouds>() {}.getType();
        return gson.fromJson(item, itemType);
    }
}
