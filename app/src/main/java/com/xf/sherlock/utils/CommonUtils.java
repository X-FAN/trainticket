package com.xf.sherlock.utils;

import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Created by Administrator on 2015/12/27.
 */
public class CommonUtils {
    /**
     * 获取屏幕宽高
     *
     * @param context
     */
    public static DisplayMetrics getDM(Context context) {
        return context.getResources().getDisplayMetrics();
    }

    /**
     * 获取屏幕宽度(单位px)
     *
     * @param context
     * @return
     */
    public static int getWidth(Context context) {
        return getDM(context).widthPixels;
    }

    /**
     * 获取屏幕高度(单位px)
     *
     * @param context
     * @return
     */
    public static int getHeight(Context context) {
        return getDM(context).heightPixels;
    }


    /**
     * dp转换px
     *
     * @param context
     * @param dpVal
     * @return
     */
    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, getDM(context));
    }

    /**
     * 获取手机屏幕分辨率
     *
     * @param context
     * @return
     */
    public static float getDisplayDensity(Context context) {
        return getDM(context).density;
    }

    /**
     * 跳转到指定的界面
     *
     * @param packageContext
     * @param cls
     */
    public static void jump(Context packageContext, Class<?> cls) {
        Intent intent = new Intent(packageContext, cls);
        packageContext.startActivity(intent);
    }

    /**
     * 跳转到指定的界面
     *
     * @param packageContext
     * @param cls
     */
    public static void jump(Intent intent, Context packageContext, Class<?> cls) {
        intent.setClass(packageContext, cls);
        packageContext.startActivity(intent);
    }


}
