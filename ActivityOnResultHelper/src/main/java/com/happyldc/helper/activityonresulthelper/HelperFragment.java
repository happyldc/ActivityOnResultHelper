package com.happyldc.helper.activityonresulthelper;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author ldc
 * @Created at 2018/9/18 14:37.
 */

@SuppressLint("ValidFragment")
public class HelperFragment extends Fragment {
    private Map<Integer, OnResultCallback> mOnActivityResultMap = new HashMap<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void startForResult(Intent intent, OnResultCallback callback) {
        this.startForResult(intent, obtionRequestCode(), callback);
    }

    public void startForResult(Intent intent, int requestCode, OnResultCallback callback) {
        if (!mOnActivityResultMap.containsKey(callback)) {
            mOnActivityResultMap.put(requestCode, callback);
        }
        startActivityForResult(intent, requestCode);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (!mOnActivityResultMap.containsKey(requestCode)) {
            return;
        }
        OnResultCallback callbck = mOnActivityResultMap.remove(requestCode);
        if (callbck != null) {
            callbck.onActivityResult(requestCode, resultCode, data);
        }
    }

    final int REQUEST_CODE_MAX = 500;

    /**
     * 生成一个请求码
     *
     * @return
     */
    private int obtionRequestCode() {
        return genrealCode();
    }

    /**
     * 生成一个未被使用的请求码
     *
     * @return
     */
    private int genrealCode() {
        //随机取值控制在100-REQUEST_CODE_MAX 之间
        int reqCode = 100 + new Random().nextInt(REQUEST_CODE_MAX - 100);
        //如果请求码被使用则重新生成
        if (mOnActivityResultMap.containsKey(reqCode)) {
            return genrealCode();
        } else {
            return reqCode;
        }
    }
}
