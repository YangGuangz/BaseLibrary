package com.ygz.baselibrary.net.interceptor;

import androidx.annotation.NonNull;

import com.ygz.baselibrary.util.ContextUtil;
import com.ygz.baselibrary.util.NetworkUtils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Date: 2019/10/18 11:11
 * @Author: YGZ
 * @Description: 缓存拦截器
 * @Version:
 */
public class CacheInterceptor implements Interceptor {

    @NotNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        if (!NetworkUtils.isNetworkConnected(ContextUtil.getContext())) {
            // 没网强制从缓存读取
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }
        // 获取response
        Response originalResponse = chain.proceed(request);
        if (NetworkUtils.isNetworkConnected(ContextUtil.getContext())) {
            // 更新token
            // refreshToken(originalResponse);
            // 有网络时，统一的设置，缓存60s
            int maxAge = 60;
            // 单独设置，接口上的@Headers里的配置，@Headers("Cache-Control: public, max-age=3600")
            // String cacheControl = request.cacheControl().toString();
            return originalResponse.newBuilder()
                    // 移除它的原因是因为pragma也是控制缓存的一个消息头属性
                    .removeHeader("Pragma")
                    // 统一设置
                    .header("Cache-Control", "public ,max-age=" + maxAge)
                    // 单独设置，接口上的@Headers里的配置
                    // .header("Cache-Control", cacheControl)
                    .build();
        } else {
            // 无网络时，缓存4weeks
            int maxStale = 60 * 60 * 24 * 7 * 4;
            return originalResponse.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();
        }
    }

}