package com.ygz.baselibrary.wx;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.ygz.baselibrary.util.FileProviderUtils;
import com.ygz.baselibrary.util.PackageUtils;

import java.io.File;

/**
 * @Date: 2020/5/7 15:05
 * @Author: YGZ
 * @Description:
 * @Version:
 */
public class ShareUtils {

    public static final int WX_SCENE_FRIENDS = 0;
    public static final int WX_SCENE_MOMENTS = 1;

    /**
     * 分享文本到微信好友
     */
    public static void shareTextToWxFriends(Context context, String str) {
        shareTextToWx(context, str, WX_SCENE_FRIENDS);
    }

    /**
     * 分享文本到微信朋友圈
     */
    public static void shareTextToWxMomentss(Context context, String str) {
        shareTextToWx(context, str, WX_SCENE_MOMENTS);
    }

    /**
     * 分享文本到微信
     */
    public static void shareTextToWx(Context context, String str, int scene) {
        if (PackageUtils.isWxInstalled(context)) {
            Intent sendIntent = new Intent();
            ComponentName componentName = null;
            if (scene == WX_SCENE_FRIENDS) {
                componentName = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareImgUI");
            } else if (scene == WX_SCENE_MOMENTS) {
                componentName = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI");
            }
            sendIntent.setComponent(componentName);
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, str);
            sendIntent.setType("text/*");
            // sendIntent.putExtra("sms_body", str);
            // sendIntent.putExtra("Kdescription", !TextUtils.isEmpty(str) ? str : "");
            sendIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(sendIntent);
        }
    }

    /**
     * 分享图片到微信好友
     */
    public static void shareImageToWxFriends(Context context, File file) {
        shareImageToWx(context, file, WX_SCENE_FRIENDS);
    }

    /**
     * 分享图片到微信朋友圈
     */
    public static void shareImageToWxMoments(Context context, File file) {
        shareImageToWx(context, file, WX_SCENE_MOMENTS);
    }

    /**
     * 分享图片到微信
     */
    public static void shareImageToWx(Context context, File file, int scene) {
        if (PackageUtils.isWxInstalled(context)) {
            Intent sendIntent = new Intent();
            // 分享精确到微信的页面，朋友圈页面，或者选择好友分享页面
            ComponentName componentName = null;
            if (scene == WX_SCENE_FRIENDS) {
                componentName = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareImgUI");
            } else if (scene == WX_SCENE_MOMENTS) {
                componentName = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI");
            }
            sendIntent.setComponent(componentName);
            // sendIntent.setAction(Intent.ACTION_SEND_MULTIPLE);// 分享多张图片时使用
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.setType("image/*");
            // 添加Uri图片地址--用于添加多张图片
            // ArrayList<Uri> imageUris = new ArrayList<>();
            // sendIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
            if (file != null) {
                if (file.isFile() && file.exists()) {
                    Uri uri = FileProviderUtils.getUriForFile(context, file);
                    sendIntent.putExtra(Intent.EXTRA_STREAM, uri);
                    // sendIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, Uri);
                }
            }
            // 微信现不能进行标题同时分享
            // sendIntent.putExtra("Kdescription", !TextUtils.isEmpty(str) ? str : "");
            sendIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (scene == WX_SCENE_FRIENDS) {
                // context.startActivity(sendIntent);
                context.startActivity(Intent.createChooser(sendIntent, "Share"));
            } else if (scene == WX_SCENE_MOMENTS) {
                context.startActivity(sendIntent);
            }
        }
    }

}
