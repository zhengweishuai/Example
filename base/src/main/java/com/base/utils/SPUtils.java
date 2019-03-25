package com.base.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * 用SharedPreferences存储
 */
public class SPUtils {
	private static SharedPreferences sharedPreferences = null;


	/**
	 *
	 * 需在activity或者application中初始化
	 * @param context the context
	 */
	public static void  init(Context context){
		if(sharedPreferences ==null){
			sharedPreferences = context.getSharedPreferences(context.getPackageName(),0);
		}
	}

	/**
	 * Remove preferences.
	 * @param key the key
	 */
	public static void removePreferences(String key) {
		if (key == null) {
			return;
		}

		Editor editor = SPUtils.sharedPreferences.edit();
		editor.remove(key);
		editor.commit();
	}

	/**
	 * Put preferences.
	 * @param key   the key
	 * @param value the value
	 */
	public static synchronized void putPreferences(String key, String value) {
		if (key == null || value == null) {
			return;
		}
		Editor editor = SPUtils.sharedPreferences.edit();
		editor.putString(key, value);
		editor.commit();
	}

	/**
	 * Put preferences.
	 * @param key   the key
	 * @param value the value
	 */
	public static void putPreferences(String key, boolean value) {
		Editor editor = SPUtils.sharedPreferences.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	/**
	 * Put preferences.
	 * @param key   the key
	 * @param value the value
	 */
	public static void putPreferences(String key, int value) {
		Editor editor = SPUtils.sharedPreferences.edit();
		editor.putInt(key, value);
		editor.commit();
	}

	/**
	 * Put preferences.
	 * @param key   the key
	 * @param value the value
	 */
	public static void putPreferences(String key, long value) {
		Editor editor = SPUtils.sharedPreferences.edit();
		editor.putLong(key, value);
		editor.commit();
	}

	/**
	 * Put preferences.
	 * @param key   the key
	 * @param value the value
	 */
	public static void putPreferences(String key, float value) {
		Editor editor = SPUtils.sharedPreferences.edit();
		editor.putFloat(key, value);
		editor.commit();
	}

	/**
	 * Gets preferences.
	 * @param key          the key
	 * @param defaultValue the default value
	 * @return the preferences
	 */
	public static boolean getPreferences(String key, boolean defaultValue) {
		try {
			return SPUtils.sharedPreferences.getBoolean(key, defaultValue);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * Gets preferences.
	 * @param key      the key
	 * @param defValue the def value
	 * @return the preferences
	 */
	public static int getPreferences(String key, int defValue) {
		try {
			return SPUtils.sharedPreferences.getInt(key, defValue);
		} catch (Exception e) {
			return defValue;
		}
	}

	/**
	 * Gets preferences.
	 * @param key      the key
	 * @param defValue the def value
	 * @return the preferences
	 */
	public static String getPreferences(String key, String defValue) {
		try {
			return SPUtils.sharedPreferences.getString(key, defValue);
		} catch (Exception e) {
			return defValue;
		}
	}

	/**
	 * Gets preferences.
	 * @param key      the key
	 * @param defValue the def value
	 * @return the preferences
	 */
	public static long getPreferences(String key, long defValue) {
		try {
			return SPUtils.sharedPreferences.getLong(key, defValue);
		} catch (Exception e) {
			return defValue;
		}
	}

	/**
	 * Gets preferences 1.
	 * @param key      the key
	 * @param defValue the def value
	 * @return the preferences 1
	 */
	public static long getPreferences1(String key, long defValue) {
		try {
			return SPUtils.sharedPreferences.getLong(key, defValue);
		} catch (Exception e) {
			return defValue;
		}
	}

	/**
	 * Gets preferences.
	 * @param key      the key
	 * @param defValue the def value
	 * @return the preferences
	 */
	public static float getPreferences(String key, float defValue) {
		try {
			return SPUtils.sharedPreferences.getFloat(key, defValue);
		} catch (Exception e) {
			return defValue;
		}
	}

	/***
	 * List转String
	 * @param sceneList the scene list
	 * @return string
	 */
	public static String arrayList2String(List sceneList) {
		String sceneListString ="";
		try {
			// 实例化一个ByteArrayOutputStream对象，用来装载压缩后的字节文件。
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			// 然后将得到的字符数据装载到ObjectOutputStream
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
			// writeObject 方法负责写入特定类的对象的状态，以便相应的 readObject 方法可以还原它
			objectOutputStream.writeObject(sceneList);
			// 最后，用Base64.encode将字节文件转换成Base64编码保存在String中
			sceneListString = new String(Base64.encode(byteArrayOutputStream.toByteArray(), Base64.NO_WRAP), Charset.forName("UTF-8"));
			// 关闭objectOutputStream
			objectOutputStream.close();
		} catch(Exception e) {
			sceneListString="";
		}

 		return sceneListString;
	}

	/***
	 * string 转 LIST
	 * @param sceneListString the scene list string
	 * @return list
	 */
	public static List string2ArrayList(String sceneListString) {
		ByteArrayInputStream byteArrayInputStream = null;
		ObjectInputStream objectInputStream = null;
		try {
			byte[] mobileBytes = Base64.decode(sceneListString.getBytes(Charset.forName("UTF-8")), Base64.DEFAULT);
			byteArrayInputStream = new ByteArrayInputStream(mobileBytes);
			objectInputStream = new ObjectInputStream(byteArrayInputStream);
			List sceneList = (ArrayList) objectInputStream.readObject();
			return sceneList;
 		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			try{
				if (byteArrayInputStream!= null){
					byteArrayInputStream.close();
				}
				if (objectInputStream!=null){
					objectInputStream.close();
				}
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		return new ArrayList();
	}

}
