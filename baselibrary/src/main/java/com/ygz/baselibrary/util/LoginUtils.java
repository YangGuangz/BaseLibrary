package com.ygz.baselibrary.util;

/**
 * @Date: 2019/10/19 11:26
 * @Author: YGZ
 * @Description:
 * @Version:
 */
public class LoginUtils {

    private static final String KEY_BEARER_TOKEN = "bearer_token";
    private static final String KEY_TOKEN = "token";

    public static void saveBearerToken(String bearerToken) {
        SpUtils.saveString(ContextUtil.getContext(), KEY_BEARER_TOKEN, bearerToken);
    }

    public static void deleteBearerToken() {
        SpUtils.removeString(ContextUtil.getContext(), KEY_BEARER_TOKEN);
    }

    public static String getBearerToken() {
        return SpUtils.getString(ContextUtil.getContext(), KEY_BEARER_TOKEN);
    }

    public static void saveToken(String token) {
        SpUtils.saveString(ContextUtil.getContext(), KEY_TOKEN, token);
    }

    public static String getToken() {
        return SpUtils.getString(ContextUtil.getContext(), KEY_TOKEN);
    }

    public static void deleteToken() {
        SpUtils.removeString(ContextUtil.getContext(), KEY_TOKEN);
    }

    public static boolean isLogin() {
        return StringUtils.notEmpty(getToken());
    }

    public static boolean notLogin() {
        return !isLogin();
    }

}
