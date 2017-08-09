package com.sir.app.utils;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;


/**
 * 显示或隐藏视图工具类
 * Created by zhuyinan on 2017/6/22.
 * Contact by 445181052@qq.com
 */

public class ShowOrHideUtils {

    /**
     * 显示View-->向右边移入
     *
     * @param activity
     * @param view
     */
    public static void showLeftView(Activity activity, final View view) {
        // 向右边移入
        view.setAnimation(AnimationUtils.makeInAnimation(activity, true));
        view.setVisibility(View.VISIBLE);
    }


    /**
     * 隐藏View<--向左边移出
     *
     * @param activity
     * @param view
     */
    public static void hideLeftView(Activity activity, final View view) {
        // 向左边移出
        view.setAnimation(AnimationUtils.makeOutAnimation(activity, false));
        view.setVisibility(View.INVISIBLE);
    }

    /**
     * 显示View<--向左边移入
     *
     * @param activity
     * @param view
     */
    public static void showRightView(Activity activity, final View view) {
        // 向左边移入
        view.setAnimation(AnimationUtils.makeInAnimation(activity, false));
        view.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏View-->向右边移出
     *
     * @param activity
     * @param view
     */
    public static void hideRightView(Activity activity, final View view) {
        // 向右边移出
        view.setAnimation(AnimationUtils.makeOutAnimation(activity, true));
        view.setVisibility(View.INVISIBLE);
    }

    /**
     * 显示view--向上移入
     *
     * @param view
     */
    public static void showFloorView(View view) {
        view.setAnimation(resetToViewBottom());
        view.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏View--向下移出
     *
     * @param view
     */
    public static void hideFloorView(View view) {
        view.setAnimation(moveToViewBottom());
        view.setVisibility(View.INVISIBLE);

    }

    /**
     * 显示View--向上移入
     *
     * @param view
     */
    public static void showTopView(View view) {
        view.setAnimation(resetToViewTop());
        view.setVisibility(View.VISIBLE);

    }

    /**
     * 隐藏View--向下移出
     *
     * @param view
     */
    public static void hideTopView(View view) {
        view.setAnimation(moveToViewTop());
        view.setVisibility(View.INVISIBLE);
    }


    /**
     * 从控件所在位置移动到控件的上部
     *
     * @return
     */
    public static TranslateAnimation moveToViewTop() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, -1.0f);
        mHiddenAction.setDuration(400);
        return mHiddenAction;
    }

    /**
     * 从控件的上部移动到控件所在位置
     *
     * @return
     */
    public static TranslateAnimation resetToViewTop() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mHiddenAction.setDuration(400);
        return mHiddenAction;
    }


    /**
     * 从控件所在位置移动到控件的底部
     *
     * @return
     */
    public static TranslateAnimation moveToViewBottom() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f);
        mHiddenAction.setDuration(400);
        return mHiddenAction;
    }

    /**
     * 从控件的底部移动到控件所在位置
     *
     * @return
     */
    public static TranslateAnimation resetToViewBottom() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mHiddenAction.setDuration(400);
        return mHiddenAction;
    }
}
