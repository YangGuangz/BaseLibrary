package com.ygz.baselibrary.util;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;

/**
 * @Date: 2019/10/10 16:18
 * @Author: YGZ
 * @Description: 字符串工具类
 * @Version:
 */
public class StringUtils {

    /**
     * 手机号正则表达式：1**********
     * "[1]"代表第1位为数字1，"\\d{10}"代表后面是可以是0～9的数字，有10位。
     */
    private static final String REGEX_CELLPHONE_NUMBER = "[1]\\d{10}";

    /**
     * 用户名正则表达式：
     * ^是正则表达式匹配字符串开始位置，$是正则表达式匹配字符串结束位置；
     * 字母开头，长度3到20位，后面可以跟数字、字母和_。
     */
    private static final String REGEX_USERNAME = "^[a-zA-Z]\\w{3,20}$";

    /**
     * 密码正则表达式：
     * 0-9的数字，3到20位。
     */
    private static final String REGEX_PWD = "^[0-9]{3,20}$";

    /**
     * 验证手机号码格式
     *
     * @param phoneNumber 手机号码
     * @return 是否是手机号
     */
    public static boolean isPhoneNum(String phoneNumber) {
        return notEmpty(phoneNumber) && phoneNumber.matches(REGEX_CELLPHONE_NUMBER);
    }

    /**
     * 验证手机号码格式
     *
     * @param phoneNumber 手机号码
     * @return 是否是手机号
     */
    public static boolean notPhoneNum(String phoneNumber) {
        return !isPhoneNum(phoneNumber);
    }

    /**
     * 校验用户名
     *
     * @param username 用户名
     * @return 是否符合规定
     */
    public static boolean checkUsername(String username) {
        return !TextUtils.isEmpty(username) /*&& username.matches(REGEX_USERNAME)*/;
    }

    /**
     * 检验密码
     *
     * @param pwd 密码
     * @return 是否符合规定
     */
    public static boolean checkPwd(String pwd) {
        return !TextUtils.isEmpty(pwd) /*&& pwd.matches(REGEX_PWD)*/;
    }

    /**
     * 获取字符串首字母大写形式
     *
     * @param str 字符串
     * @return 首字母大写
     */
    public static String getInitial(String str) {
        return isEmpty(str) ? null : str.substring(0, 1).toUpperCase();
    }

    /**
     * 截取字符串str中separator前面的字符串
     *
     * @param str       需要截取的字符串
     * @param separator 分隔符
     * @return 截取后的结果
     */
    public static String substringBefore(String str, String separator) {
        if (isEmpty(str) || isEmpty(separator)) {
            return str;
        }
        int index = str.indexOf(separator);
        return index == -1 ? str : str.substring(0, index);
    }

    /**
     * 截取字符串str中"."前面的字符串
     *
     * @param str 需要截取的字符串
     * @return 截取后的结果
     */
    public static String substringBeforePoint(String str) {
        String separator = ".";
        return substringBefore(str, separator);
    }

    /**
     * 截取字符串str中separator后面的字符串
     *
     * @param str       需要截取的字符串
     * @param separator 分隔符
     * @return 截取后的结果
     */
    public static String substringAfter(String str, String separator) {
        if (isEmpty(str) || isEmpty(separator)) {
            return str;
        }
        int index = str.indexOf(separator);
        return index == -1 ? str : str.substring(index + separator.length());
    }

    /**
     * 根据字符串最大长度截取字符串
     *
     * @param str       需要截取的字符串
     * @param maxLength 最大长度
     * @return 截取后的结果
     */
    public static String substringByLength(String str, int maxLength) {
        if (notEmpty(str) && (str.length() > maxLength))
            return str.substring(0, maxLength);
        return str;
    }

    /**
     * 判断字符串为空 2020/2/21
     *
     * @param s
     * @return
     */
    /*public static boolean isEmpty(String s) {
        if (null == s)
            return true;
        if (s.length() == 0)
            return true;
        if (s.trim().length() == 0)
            return true;
        return false;
    }*/

    /**
     * 判断字符串不为空 2020/2/21
     *
     * @param s
     * @return
     */
    /*public static boolean notEmpty(String s) {
        return !isEmpty(s);
    }*/

    /**
     * 判断字符串为空
     *
     * @param str CharSequence
     * @return
     */
    public static boolean isEmpty(CharSequence str) {
        return TextUtils.isEmpty(str);
    }

