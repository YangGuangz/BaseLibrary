package com.egee.baselibrary.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import androidx.core.content.FileProvider;

import java.io.File;

/**
 * @Date: 2019/10/10 20:17
 * @Author: YGZ
 * @Description: 7.0及以上版本适配
 * @Version:
 */
public class FileProvider7 {

    public static Uri getUriForFile(Context context, File file) {
        Uri fileUri;
        if (Build.VERSION.SDK_INT >= 24) {
            fileUri = getUriForFile24(context, file);
        } else {
            fileUri = Uri.fromFile(file);
        }
        return fileUri;
    }

    public static Uri getUriForFile24(Context context, File file) {
        return FileProvider.getUriForFile(context, context.getPackageName() + ".android7.fileprovider", file);
    }


    public static void setIntentDataAndType(Context context, Intent intent, String type, File file, boolean writeAble) {
        if (Build.VERSION.SDK_INT >= 24) {
            intent.setDataAndType(getUriForFile(context, file), type);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            if (writeAble) {
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }
        } else {
            intent.setDataAndType(Uri.fromFile(file), type);
        }
    }

}
