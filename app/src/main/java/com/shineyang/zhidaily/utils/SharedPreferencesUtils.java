package com.shineyang.zhidaily.utils;/*
 * Copyright 2016 ShineYang
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 *  代码中的"‚‗‚" 符号并不是指逗号,它是SINGLE LOW-9 QUOTATION MARK unicode 201A
 *  和 unicode 2017 用来分割list的条目的符号.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

//import com.google.gson.Gson;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;


public class SharedPreferencesUtils {

    private SharedPreferences preferences;

    public SharedPreferencesUtils(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * 获取方法
     * getInt,getListInt,getLong,getFloat,getDouble,getListDouble
     * getString,getListString,getBoolean,getListBoolean
     * getObject,getObjectList
     */

    /**
     * 使用key来获取int值
     *
     * @param key SharedPreferences key
     * @return int value
     */
    public int getInt(String key) {
        return preferences.getInt(key, 0);
    }

    /**
     * 解析获取Integers类型的ArrayList
     *
     * @param key SharedPreferences key
     * @return ArrayList of Integers
     */
    public ArrayList<Integer> getListInt(String key) {
        String[] myList = TextUtils.split(preferences.getString(key, ""), "‚‗‚");
        ArrayList<String> arrayToList = new ArrayList<>(Arrays.asList(myList));
        ArrayList<Integer> newList = new ArrayList<>();

        for (String item : arrayToList)
            newList.add(Integer.parseInt(item));

        return newList;
    }

    /**
     * 获取long值
     *
     * @param key SharedPreferences key
     */
    public long getLong(String key, long defaultValue) {
        return preferences.getLong(key, defaultValue);
    }

    /**
     * 获取float值
     *
     * @param key SharedPreferences key
     */
    public float getFloat(String key) {
        return preferences.getFloat(key, 0);
    }

    /**
     * 获取double值
     *
     * @param key SharedPreferences key
     */
    public double getDouble(String key, double defaultValue) {
        String number = getString(key);
        try {
            return Double.parseDouble(number);

        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * 解析获取double类型的ArrayList
     *
     * @param key SharedPreferences key
     * @return ArrayList of Double
     */
    public ArrayList<Double> getListDouble(String key) {
        String[] myList = TextUtils.split(preferences.getString(key, ""), "‚‗‚");
        ArrayList<String> arrayToList = new ArrayList<>(Arrays.asList(myList));
        ArrayList<Double> newList = new ArrayList<>();

        for (String item : arrayToList)
            newList.add(Double.parseDouble(item));

        return newList;
    }

    /**
     * 获取String
     *
     * @param key SharedPreferences key
     */
    public String getString(String key) {
        return preferences.getString(key, "");
    }

    /**
     * 解析获取String类型的ArrayList
     *
     * @param key SharedPreferences key
     * @return ArrayList of String
     */
    public ArrayList<String> getListString(String key) {
        return new ArrayList<>(Arrays.asList(TextUtils.split(preferences.getString(key, ""), "‚‗‚")));
    }

    /**
     * 获取boolean
     *
     * @param key SharedPreferences key
     */
    public boolean getBoolean(String key) {
        return preferences.getBoolean(key, false);
    }

    /**
     * 解析获取boolean类型的ArrayList
     *
     * @param key SharedPreferences key
     * @return ArrayList of Boolean
     */
    public ArrayList<Boolean> getListBoolean(String key) {
        ArrayList<String> myList = getListString(key);
        ArrayList<Boolean> newList = new ArrayList<>();

        for (String item : myList) {
            if (item.equals("true")) {
                newList.add(true);
            } else {
                newList.add(false);
            }
        }

        return newList;
    }

    /**
     * 获取object类型的Arraylist(使用此方达需先添加gson依赖库)
     */

//    public ArrayList<Object> getListObject(String key, Class<?> mClass){
//    	Gson gson = new Gson();
//
//    	ArrayList<String> objStrings = getListString(key);
//    	ArrayList<Object> objects =  new ArrayList<Object>();
//
//    	for(String jObjString : objStrings){
//    		Object value  = gson.fromJson(jObjString,  mClass);
//    		objects.add(value);
//    	}
//    	return objects;
//    }

    /**
     * 获取object(使用此方达需先添加gson依赖库)
     */

//    public  Object getObject(String key, Class<?> classOfT){
//
//        String json = getString(key);
//        Object value = new Gson().fromJson(json, classOfT);
//        if (value == null)
//            throw new NullPointerException();
//        return value;
//    }


    /**
     * 存放方法
     * putInt,putListInt,putLong,putFloat,putDouble,putListDouble
     * putString,putListString,putBoolean,putListBoolean
     * putObject,putObjectList
     */

    /**
     * 存放int值
     *
     * @param key   SharedPreferences key
     * @param value int value to be added
     */
    public void putInt(String key, int value) {
        checkForNullKey(key);
        preferences.edit().putInt(key, value).apply();
    }

    /**
     * 存放int类型的ArrayList
     *
     * @param key     SharedPreferences key
     * @param intList ArrayList of Integer to be added
     */
    public void putListInt(String key, ArrayList<Integer> intList) {
        checkForNullKey(key);
        Integer[] myIntList = intList.toArray(new Integer[intList.size()]);
        preferences.edit().putString(key, TextUtils.join("‚‗‚", myIntList)).apply();
    }

    /**
     * 存放long值
     *
     * @param key   SharedPreferences key
     * @param value long value to be added
     */
    public void putLong(String key, long value) {
        checkForNullKey(key);
        preferences.edit().putLong(key, value).apply();
    }

    /**
     * 存放float值
     *
     * @param key   SharedPreferences key
     * @param value float value to be added
     */
    public void putFloat(String key, float value) {
        checkForNullKey(key);
        preferences.edit().putFloat(key, value).apply();
    }

    /**
     * 存放double值
     *
     * @param key   SharedPreferences key
     * @param value double value to be added
     */
    public void putDouble(String key, double value) {
        checkForNullKey(key);
        putString(key, String.valueOf(value));
    }

    /**
     * 存放double类型的ArrayList值
     *
     * @param key        SharedPreferences key
     * @param doubleList ArrayList of Double to be added
     */
    public void putListDouble(String key, ArrayList<Double> doubleList) {
        checkForNullKey(key);
        Double[] myDoubleList = doubleList.toArray(new Double[doubleList.size()]);
        preferences.edit().putString(key, TextUtils.join("‚‗‚", myDoubleList)).apply();
    }

    /**
     * 存放string
     *
     * @param key   SharedPreferences key
     * @param value String value to be added
     */
    public void putString(String key, String value) {
        checkForNullKey(key);
        preferences.edit().putString(key, value).apply();
    }

    /**
     * 存放string类型的ArrayList
     *
     * @param key        SharedPreferences key
     * @param stringList ArrayList of String to be added
     */
    public void putListString(String key, ArrayList<String> stringList) {
        checkForNullKey(key);
        String[] myStringList = stringList.toArray(new String[stringList.size()]);
        preferences.edit().putString(key, TextUtils.join("‚‗‚", myStringList)).apply();
    }

    /**
     * 存放boolean值
     *
     * @param key   SharedPreferences key
     * @param value boolean value to be added
     */
    public void putBoolean(String key, boolean value) {
        checkForNullKey(key);
        preferences.edit().putBoolean(key, value).apply();
    }

    /**
     * 存放boolean类型的ArrayList
     *
     * @param key      SharedPreferences key
     * @param boolList ArrayList of Boolean to be added
     */
    public void putListBoolean(String key, ArrayList<Boolean> boolList) {
        checkForNullKey(key);
        ArrayList<String> newList = new ArrayList<>();

        for (Boolean item : boolList) {
            if (item) {
                newList.add("true");
            } else {
                newList.add("false");
            }
        }

        putListString(key, newList);
    }

    /**
     * 存放ObJect，搭配gson使用(使用此方达需先添加gson依赖库)
     * @param key SharedPreferences key
     * @param obj is the Object you want to put
     */
//    public void putObject(String key, Object obj){
//    	checkForNullKey(key);
//    	Gson gson = new Gson();
//    	putString(key, gson.toJson(obj));
//    }
//
//    public void putListObject(String key, ArrayList<Object> objArray){
//    	checkForNullKey(key);
//    	Gson gson = new Gson();
//    	ArrayList<String> objStrings = new ArrayList<String>();
//    	for(Object obj : objArray){
//    		objStrings.add(gson.toJson(obj));
//    	}
//    	putListString(key, objStrings);
//    }

    /**
     * 删除指定'key'的SharedPreferences
     *
     * @param key SharedPreferences key
     */
    public void remove(String key) {
        preferences.edit().remove(key).apply();
    }

    /**
     * 清除SharedPreferences文件
     */
    public void clear() {
        preferences.edit().clear().apply();
    }

    /**
     * 返回SharedPreferences中的所有数据
     *
     * @return a Map representing a list of key/value pairs from SharedPreferences
     */
    public Map<String, ?> getAll() {
        return preferences.getAll();
    }


    /**
     * 注册 SharedPreferences change listener
     *
     * @param listener listener object of OnSharedPreferenceChangeListener
     */
    public void registerOnSharedPreferenceChangeListener(
            SharedPreferences.OnSharedPreferenceChangeListener listener) {

        preferences.registerOnSharedPreferenceChangeListener(listener);
    }

    /**
     * 注销 SharedPreferences change listener
     *
     * @param listener listener object of OnSharedPreferenceChangeListener to be unregistered
     */
    public void unregisterOnSharedPreferenceChangeListener(
            SharedPreferences.OnSharedPreferenceChangeListener listener) {

        preferences.unregisterOnSharedPreferenceChangeListener(listener);
    }


    /**
     * 检查存储是否可以写入
     *
     * @return true if writable, false otherwise
     */
    public static boolean isExternalStorageWritable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * 检查存储是否可以读取
     *
     * @return true if readable, false otherwise
     */
    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();

        return Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }

    /**
     * 检测key是否存在
     * null keys would corrupt the shared pref file and make them unreadable this is a preventive measure
     */
    public void checkForNullKey(String key) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (preferences.contains(key)) {
            Log.v("sputil", "------>the key " + "'" + key + "'" + " already exist");
        } else {
            Log.v("sputil", "------>the key " + "'" + key + "'" + " doesn't exist");
        }
    }

    /**
     * 检测value是否存在
     * null keys would corrupt the shared pref file and make them unreadable this is a preventive measure
     */
    public void checkForNullValue(String value) {
        if (value == null) {
            throw new NullPointerException();
        }
    }
}