    /**
     * 判断字符串不为空
     *
     * @param str
     * @return
     */
    public static boolean notEmpty(CharSequence str) {
        return !TextUtils.isEmpty(str);
    }

    /**
     * 加密手机号码
     *
     * @param phoneNum
     * @return
     */
    public static String encryptPhoneNum(String phoneNum) {
        if (StringUtils.isPhoneNum(phoneNum)) {
            return phoneNum.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        }
        return phoneNum;
    }

    /**
     * 获取WebView的Url，判断是否包含“http://”或者“https://”，不包含的手动添加
     *
     * @param url
     */
    public static String getWebViewUrl(String url) {
        if (StringUtils.notEmpty(url) && !url.startsWith("http://") && !url.startsWith("https://")) {
            return "http://".concat(url);
        }
        return url;
    }

    /**
     * 获取WebView的当前页面Url，url示例：http://xxxx.com/?rightTitle='规则'&rightUrl='http://www.baidu.com'
     *
     * @param url
     */
    public static String getCurrentUrl(String url) {
        try {
            if (StringUtils.notEmpty(url)) {
                int beginIndex = 0;
                int endIndex = url.indexOf("?rightTitle");
                return url.substring(beginIndex, endIndex);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取WebView的Url内的rightTitle，url示例：http://xxxx.com/?rightTitle='规则'&rightUrl='http://www.baidu.com'
     * http://api-test.djddzx.com/newer-tutorials?rightTitle='规则'&rightUrl='http://www.baidu.com'
     *
     * @param url
     */
    public static String getRightTitle(String url) {
        try {
            if (StringUtils.notEmpty(url)) {
                int beginIndex = url.indexOf("rightTitle='") + "rightTitle='".length();
                int endIndex = url.indexOf("'&rightUrl");
                return url.substring(beginIndex, endIndex);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取WebView的Url内的rightUrl，url示例：http://xxxx.com/?rightTitle='规则'&rightUrl='http://www.baidu.com'
     *
     * @param url
     */
    public static String getRightUrl(String url) {
        try {
            if (StringUtils.notEmpty(url)) {
                int beginIndex = url.indexOf("rightUrl='") + "rightUrl='".length();
                int endIndex = url.length() - 1;
                return url.substring(beginIndex, endIndex);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 判断两个字符串是否相同
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean notEquals(CharSequence a, CharSequence b) {
        return !TextUtils.equals(a, b);
    }

    /**
     * 判断我的页面收益金额是否大于0
     *
     * @param income
     * @return
     */
    public static boolean mineIncomeNotEmpty(String income) {
        return StringUtils.notEmpty(income) &&
                StringUtils.notEquals(income, "0") &&
                StringUtils.notEquals(income, "0.0") &&
                StringUtils.notEquals(income, "0.00");
    }

    /**
     * 判断手机号登录注册时的密码是否可用（至少8位）
     *
     * @param password
     * @return
     */
    public static boolean isSignUpPassword(String password) {
        return notEmpty(password) && (password.length() >= 8);
    }

    /**
     * 判断手机号登录注册时的密码是否可用（至少8位）
     *
     * @param password
     * @return
     */
    public static boolean notSignUpPassword(String password) {
        return !isSignUpPassword(password);
    }

    /**
     * 判断字符串长度是否为1
     *
     * @param str
     * @return
     */
    public static boolean isLength1(String str) {
        return StringUtils.notEmpty(str) && (str.length() == 1);
    }

    /**
     * 隐藏第2-4位字符为*
     *
     * @param s
     * @return
     */
    public static String hide2to4String(String s) {
        return s.replace(s.substring(2, 4), "***");
    }

    /**
     * 隐藏第2-5位字符为*
     *
     * @param mobile
     * @return
     */
    public static String hide2to5Mobile(String mobile) {
        return mobile.replace(mobile.substring(2, 5), "****");
    }

    /**
     * 超过多少位后显示省略号,忽略中英文区分
     *
     * @param nickName 昵称
     * @param maxLen   最大字符数
     * @return
     */
    public static CharSequence moreCountEllipsizeToIgnoreCase(String nickName, int maxLen) {
        if (!TextUtils.isEmpty(nickName) && nickName.length() > maxLen) {
            return nickName.substring(0, maxLen) + "...";
        }
        return nickName;
    }

    /**
     * get text by string res id
     *
     * @param context
     * @param resId
     * @return
     */
    public static String getTextByResId(Context context, int resId) {
        String str = "";
        try {
            str = context.getApplicationContext()
                    .getResources()
                    .getText(resId)
                    .toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

}
