package com.egee.baselibrary.util;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * @Date: 2019/10/9 20:56
 * @Author: YGZ
 * @Description: 剪贴板工具类
 * @Version:
 */
public class ClipboardUtils {

    /**
     * 构造方法
     */
    private ClipboardUtils() {
        throw new UnsupportedOperationException("ClipboardUtils can't be instantiated!");
    }

    /**
     * 复制文本到剪贴板
     *
     * @param context
     * @param text
     */
    public static boolean copyTextToClipboard(Context context, CharSequence text) {
        try {
            // 获取剪贴板管理器
            ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            // 创建普通字符型ClipData
            ClipData clipData = ClipData.newPlainText("text", text);
            // 将ClipData内容放到系统剪贴板里
            cm.setPrimaryClip(clipData);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取剪贴板的文本
     *
     * @param context
     * @return
     */
    public static CharSequence getTextFromClipboard(Context context) {
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData cd = cm.getPrimaryClip();
        if (cd != null && cd.getItemCount() > 0) {
            return cd.getItemAt(0).coerceToText(context);
        }
        return null;
    }

    /**
     * 复制uri到剪贴板
     *
     * @param context
     * @param uri
     */
    public static void copyUriToClipboard(Context context, Uri uri) {
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cm.setPrimaryClip(ClipData.newUri(context.getContentResolver(), "uri", uri));
    }

    /**
     * 获取剪贴板的uri
     *
     * @param context
     * @return
     */
    public static Uri getUriFromClipboard(Context context) {
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData cd = cm.getPrimaryClip();
        if (cd != null && cd.getItemCount() > 0) {
            return cd.getItemAt(0).getUri();
        }
        return null;
    }

    /**
     * 复制意图到剪贴板
     *
     * @param context
     * @param intent
     */
    public static void copyIntentToClipboard(Context context, Intent intent) {
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cm.setPrimaryClip(ClipData.newIntent("intent", intent));
    }

    /**
     * 获取剪贴板的意图
     *
     * @param context
     * @return
     */
    public static Intent getIntent(Context context) {
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData cd = cm.getPrimaryClip();
        if (cd != null && cd.getItemCount() > 0) {
            return cd.getItemAt(0).getIntent();
        }
        return null;
    }

}
