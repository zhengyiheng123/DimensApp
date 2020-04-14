package com.example.asus.dimensapp.view.picturetag.entity;

import android.support.annotation.IntRange;

/**
 * Created by zyh
 * on 2020/4/14
 */
public class DataEntity {
    public boolean isFirst;
    private int type;//1 正确 2半对 3错误 0未批改

    public int getType() {
        return type;
    }

    public void setType(@IntRange(from = 0, to = 3) int type) {
        this.type = type;
    }
}
