package com.tencent.debughandler;

import java.util.LinkedList;
import java.util.Queue;

public class RPManager {
    public static Queue<Queue<MotionEventRc>> recodes = new LinkedList<>();
    public static boolean isRecode = false;
    public static boolean isPlay = false;

}
