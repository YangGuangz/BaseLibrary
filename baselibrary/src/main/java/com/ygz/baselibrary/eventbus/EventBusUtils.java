package com.ygz.baselibrary.eventbus;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.meta.SubscriberInfoIndex;

/**
 * @Date: 2020/3/13 21:14
 * @Author: YGZ
 * @Description: EventBus事件处理
 * EventBus是为了给已经存在的窗体传递信息，而且订阅者必须要注册且不能被注销了，否则接收不到消息；
 * 给还未创建的窗体传递信息需要用粘性事件。
 * @Version:
 */
public class EventBusUtils {

    private static final String TAG = EventBusUtils.class.getSimpleName();

    /**
     * 控制日志在开发的时候输出,发布的时候不输出，在开发的时候错误崩溃，而发布的时候不崩溃
     * 打开加速
     */
    public static void openIndex(boolean throwSubscriberException, SubscriberInfoIndex index) {
        // 修改默认实现的配置，记住，必须在第一次EventBus.getDefault()之前配置，且只能设置一次。建议在application.onCreate()调用
        EventBus.builder()
                .throwSubscriberException(throwSubscriberException)
                .addIndex(index)
                .installDefaultEventBus();
        // 这样每次获取到默认实例，都是使用Subscriber Index的了，代码得到了精简。
        EventBus eventBus = EventBus.getDefault();
    }

    /**
     * 注册EventBus
     *
     * @param subscriber 订阅者
     */
    public static void register(Object subscriber) {
        if (!EventBus.getDefault().isRegistered(subscriber)) {
            EventBus.getDefault().register(subscriber);
        }
    }

    /**
     * 注消EventBus
     *
     * @param subscriber 订阅者
     */
    public static void unregister(Object subscriber) {
        EventBus.getDefault().unregister(subscriber);
    }

    /**
     * 发布订阅事件
     *
     * @param publisher 发布者
     */
    public static void post(Object publisher) {
        EventBus.getDefault().post(publisher);
    }

    /**
     * 发布粘性订阅事件
     *
     * @param publisher 发布者
     */
    public static void postSticky(Object publisher) {
        EventBus.getDefault().postSticky(publisher);
    }

    /**
     * 移除指定的粘性订阅事件
     *
     * @param eventType 移除的内容
     * @param <T>
     */
    public static <T> void removeStickyEvent(Class<T> eventType) {
        T stickyEvent = EventBus.getDefault().getStickyEvent(eventType);
        if (stickyEvent != null) {
            EventBus.getDefault().removeStickyEvent(stickyEvent);
        }
    }

    /**
     * 取消事件传送 事件取消仅限于ThreadMode.PostThread下才可以使用
     * 不取消事件就会一直存在
     *
     * @param event
     */
    public static void cancelEventDelivery(Object event) {
        EventBus.getDefault().cancelEventDelivery(event);
    }

    /**
     * 移除所有的粘性订阅事件
     */
    public static void removeAllStickyEvents() {
        EventBus.getDefault().removeAllStickyEvents();
    }

}

