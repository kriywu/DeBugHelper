package com.tencent.debughandler;

import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.LinkedList;
import java.util.Queue;

public abstract class BaseActivity extends AppCompatActivity{
    protected long startTime = 0;
    protected Queue<MotionEventRc> motionEventRcQueue = new LinkedList<>();
    protected Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startTime = System.currentTimeMillis();
        play();
        if(RPManager.isRecode) RPManager.recodes.add(motionEventRcQueue);


    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        record(ev);
        return super.dispatchTouchEvent(ev);

    }

    @Override
    public void onBackPressed() {
        record(null);
        super.onBackPressed();
    }

    public void record(MotionEvent ev){
        if(!RPManager.isRecode) return;
        RPManager.isPlay = false;

        MotionEventRc motionEventRc;
        if(ev == null) motionEventRc = new MotionEventRc(null, System.currentTimeMillis() - startTime);
        else motionEventRc = new MotionEventRc(MotionEvent.obtain(ev), System.currentTimeMillis() - startTime);
        motionEventRcQueue.offer(motionEventRc);
    }

    public void play(){
        if(!RPManager.isPlay) return;
        RPManager.isRecode = false;

        motionEventRcQueue = RPManager.recodes.poll();
        if(motionEventRcQueue == null) return;

        while(!motionEventRcQueue.isEmpty()){
            final MotionEventRc motionEventRc = motionEventRcQueue.poll();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(motionEventRc.getEvent() == null)
                        BaseActivity.super.onBackPressed();
                    else
                        dispatchTouchEvent(motionEventRc.getEvent());
                }
            }, motionEventRc.getTime());
        }
        if(RPManager.recodes.size() == 0) RPManager.isPlay = false;
    }
}
