package com.egee.baselibrary.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
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
public class ImageLoader {

    /**
     * 使用Glide加载图片
     *
     * @param context
     * @param model
     * @param imageView
     */
    public static void load(Context context, Object model, ImageView imageView) {
        DrawableCrossFadeFactory drawableCrossFadeFactory = new DrawableCrossFadeFactory.Builder(300)
                .setCrossFadeEnabled(true)
                .build();
        Glide.with(context)
                .load(model)
                .transition(DrawableTransitionOptions.with(drawableCrossFadeFactory))
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
        DrawableCrossFadeFactory drawableCrossFadeFactory = new DrawableCrossFadeFactory.Builder(300)
                .setCrossFadeEnabled(true)
                .build();
        Glide.with(context)
                .load(model)
                .placeholder(placeholderResId)
                .transition(DrawableTransitionOptions.with(drawableCrossFadeFactory))
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
        DrawableCrossFadeFactory drawableCrossFadeFactory = new DrawableCrossFadeFactory.Builder(300)
                .setCrossFadeEnabled(true)
                .build();
        Glide.with(context)
                .load(model)
                .placeholder(placeholderResId)
                .error(errorResId)
                .transition(DrawableTransitionOptions.with(drawableCrossFadeFactory))
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(imageView);
    }

    /**
     * 使用Glide加载图片，裁剪为圆形
     *
     * @param context
     * @param model
     * @param imageView
     */
    public static void loadCircle(Context context, Object model, ImageView imageView) {
        DrawableCrossFadeFactory drawableCrossFadeFactory = new DrawableCrossFadeFactory.Builder(300)
                .setCrossFadeEnabled(true)
                .build();
        Glide.with(context)
                .load(model)
                .apply(RequestOptions.circleCropTransform())
                .transition(DrawableTransitionOptions.with(drawableCrossFadeFactory))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView);
    }

    /**
     * 加载圆形头像
     *
     * @param context
     * @param model
     * @param imageView
     */
    public static void loadCircleHead(Context context, Object model, ImageView imageView) {
        DrawableCrossFadeFactory drawableCrossFadeFactory = new DrawableCrossFadeFactory.Builder(300)
                .setCrossFadeEnabled(true)
                .build();
        Glide.with(context)
                .load(model)
                .apply(RequestOptions.circleCropTransform())
                .transition(DrawableTransitionOptions.with(drawableCrossFadeFactory))
                .into(imageView);
    }

    /**
     * 使用Glide加载图片，指定加载监听
     *
     * @param context
     * @param model
     * @param imageView
     * @param listener  加载监听
     */
    public static void load(Context context, Object model, ImageView imageView, RequestListener<Drawable> listener) {
        DrawableCrossFadeFactory drawableCrossFadeFactory = new DrawableCrossFadeFactory.Builder(300)
                .setCrossFadeEnabled(true)
                .build();
        Glide.with(context)
                .load(model)
                .listener(listener)
                .transition(DrawableTransitionOptions.with(drawableCrossFadeFactory))
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(imageView);
    }

}
