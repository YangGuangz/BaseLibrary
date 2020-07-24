package com.ygz.baselibrary.event;


import com.ygz.baselibrary.util.LogUtils;

import java.util.Arrays;

/**
 * @Date: 2020/7/2 22:47
 * @Author: YGZ
 * @Description:
 * @Version:
 */
public class MessageEvent {

    public static final String TAG = "EventBus";

    private int type;
    private Object[] args;

    public MessageEvent(int type) {
        this(type, null);
    }

    public MessageEvent(int type, Object...args) {
        this.type = type;
        this.args = args;
        LogUtils.d(TAG, "发送了事件，" + toString());
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object[] getMsg() {
        return args;
    }

    public void setMsg(Object...args) {
        this.args = args;
    }

    @Override
    public String toString() {
        return "MessageEvent{" +
                "type=" + type +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
