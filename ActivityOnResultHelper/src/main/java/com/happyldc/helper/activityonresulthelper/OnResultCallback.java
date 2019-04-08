package com.happyldc.helper.activityonresulthelper;

import android.content.Intent;

/**
 *
 *@author ldc
 *@created at 2019/4/8 13:06
 */

public interface OnResultCallback {
    void onActivityResult(int requestCode, int resultCode, Intent data);
}
