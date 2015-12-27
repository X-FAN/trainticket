package com.xf.sherlock.utils;

import android.content.Context;
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


}
