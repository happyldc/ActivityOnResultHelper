package com.happyldc.helper.activityonresulthelper;

import android.app.Activity;
import android.content.Intent;

/**
 * @author ldc
 * @Created at 2019/4/8 13:08.
 */

public abstract class WrapOnResultCallback implements OnResultCallback {
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case Activity.RESULT_OK:
                onResultOk(requestCode, data);
                break;
            case Activity.RESULT_CANCELED:
                onResultCancel(requestCode, data);
                break;
            case Activity.RESULT_FIRST_USER:
                onResultFirstUser(requestCode, data);
                break;
        }
    }

    public abstract void onResultOk(int requestCode, Intent data);

    public void onResultCancel(int requestCode, Intent data) {
    }

    public void onResultFirstUser(int requestCode, Intent data) {
    }

    ;
}
