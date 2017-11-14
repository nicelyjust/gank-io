package com.eebbk.geek.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/*
 *  @项目名：  ${PROJECT_NAME}
 *  @包名：    ${PACKAGE_NAME}
 *  @文件名:   ${NAME}
 *  @创建者:   lz
 *  @创建时间:  ${DATE} ${TIME}
 *  @修改时间:  ${USER} ${DATE} ${TIME}
 *  @描述：    TODO
 */
public class TDevice {
	/**
	 * 将px值转换为dip或dp值，保证尺寸大小不变
	 *
	 * @param pxValue
	 *            （DisplayMetrics类中属性density）
	 * @return
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 将dip或dp值转换为px值，保证尺寸大小不变
	 *
	 * @param dipValue
	 *            （DisplayMetrics类中属性density）
	 * @return
	 */
	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	/**
	 * 将px值转换为sp值，保证文字大小不变
	 *
	 * @param pxValue
	 *            （DisplayMetrics类中属性scaledDensity）
	 * @return
	 */
	public static int px2sp(Context context, float pxValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	/**
	 * 将sp值转换为px值，保证文字大小不变
	 *
	 * @param spValue
	 *            （DisplayMetrics类中属性scaledDensity）
	 * @return
	 */
	public static int sp2px(Context context, float spValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}
	/*------------ 获得机器像素以及DPI -------------*/
	public static DisplayMetrics getDisplayMetrics() {
		return UiUtils.getApp().getResources().getDisplayMetrics();
	}

	public static float getScreenHeightPX() {
		return getDisplayMetrics().heightPixels;
	}

	public static float getScreenWidthPX() {
		return getDisplayMetrics().widthPixels;
	}

	public static float getScreenDPI() {
		return getDisplayMetrics().densityDpi;
	}

	public static boolean hasInternet() {
		ConnectivityManager cm = (ConnectivityManager) UiUtils.getApp().getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();
		return info != null && info.isAvailable() && info.isConnected();
	}

	/**
	 * 获得屏幕的宽度
	 * @param context context
	 * @return width
	 */
	public static int getScreenWidth(Context context) {
		WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display       display = manager.getDefaultDisplay();
		return display.getWidth();
	}

	/**
	 * 获得屏幕的高度
	 * @param context context
	 * @return height
	 */
	public static int getScreenHeight(Context context) {
		WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = manager.getDefaultDisplay();
		return display.getHeight();
	}
}
