package com.happyldc.helper.onresulthelperdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.happyldc.helper.activityonresulthelper.ActivityOnResultHelper;
import com.happyldc.helper.activityonresulthelper.OnResultCallback;
import com.happyldc.helper.activityonresulthelper.WrapOnResultCallback;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    OnResultCallback resultCallback = new OnResultCallback() {
        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            StringBuilder sb = new StringBuilder();
            sb.append("requestCode=" + requestCode + "\t");
            sb.append("resultCode=" + resultCode + "\t");
            sb.append("intent Data (key test)=" + data.getStringExtra("test"));
            Toast.makeText(MainActivity.this, sb.toString(), Toast.LENGTH_SHORT).show();
        }
    };

    public void test(View view) {
        ActivityOnResultHelper
                .with(this)
                .target(TargetActivity.class)//跳转的页面
                .putExtra("test", "testRequest")//携带参数
                .requestCode(100)//请求码
                .startForResult(new WrapOnResultCallback() {
                    @Override
                    public void onResultOk(int requestCode, Intent data) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("requestCode=" + requestCode + "\t");
                        sb.append("intent Data (key test)=" + data.getStringExtra("test"));
                        Toast.makeText(MainActivity.this, sb.toString(), Toast.LENGTH_SHORT).show();
                    }

                });
//                .startForResult(new Intent(this,TargetActivity.class),100,resultCallback);
//                .startForResult(TargetActivity.class, resultCallback);
    }
}
