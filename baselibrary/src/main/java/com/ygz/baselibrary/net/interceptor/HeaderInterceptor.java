package com.ygz.baselibrary.net.interceptor;

import com.ygz.baselibrary.util.LogUtils;
import com.ygz.baselibrary.util.LoginUtils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Date: 2019/10/18 12:04
 * @Author: YGZ
 * @Description: 请求头拦截器
 * @Version:
 */
public class HeaderInterceptor implements Interceptor {

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        // 获取登录成功保存的token
        String token = LoginUtils.getToken();
        LogUtils.d(token);
        Request request = chain.request();
        Request requestBuilder = request.newBuilder()
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Authorization", "Bearer " + token)
                // .addHeader("osType","android")
                // .addHeader("versionCode", "")
                .method(request.method(), request.body())
                .build();
        return chain.proceed(requestBuilder);
    }

}