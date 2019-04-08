package com.happyldc.helper.activityonresulthelper;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Random;

/**
 *
 *@author ldc
 *@created at 2019/4/8 13:08
 */

public class ActivityOnResultHelper {
    private static final String TAG = "ActivityOnResultHelper";
    /**
     * Request Code 最大的请求码
     */
    private static final int REQUEST_CODE_MAX = 500;

    private HelperFragment mFragment;

    private ActivityOnResultHelper(Activity activity) {
        mFragment = getOnResultFragment(activity);
    }

    private ActivityOnResultHelper(Fragment fragment) {
        this(fragment.getActivity());
    }

//    private ActivityOnResultHelper(android.support.v4.app.Fragment fragment) {
//        this(fragment.getActivity());
//    }

    public static ActivityOnResultHelper with(Activity activity) {
        return new ActivityOnResultHelper(activity);
    }

    private HelperFragment getOnResultFragment(Activity activity) {
        HelperFragment _helpFragment = findHelpFragment(activity);
        if (_helpFragment == null) {
            _helpFragment = new HelperFragment();
            FragmentManager _fmager = activity.getFragmentManager();
            _fmager.beginTransaction()
                    .add(_helpFragment, TAG)
                    .commitAllowingStateLoss();
            _fmager.executePendingTransactions();
        }
        return _helpFragment;
    }

    private HelperFragment findHelpFragment(Activity activity) {
        return (HelperFragment) activity.getFragmentManager().findFragmentByTag(TAG);
    }

    Intent mIntent = new Intent();
    private int mRequestCode;

    public ActivityOnResultHelper requestCode(int requestCode) {
        mRequestCode = requestCode;
        return this;
    }


    public ActivityOnResultHelper target(Class clz) {
        mIntent.setClass(mFragment.getActivity(), clz);
        return this;
    }

    public ActivityOnResultHelper putExtra(String key, String value) {
        mIntent.putExtra(key, value);
        return this;
    }

    public ActivityOnResultHelper putExtra(String key, int value) {
        mIntent.putExtra(key, value);
        return this;
    }

    public ActivityOnResultHelper putExtra(String key, float value) {
        mIntent.putExtra(key, value);
        return this;
    }

    public ActivityOnResultHelper putExtra(String key, boolean value) {
        mIntent.putExtra(key, value);
        return this;
    }

    public ActivityOnResultHelper putExtra(String key, Serializable value) {
        mIntent.putExtra(key, value);
        return this;
    }

    public ActivityOnResultHelper putExtra(String key, Parcelable value) {
        mIntent.putExtra(key, value);
        return this;
    }

    public ActivityOnResultHelper putExtra(String key, Bundle value) {
        mIntent.putExtra(key, value);
        return this;
    }

    public ActivityOnResultHelper putExtra(Bundle value) {
        mIntent.putExtras(value);
        return this;
    }

    /**
     * 跳转
     *
     * @param callback onActivityResult的回调
     */
    public void startForResult(OnResultCallback callback) {
        if (mIntent.getComponent()==null ) {
            throw new IllegalArgumentException("The target method must be invoked before calling the this method.");
        }
        if (mRequestCode == -1) {
            requestCode(newRequestCode());
        }
        startForResult(mIntent, mRequestCode, callback);
    }

    public void startForResult(Class clz, OnResultCallback callback) {
        startForResult(clz, newRequestCode(), callback);
    }

    public void startForResult(Class clz, int requestCode, OnResultCallback callback) {
        Intent intent = new Intent(mFragment.getActivity(), clz);
        startForResult(intent, requestCode, callback);
    }

    public void startForResult(Intent intent, OnResultCallback callback) {
        startForResult(intent, newRequestCode(), callback);
    }


    public void startForResult(Intent intent, int requestCode, OnResultCallback callback) {
        mFragment.startForResult(intent, requestCode, callback);
    }


    /**
     * 新建一个请求码
     *
     * @return
     */
    private int newRequestCode() {
        //随机取值控制在100-REQUEST_CODE_MAX 之间
        int reqCode = 100 + new Random().nextInt(REQUEST_CODE_MAX - 100);
        //如果请求码被使用则重新生成
        return reqCode;
    }

}
