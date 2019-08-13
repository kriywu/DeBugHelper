package com.tencent.debughandler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.Queue;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_jump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });

        findViewById(R.id.btn_record).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RPManager.isRecode = true;
                RPManager.isPlay = false;
                startTime = System.currentTimeMillis();
                RPManager.recodes.add(motionEventRcQueue);
            }
        });

        findViewById(R.id.btn_play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RPManager.isPlay = true;
                RPManager.isRecode = false;
                play();
            }
        });
    }
}
