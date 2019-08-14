package com.utils;

import android.text.TextUtils;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * 日志输出类<br>
 * init 传入的tag是基础tag  再输出日志时再传入 basetag_tag<br>
 */
public class LogUtil {
    private static String TAG = "LOG--->";

    /**
     * 初始化<br>
     * 使用默认统一 tag
     *
     * @param openDebug the open debug 标识是否输入日志
     */
    public static void init(boolean openDebug) {
        init(openDebug, "");
    }


    /**
     * 初始化<br>
     * 使用自定义统一 tag
     *
     * @param openDebug the open debug 标识是否输入日志
     * @param tag       the tag 自定义tag
     */
    public static void init(final boolean openDebug, String tag) {

        String logTag = TextUtils.isEmpty(tag) ? TAG : tag;

        Logger.clearLogAdapters(); // clear strategy

        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
//                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
//                .methodCount(0)         // (Optional) How many method line to show. Default 2
//                .methodOffset(3)        // (Optional) Hides internal method calls up to offset. Default 5
                .tag(logTag)              // (Optional) Global tag for every log.
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return openDebug;
            }
        });
    }


    /**
     * D.
     *
     * @param message the message
     */
    public static void d(String message) {
        Logger.d(message);
    }

    /**
     * D.
     *
     * @param message the message
     * @param args    the args
     */
    public static void d(String message, Object... args) {
        Logger.d(message, args);
    }

    /**
     * D.
     *
     * @param tag     the tag
     * @param message the message
     */
    public static void d(String tag, String message) {
        Logger.t(tag).d(message);
    }

    /**
     * D.
     *
     * @param tag     the tag
     * @param message the message
     * @param args    the args
     */
    public static void d(String tag, String message, Object... args) {
        Logger.t(tag).d(message, args);
    }


    /**
     * .
     *
     * @param message the message
     */
    public static void i(String message) {
        Logger.d(message);
    }

    /**
     * .
     *
     * @param tag     the tag
     * @param message the message
     */
    public static void i(String tag, String message) {
        Logger.t(tag).d(message);
    }

    /**
     * E.
     *
     * @param message the message
     */
    public static void e(String message) {
        Logger.e(message);
    }

    /**
     * E.
     *
     * @param message the message
     * @param args    the args
     */
    public static void e(String message, Object... args) {
        Logger.e(message, args);
    }

    /**
     * E.
     *
     * @param tag     the tag
     * @param message the message
     */
    public static void e(String tag, String message) {
        Logger.t(tag).e(message);
    }

    /**
     * E.
     *
     * @param tag     the tag
     * @param message the message
     * @param args    the args
     */
    public static void e(String tag, String message, Object... args) {
        Logger.t(tag).e(message, args);
    }

    /**
     * V.
     *
     * @param message the message
     */
    public static void v(String message) {
        Logger.v(message);
    }

    /**
     * V.
     *
     * @param tag     the tag
     * @param message the message
     */
    public static void v(String tag, String message) {
        Logger.t(tag).v(message);
    }

    /**
     * W.
     *
     * @param message the message
     */
    public static void w(String message) {
        Logger.w(message);
    }

    /**
     * W.
     *
     * @param tag     the tag
     * @param message the message
     */
    public static void w(String tag, String message) {
        Logger.t(tag).w(message);
    }

    /**
     * Json.
     *
     * @param json the json
     */
    public static void json(String json) {
        Logger.json(json);
    }

    /**
     * Json.
     *
     * @param tag  the tag
     * @param json the json
     */
    public static void json(String tag, String json) {
        Logger.t(tag).json(json);
    }

    /**
     * Xml.
     *
     * @param xml the xml
     */
    public static void xml(String xml) {
        Logger.xml(xml);
    }

    /**
     * Wtf.
     *
     * @param wtf the wtf
     */
    public static void wtf(String wtf) {
        Logger.wtf(wtf);
    }

    public static String getFormatedJson(String json) {
        if (TextUtils.isEmpty(json)) {
            return "Empty/Null json content";
        }
        try {
            json = json.trim();
            if (json.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(json);
                String message = jsonObject.toString(2);
                return message;
            }
            if (json.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(json);
                String message = jsonArray.toString(2);
                return message;
            }
            return "Invalid Json";
        } catch (JSONException e) {
            return "Invalid Json";
        }
    }

}
