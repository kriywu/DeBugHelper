package com.tencent.debughandler;

import android.view.MotionEvent;

import androidx.annotation.NonNull;

public class MotionEventRc {


    private MotionEvent event;
    @NonNull
    private long time;
    public MotionEventRc(MotionEvent event, long time){
        this.event = event;
        this.time = time;
    }

    public MotionEvent getEvent() {
        return event;
    }

    public void setEvent(MotionEvent event) {
        this.event = event;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
