package com.egee.baselibrary.util;

/**
 * @Date: 2019/10/10 13:36
 * @Author: YGZ
 * @Description: 
 * @Version:
 */
public class SpannableStringUtils {
    /**
     * 超链接 URLSpan
     * 文字背景颜色 BackgroundColorSpan
     * 文字颜色 ForegroundColorSpan
     * 字体大小 AbsoluteSizeSpan
     * 粗体、斜体 StyleSpan
     * 删除线 StrikethroughSpan
     * 下划线 UnderlineSpan
     * 图片 ImageSpan
     */
    // 获取按钮上的文字
    // SpannableString spannableString = new SpannableString(mWr.gettext().toString().trim());
    // ForegroundColorSpan span = new ForegroundColorSpan(Color.RED);
    /**
     * public void setSpan(Object what, int start, int end, int flags) {
     * 主要是start跟end，start是起始位置，无论中英文，都算一个。
     * 从0开始计算起。end是结束位置，所以处理的文字，包含开始位置，但不包含结束位置。
     */
    // 将倒计时的时间设置为红色
    // spannableString.setSpan(span, 0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
    // mWr.settext(spannableString);

    /*public static String setSize(String str);*/

}
