package com.happyldc.helper.onresulthelperdemo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class TargetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);
     if(   getIntent().hasExtra("test")){
         Toast.makeText(this, getIntent().getStringExtra("test"), Toast.LENGTH_SHORT).show();
     }
    }

    public void onResult_KeyForTest(View view) {
        Intent intent = new Intent();
        intent.putExtra("test", " TestResult");
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
