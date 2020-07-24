package com.ygz.baselibrary.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;

/**
 * @Date: 2019/7/22 17:12
 * @Author: YGZ
 * @Description: 基于Glide图片加载的二次封装
 * @Version:
 */
public class GlideUtils {

    /**
     * 使用Glide加载图片
     *
     * @param context
     * @param model
     * @param imageView
     */
    public static void load(Context context, Object model, ImageView imageView) {
        Glide.with(context)
                .load(model)
                .transition(DrawableTransitionOptions.with(getDrawableCrossFadeFactory()))
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(imageView);
    }

    /**
     * 使用Glide加载图片，指定加载监听
     *
     * @param context
     * @param model
     * @param listener  加载监听
     * @param imageView
     */
    public static void load(Context context, Object model, RequestListener<Drawable> listener, ImageView imageView) {
        Glide.with(context)
                .load(model)
                .listener(listener)
                .transition(DrawableTransitionOptions.with(getDrawableCrossFadeFactory()))
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(imageView);
    }

    /**
     * 使用Glide加载图片，指定占位图
     *
     * @param context
     * @param model
     * @param placeholderResId 占位图resId
     * @param imageView
     */
    public static void load(Context context, Object model, int placeholderResId, ImageView imageView) {
        Glide.with(context)
                .load(model)
                .placeholder(placeholderResId)
                .transition(DrawableTransitionOptions.with(getDrawableCrossFadeFactory()))
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(imageView);
    }

    /**
     * 使用Glide加载图片，指定占位图、error图
     *
     * @param context
     * @param model
     * @param placeholderResId 占位图
     * @param errorResId       error图
     * @param imageView
     */
    public static void load(Context context, Object model, int placeholderResId, int errorResId, ImageView imageView) {
        Glide.with(context)
                .load(model)
                .placeholder(placeholderResId)
                .error(errorResId)
                .transition(DrawableTransitionOptions.with(getDrawableCrossFadeFactory()))
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(imageView);
    }

    /**
     * 使用Glide加载图片，裁剪为圆形，缓存策略为不缓存
     *
     * @param context
     * @param model
     * @param imageView
     */
    public static void loadCircle(Context context, Object model, ImageView imageView) {
        loadCircle(context, model, DiskCacheStrategy.NONE, imageView);
    }

    /**
     * 使用Glide加载图片，裁剪为圆形
     *
     * @param context
     * @param model
     * @param diskCacheStrategy 缓存策略
     * @param imageView
     */
    public static void loadCircle(Context context, Object model, DiskCacheStrategy diskCacheStrategy, ImageView imageView) {
        Glide.with(context)
                .load(model)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .transition(DrawableTransitionOptions.with(getDrawableCrossFadeFactory()))
                .diskCacheStrategy(diskCacheStrategy)
                .into(imageView);
    }

    /**
     * 使用Glide加载图片，裁剪为圆角
     *
     * @param context
     * @param model
     * @param roundingRadius 圆角角度
     * @param imageView
     */
    public static void loadRound(Context context, Object model, int roundingRadius, ImageView imageView) {
        Glide.with(context)
                .load(model)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(roundingRadius)))
                .transition(DrawableTransitionOptions.with(getDrawableCrossFadeFactory()))
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(imageView);
    }

    /**
     * 获取DrawableCrossFadeFactory
     *
     * @return DrawableCrossFadeFactory
     */
    public static DrawableCrossFadeFactory getDrawableCrossFadeFactory() {
        DrawableCrossFadeFactory drawableCrossFadeFactory = new DrawableCrossFadeFactory.Builder(300)
                .setCrossFadeEnabled(true)
                .build();
        return drawableCrossFadeFactory;
    }

}
