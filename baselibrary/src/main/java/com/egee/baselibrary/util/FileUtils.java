package com.egee.baselibrary.util;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @Date: 2019/10/9 14:23
 * @Author: YGZ
 * @Description:
 * @Version:
 */
public class FileUtils {

    /**
     * 获取文件大小单位为B的double值
     */
    private static final int SIZETYPE_B = 1;
    /**
     * 获取文件大小单位为KB的double值
     */
    private static final int SIZETYPE_KB = 2;
    /**
     * 获取文件大小单位为MB的double值
     */
    private static final int SIZETYPE_MB = 3;
    /**
     * 获取文件大小单位为GB的double值
     */
    private static final int SIZETYPE_GB = 4;

    /**
     * 获取应用SD卡缓存目录
     *
     * @param context Context
     * @param type    子目录文件夹类型，如果为空则返回：/storage/emulated/0/Android/data/app_package_name/cache，否则返回对应类型的文件夹如：/storage/emulated/0/Android/data/app_package_name/files/Pictures。
     * @return 缓存目录文件夹或null（无SD卡或SD卡挂载失败）
     */
    private static File getExternalCacheDirectory(Context context, String type) {
        File appCacheDir = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            if (TextUtils.isEmpty(type)) {
                // 对应清除缓存
                appCacheDir = context.getExternalCacheDir();
            } else {
                // 对应清除数据
                appCacheDir = context.getExternalFilesDir(type);
            }
            // Android 10版本适配
            /*if (appCacheDir == null) {
                // 有些手机需要自定义缓存目录
                appCacheDir = new File(Environment.getExternalStorageDirectory(), "Android/data/" + context.getPackageName() + "/cache/" + type);
            }*/
            if (!appCacheDir.exists() && !appCacheDir.mkdirs()) {
                LogUtils.e("getExternalCacheDirectory fail,the reason is make directory fail!");
            }
        } else {
            LogUtils.e("getExternalCacheDirectory fail,the reason is sdCard nonexistence or sdCard mount fail!");
        }
        return appCacheDir;
    }

    /**
     * 获取/data/data/缓存目录，该方法获取的目录只能供当前应用自己使用，外部应用没有读写权限，如：系统相机应用。
     *
     * @param type 子目录文件夹类型，如果为空则返回：/data/data/app_package_name/cache，否则返回对应类型的文件夹如：/data/data/app_package_name/files/Pictures。
     * @return 缓存目录文件夹或null（创建目录文件失败）
     */
    private static File getInternalCacheDirectory(Context context, String type) {
        File appCacheDir;
        if (TextUtils.isEmpty(type)) {
            appCacheDir = context.getCacheDir();
        } else {
            appCacheDir = new File(context.getFilesDir(), type);
        }
        if (!appCacheDir.exists() && !appCacheDir.mkdirs()) {
            LogUtils.e("getInternalCacheDirectory fail,the reason is make directory fail!");
        }
        return appCacheDir;
    }

    /**
     * 获取应用专属缓存目录，android 4.4及以上不需要申请SD卡读写权限，因此也不用考虑6.0系统动态申请SD卡读写权限问题，并且应用被卸载后缓存会自动清空。
     *
     * @param context Context
     * @param type    文件夹类型，可以为空，为空则返回API得到的一级目录
     * @return 缓存文件夹，如果没有SD卡或SD卡不可用则返回内存缓存目录，否则优先返回SD卡缓存目录
     */
    public static File getCacheDirectory(Context context, String type) {
        File appCacheDir = getExternalCacheDirectory(context, type);
        if (appCacheDir == null) {
            appCacheDir = getInternalCacheDirectory(context, type);
        }
        if (appCacheDir == null) {
            LogUtils.e("getCacheDirectory fail,the reason is unknown exception!");
        } else {
            if (!appCacheDir.exists() && !appCacheDir.mkdirs()) {
                LogUtils.e("getCacheDirectory fail,the reason is make directory fail!");
            }
        }
        return appCacheDir;
    }

    /**
     * 在应用缓存文件夹下生成一个jpg格式的图片文件。
     *
     * @param context 上下文
     * @return 图片路径
     */
    public static File createImageFile(Context context) {
        File image = null;
        // 获取图片保存的文件夹
        File storageDir = getCacheDirectory(context, Environment.DIRECTORY_PICTURES);
        if (storageDir != null) {
            if (storageDir.exists() || storageDir.mkdirs()) {
                // 文件夹不为空并且文件夹存在，或者文件夹不存在但是创建成功
                // 根据时间标记创建图片文件名称
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date());
                String imageFileName = "JPEG_" + timeStamp + "_";
                // 使用给定的前缀和后缀字符串在指定的目录中创建一个新的空文件。
                try {
                    image = File.createTempFile(imageFileName, ".jpg", storageDir);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return image;
    }

    /**
     * 根据Uri获取图片真实路径
     *
     * @param context   Context
     * @param uri       Uri
     * @param selection selection
     * @return path
     */
    private static String getImagePath(Context context, Uri uri, String selection) {
        String path = null;
        // 通过Uri和selection来获取真实的图片路径
        Cursor cursor = null;
        if (uri != null) {
            cursor = context.getContentResolver().query(uri, null, selection, null, null);
        }
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    /**
     * Uri转换为path
     *
     * @param context Context
     * @param uri     Uri
     * @return path
     */
    public static String uriToPath(Context context, Uri uri) {
        String imagePath = null;
        // 判断手机系统版本号
        if (Build.VERSION.SDK_INT <= 19) {
            // 4.4以下系统使用这个方法处理图片
            imagePath = getImagePath(context, uri, null);
        } else {
            // 4.4及以上系统使用这个方法处理图片
            if (DocumentsContract.isDocumentUri(context, uri)) {
                // 如果是document类型的Uri，则通过document id处理
                String docId = DocumentsContract.getDocumentId(uri);
                if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                    // 解析出数字格式的id
                    String id = docId.split(":")[1];
                    String selection = MediaStore.Images.Media._ID + "=" + id;
                    imagePath = getImagePath(context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
                } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                    Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                    imagePath = getImagePath(context, contentUri, null);
                }
            } else if ("content".equalsIgnoreCase(uri.getScheme())) {
                // 如果是content类型的Uri，则使用普通方式处理
                imagePath = getImagePath(context, uri, null);
            } else if ("file".equalsIgnoreCase(uri.getScheme())) {
                // 如果是file类型的Uri，直接获取图片路径即可
                imagePath = uri.getPath();
            }
        }
        return imagePath;
    }

    /**
     * 清除缓存(/storage/emulated/0/Android/data/app_package_name/cache)
     *
     * @param context 上下文
     */
    public static void cleanExternalCache(Context context) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            deleteFilesByDirectory(context.getExternalCacheDir());
        }
    }

    /**
     * 删除文件夹，这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理
     *
     * @param directory 文件夹
     */
    private static void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                item.delete();
            }
        }
    }

    /**
     * 获取文件指定文件的指定单位的大小
     *
     * @param filePath 文件路径
     * @param sizeType 获取大小的类型1为B、2为KB、3为MB、4为GB
     * @return double值的大小
     */
    public static double getFileOrFilesSize(String filePath, int sizeType) {
        File file = new File(filePath);
        long blockSize = 0;
        try {
            if (file.isDirectory()) {
                blockSize = getFileSizes(file);
            } else {
                blockSize = getFileSize(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return FormetFileSize(blockSize, sizeType);
    }

    /**
     * 调用此方法自动计算指定文件或指定文件夹的大小
     *
     * @param filePath 文件路径
     * @return 计算好的带B、KB、MB、GB的字符串
     */
    public static String getAutoFileOrFilesSize(String filePath) {
        File file = new File(filePath);
        long blockSize = 0;
        try {
            if (file.isDirectory()) {
                blockSize = getFileSizes(file);
            } else {
                blockSize = getFileSize(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return FormetFileSize(blockSize);
    }

    /**
     * 获取指定文件夹大小
     *
     * @param file
     * @return
     * @throws Exception
     */
    private static long getFileSizes(File file) throws Exception {
        long size = 0;
        File listFiles[] = file.listFiles();
        for (File listFile : listFiles) {
            if (listFile.isDirectory()) {
                size = size + getFileSizes(listFile);
            } else {
                size = size + getFileSize(listFile);
            }
        }
        return size;
    }

    /**
     * 获取指定文件大小
     *
     * @param file
     * @return
     * @throws Exception
     */
    private static long getFileSize(File file) throws Exception {
        long size = 0;
        if (file.exists()) {
            FileInputStream fis;
            fis = new FileInputStream(file);
            size = fis.available();
            fis.close();
        }
        return size;
    }

    /**
     * 转换文件大小
     *
     * @param fileSize
     * @return
     */
    private static String FormetFileSize(long fileSize) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString;
        String wrongSize = "0B";
        if (fileSize == 0) {
            return wrongSize;
        }
        if (fileSize < 1024) {
            fileSizeString = df.format((double) fileSize) + "B";
        } else if (fileSize < 1048576) {
            fileSizeString = df.format((double) fileSize / 1024) + "KB";
        } else if (fileSize < 1073741824) {
            fileSizeString = df.format((double) fileSize / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileSize / 1073741824) + "GB";
        }
        return fileSizeString;
    }

    /**
     * 转换文件大小,指定转换的类型
     *
     * @param fileS
     * @param sizeType
     * @return
     */
    private static double FormetFileSize(long fileS, int sizeType) {
        DecimalFormat df = new DecimalFormat("#.00");
        double fileSizeLong = 0;
        switch (sizeType) {
            case SIZETYPE_B:
                fileSizeLong = Double.valueOf(df.format((double) fileS));
                break;
            case SIZETYPE_KB:
                fileSizeLong = Double.valueOf(df.format((double) fileS / 1024));
                break;
            case SIZETYPE_MB:
                fileSizeLong = Double.valueOf(df.format((double) fileS / 1048576));
                break;
            case SIZETYPE_GB:
                fileSizeLong = Double.valueOf(df.format((double) fileS / 1073741824));
                break;
            default:
                break;
        }
        return fileSizeLong;
    }

}
