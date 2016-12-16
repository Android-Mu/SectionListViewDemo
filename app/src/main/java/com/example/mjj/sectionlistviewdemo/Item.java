package com.example.mjj.sectionlistviewdemo;

/**
 * Description：区分级别
 * <p>
 * Created by Mjj on 2016/12/15.
 */

public class Item {

    // 区分项
    public static final int ITEM = 0;
    public static final int SECTION = 1;

    public final int type;
    public final String text;

    public int sectionPosition;
    public int listPosition;

    public Item(int type, String text) {
        this.type = type;
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

}
