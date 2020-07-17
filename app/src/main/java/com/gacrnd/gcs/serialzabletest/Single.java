package com.gacrnd.gcs.serialzabletest;

import java.io.Serializable;

public class Single implements Serializable {

    private static final long serialVersionUID = 1L;
    private static boolean flag = false;
    private static Single single;

    private Single() {
        synchronized (Single.class) {
            if (!flag) {
//                flag = true;
            } else {
                throw new RuntimeException("单例模式被侵犯！");
            }
        }
    }

    public static Single getInstance() {
        if (single == null) {
            synchronized (Single.class) {
                if (single == null) {
                    single = new Single();
                }
            }
        }
        return single;
    }

    //如果不实现这个方法，单例序列化会出现两个实例。
    private Object readResolve(){
        return single;
    }
}
