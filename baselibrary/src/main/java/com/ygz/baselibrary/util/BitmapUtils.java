package com.ygz.baselibrary.util;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Date: 2019/10/10 17:09
 * @Author: YGZ
 * @Description: Bitmap工具类
 * @Version:
 */
public class BitmapUtils {

    /**
     * 根据图片路径加载图片为bitmap，指定目标高度和目标宽度对原始图片进行缩放，防止原始图片过大导致oom
     *
     * @param imagePath 图片路径
     * @param targetW   目标宽度
     * @param targetH   目标高度
     * @return 目标bitmap
     */
    public static Bitmap getBitmapFromFile(String imagePath, int targetW, int targetH) {
        // 第一次解析将inJustDecodeBounds设置为true，来获取图片的基本信息
        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        // 让解析方法禁止为bitmap分配内存，返回值也不再是一个Bitmap对象，而是null，但是会返回图片的基本信息（outWidth、outHeight和outMimeType）
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imagePath, bmOptions);

        // 计算图片的缩放值
        int scaleFactor = calculateInSampleSize(bmOptions, targetW, targetH);

        // 使用获取到的scaleFactor值再次解析图片，并且返回一个Bitmap对象
        // Decode the image file into a Bitmap sized to fill the IView
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        return BitmapFactory.decodeFile(imagePath, bmOptions);
    }

    /**
     * 从res中加载bitmap
     *
     * @param res     Resources
     * @param resId   图片资源id
     * @param targetW 目标宽度
     * @param targetH 目标高度
     * @return 目标bitmap
     */
    public static Bitmap getBitmapFromRes(Resources res, int resId, int targetW, int targetH) {
        // 第一次解析将inJustDecodeBounds设置为true，来获取图片的基本信息
        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        // 让解析方法禁止为bitmap分配内存，返回值也不再是一个Bitmap对象，而是null，但是会返回图片的基本信息（outWidth、outHeight和outMimeType）
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, bmOptions);

        // 计算图片的缩放值
        int scaleFactor = calculateInSampleSize(bmOptions, targetW, targetH);

        // 使用获取到的scaleFactor值再次解析图片，并且返回一个Bitmap对象
        // Decode the image file into a Bitmap sized to fill the IView
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        return BitmapFactory.decodeResource(res, resId, bmOptions);
    }

    /**
     * 计算图片的缩放值
     *
     * @param options
     * @param targetW 目标宽度
     * @param targetH 目标高度
     * @return
     */
    private static int calculateInSampleSize(BitmapFactory.Options options, int targetW, int targetH) {
        int scaleFactor = 1;

        int photoW = options.outWidth;
        int photoH = options.outHeight;

        // 计算图片的缩放值
        // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高一定都会大于等于目标的宽和高
        // Determine how much to scale down the image
        if (photoW > targetW || photoH > targetH) {
            // 图片原始宽高 至少有一个大于 目标宽高，缩放值为 原始宽/目标宽、原始高/目标高 中的最小值
            scaleFactor = Math.min(Math.round((float) photoW / (float) targetW), Math.round((float) photoH / (float) targetH));
        }

        return scaleFactor;
    }

    /**
     * 缩放大图防止oom，指定缩放比例
     *
     * @param imagePath   图片路径
     * @param scaleFactor 将图片的长和宽缩小味原来的1 / scaleFactor
     * @return 目标bitmap
     */
    public static Bitmap scaleBitmap(String imagePath, int scaleFactor) {
        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;
        return BitmapFactory.decodeFile(imagePath, bmOptions);
    }

    /**
     * 保存Bitmap为图片文件
     *
     * @param imgFile
     * @param bitmap
     * @param quality 质量参数 1-100
     */
    public static void saveBitmap(File imgFile, Bitmap bitmap, int quality) {
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(imgFile));
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, bos);
            bos.flush();
            bos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 回收Bitmap
     *
     * @param bitmap Bitmap
     */
    public static void recycle(Bitmap bitmap) {
        try {
            if (bitmap != null && !bitmap.isRecycled()) {
                bitmap.recycle();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Bitmap转换成ByteArray并且进行压缩，压缩到不大于maxkb
     *
     * @param bitmap
     * @param maxkbSize
     * @return
     */
    public static byte[] bmpToByteArray(Bitmap bitmap, int maxkbSize) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        LogUtils.d("原始Bitmap大小 = " + baos.toByteArray().length / 1024 + "kb");
        int options = 90;
        while (baos.toByteArray().length > maxkbSize * 1024 && options != 10) {
            // 清空output
            baos.reset();
            // 这里压缩options%，把压缩后的数据存放到output中
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
            options -= 10;
        }
        // 回收bitmap
        bitmap.recycle();
        byte[] result = baos.toByteArray();
        LogUtils.d("压缩后Bitmap大小 = " + result.length / 1024 + "kb");
        /**
         * ByteArrayOutputStream或ByteArrayInputStream是内存读写流，
         * 不同于指向硬盘的流，它内部是使用字节数组读内存的，
         * 这个字节数组是它的成员变量，当这个数组不再使用变成垃圾的时候，
         * Java的垃圾回收机制会将它回收。所以不需要关流。
         */
        try {
            // 关闭baos
            baos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 保存本地图片到相册
     *
     * @param context
     * @param bitmap   资源图片
     * @param fileName 文件名
     * @return
     */
    public static void saveBitmapToPhoto(Context context, Bitmap bitmap, String fileName) {
        // TODO: 2020/4/20
        // 保存图片至指定路径
        File appDir = FileUtils.getCacheDirectory(context, Environment.DIRECTORY_PICTURES);
        if (!appDir.exists()) {
            appDir.mkdirs();
        }
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        /*ToastUtil.showToast(context, R.string.save_image_success);*/
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse(file.getAbsolutePath())));
    }

    /**
     * 给bitmap绘制颜色
     *
     * @param orginBitmap
     * @param color
     * @return
     */
    public static Bitmap setBg4Bitmap(Bitmap orginBitmap, int color) {
        Paint paint = new Paint();
        paint.setColor(color);
        Bitmap bitmap = Bitmap.createBitmap(orginBitmap.getWidth(), orginBitmap.getHeight(), orginBitmap.getConfig());
        Canvas canvas = new Canvas(bitmap);
        canvas.drawRect(0, 0, orginBitmap.getWidth(), orginBitmap.getHeight(), paint);
        canvas.drawBitmap(orginBitmap, 0, 0, paint);
        return bitmap;
    }

}