package com.example.igor.messanger;

public abstract class ListItem {

    public static final int TYPE_DATE = 0;
    public static final int TYPE_LEFT_MESSAGE = 1;
    public static final int TYPE_RIGHT_MESSAGE = 2;
    public static final int TYPE_LOAD_MORE = 3;

    abstract public int getType();

}
