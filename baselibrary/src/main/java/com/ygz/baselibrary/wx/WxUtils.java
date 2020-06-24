package com.ygz.baselibrary.wx;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Looper;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelmsg.WXMiniProgramObject;
import com.tencent.mm.opensdk.modelmsg.WXMusicObject;
import com.tencent.mm.opensdk.modelmsg.WXVideoObject;
import com.ygz.baselibrary.R;
import com.ygz.baselibrary.util.ContextUtil;
import com.ygz.baselibrary.util.LogUtils;
import com.ygz.baselibrary.util.ToastUtil;
import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import io.reactivex.schedulers.Schedulers;

/**
 * @Date: 2019/10/13 10:04
 * @Author: YGZ
 * @Description: 微信opensdk相关工具类
 * @Version:
 */
public class WxUtils {

    public static final String TAG = WxUtils.class.getSimpleName();
    // 登录请求所需参数-获取用户个人信息
    public static final String SCOPE_USERINFO = "snsapi_userinfo";
    // 请求类型-登录
    public static final int REQ_LOGIN = ConstantsAPI.COMMAND_SENDAUTH;
    // 请求类型-分享
    public static final int REQ_SHARE = ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX;
    // 分享渠道-微信朋友
    public static final int SHARE_CHANNEL_FRIENDS = SendMessageToWX.Req.WXSceneSession;
    // 分享渠道-微信朋友圈
    public static final int SHARE_CHANNEL_MOMENTS = SendMessageToWX.Req.WXSceneTimeline;
    // 分享渠道-微信收藏
    public static final int SHARE_CHANNEL_FAVORITE = SendMessageToWX.Req.WXSceneFavorite;
    // 微信包名
    public static final String PACKAGE_NAME = "com.tencent.mm";

    private Context mContext;
    // IWXAPI 是第三方app和微信通信的openApi接口
    private IWXAPI mApi;

    /**
     * 获取WxUtils实例
     *
     * @return
     */
    public static WxUtils getInstance(String appId) {
        return new WxUtils(appId);
    }

    public WxUtils(String appId) {
        this.mContext = ContextUtil.getContext();
        mApi = WXAPIFactory.createWXAPI(mContext, appId, true);
        // 将应用的appId注册到微信
        mApi.registerApp(appId);
    }

    /**
     * 获取IWXAPI实例
     *
     * @return
     */
    public IWXAPI getWxApi() {
        return mApi;
    }

    /**
     * 判断设备是否安装微信
     */
    private boolean wxAppNotInstalled() {
        if (mApi.isWXAppInstalled()) {
            return false;
        } else {
            ToastUtil.showToast(mContext, R.string.wechat_not_installed);
            return true;
        }
    }

    /**
     * 判断微信版本是否支持当前分享
     */
    private boolean isCanShare(int scene) {
        if ((scene == SHARE_CHANNEL_FRIENDS) || (scene == SHARE_CHANNEL_MOMENTS && mApi.getWXAppSupportAPI() >= Build.TIMELINE_SUPPORTED_SDK_INT)) {
            return true;
        } else {
            ToastUtil.showToast(mContext, "不支持的微信版本");
            return false;
        }
    }

    /**
     * 生成一个请求的唯一标识
     *
     * @param type
     * @return
     */
    private String buildTransaction(String type) {
        return type == null ?
                String.valueOf(System.currentTimeMillis()) :
                type + System.currentTimeMillis();
    }

    /**
     * 请求微信授权登录
     */
    public void login() {
        // 判断设备是否安装微信app
        if (wxAppNotInstalled()) {
            return;
        }
        // 发送登录请求到微信
        final SendAuth.Req req = new SendAuth.Req();
        // 必须参数，应用授权作用域，如获取用户个人信息则填写snsapi_userinfo
        req.scope = SCOPE_USERINFO;
        // 用于保持请求和回调的状态，授权请求后原样带回给第三方。该参数可用于防止csrf攻击（跨站请求伪造攻击），建议第三方带上该参数，可设置为简单的随机数加session进行校验
        req.state = String.valueOf(System.currentTimeMillis());
        // sendReq是第三方app主动发送消息给微信，发送完成之后会切回到第三方app界面。
        mApi.sendReq(req);
    }

    /**
     * 文字类型分享
     *
     * @param text  长度需大于 0 且不超过 10KB
     * @param scene 发送的目标场景
     */
    public void shareTextToWx(String text, final int scene) {
        // 判断设备是否安装微信
        if (wxAppNotInstalled()) {
            return;
        }

        if (!isCanShare(scene)) {
            return;
        }

        // 初始化一个 WXTextObject 对象，填写分享的文本内容
        WXTextObject textObj = new WXTextObject();
        textObj.text = text;

        // 用 WXTextObject 对象初始化一个 WXMediaMessage 对象
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = textObj;
        msg.description = text;

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("text");
        req.message = msg;
        req.scene = scene;
        // 调用api接口，发送数据到微信
        mApi.sendReq(req);
    }

    /**
     * 网页类型分享
     *
     * @param webpageUrl   网页url，限制长度不超过10KB
     * @param title        网页标题，限制长度不超过512Bytes
     * @param desc         网页描述，限制长度不超过1KB
     * @param thumbDataUrl 缩略图url，限制内容大小不超过32KB
     * @param scene        发送的目标场景
     */
    public void shareWebpageToWx(String webpageUrl, String title, String desc, final String thumbDataUrl, final int scene) {
        // 判断设备是否安装微信
        if (wxAppNotInstalled()) {
            return;
        }

        if (!isCanShare(scene)) {
            return;
        }

        // 初始化一个WXWebpageObject，填写url
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = webpageUrl;

        // 用 WXWebpageObject 对象初始化一个 WXMediaMessage 对象
        final WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = title;
        msg.description = desc;
        LogUtils.d("原始分享缩略图链接 = " + thumbDataUrl);
        // 启动加载分享缩略图线程
        LoadThumbDataThread loadThread = new LoadThumbDataThread(thumbDataUrl, new LoadThumbDataThread.LoadThumbDataListener() {
            @Override
            public void onLoadThumbData(byte[] thumbData) {
                int thumbDataKbSize = thumbData == null ?
                        0 :
                        thumbData.length / 1024;
                // 设置缩略图，未获取到或者大小超过限制（实际byte大小限制为64kb）则不设置，优先保证分享成功
                if (thumbDataKbSize > 0 && thumbDataKbSize <= 32) {
                    msg.thumbData = thumbData;
                }
                // 构造一个Req
                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = buildTransaction("webpage");
                req.message = msg;
                req.scene = scene;

                // 调用api接口，发送数据到微信
                mApi.sendReq(req);
            }
        });
        // 如果要在主线程放入线程池
        if (Looper.getMainLooper() == Looper.myLooper()) {
            Schedulers.io().scheduleDirect(loadThread);
        } else {
            loadThread.run();
        }
    }

    /**
     * 图片类型分享
     *
     * @param sendImgBitmap 发送图片的原图，限制图片大小不超过10MB
     * @param scene         发送的目标场景
     */
    public void shareImageToWx(Bitmap sendImgBitmap, int scene) {
        // 判断设备是否安装微信
        if (wxAppNotInstalled()) {
            return;
        }

        if (!isCanShare(scene)) {
            return;
        }

        // 初始化WXImageObject
        WXImageObject imgObj = new WXImageObject(sendImgBitmap);

        // 初始化WXMediaMessage
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = imgObj;
        // 设置缩略图
        /*Bitmap thumbBmp = Bitmap.createScaledBitmap(sendImgBitmap, THUMB_SIZE, THUMB_SIZE, true);
        sendImgBitmap.recycle();
        msg.thumbData = BitmapUtils.bmpToByteArray(thumbBmp, true);*/

        // 构造一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("img");
        req.message = msg;
        req.scene = scene;

        // 调用api接口，发送数据到微信
        mApi.sendReq(req);
    }

    /**
     * 音乐类型分享
     *
     * @param musicUrl     音频网页的 URL 地址，限制长度不超过 10KB
     *                     // @param musicLowBandUrl 供低带宽环境下使用的音频网页 URL 地址，限制长度不超过 10KB
     * @param title        音乐标题，限制长度不超过512Bytes
     * @param desc         音乐描述，限制长度不超过1KB
     * @param thumbDataUrl 缩略图url，限制内容大小不超过32KB
     * @param scene        发送的目标场景
     */
    public void shareMusicToWx(String musicUrl, /*String musicLowBandUrl,*/ String title, String desc, final String thumbDataUrl, final int scene) {
        // 判断设备是否安装微信
        if (wxAppNotInstalled()) {
            return;
        }

        if (!isCanShare(scene)) {
            return;
        }

        // 初始化一个WXMusicObject，填写url
        WXMusicObject music = new WXMusicObject();
        // musicUrl 和 musicLowBandUrl 不能同时为空
        music.musicUrl = musicUrl;
        // music.musicLowBandUrl = "http://www.qq.com";

        // 用 WXMusicObject 对象初始化一个 WXMediaMessage 对象
        final WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = music;
        msg.title = title;
        msg.description = desc;
        LogUtils.d("原始分享缩略图链接 = " + thumbDataUrl);
        // 启动加载分享缩略图线程
        LoadThumbDataThread loadThread = new LoadThumbDataThread(thumbDataUrl, new LoadThumbDataThread.LoadThumbDataListener() {
            @Override
            public void onLoadThumbData(byte[] thumbData) {
                int thumbDataKbSize = thumbData == null ?
                        0 :
                        thumbData.length / 1024;
                // 设置缩略图，未获取到或者大小超过限制（实际byte大小限制为64kb）则不设置，优先保证分享成功
                if (thumbDataKbSize > 0 && thumbDataKbSize <= 32) {
                    msg.thumbData = thumbData;
                }
                // 构造一个Req
                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = buildTransaction("music");
                req.message = msg;
                req.scene = scene;
                // req.userOpenId = getOpenId();

                // 调用api接口，发送数据到微信
                mApi.sendReq(req);
            }
        });
        // 如果要在主线程放入线程池
        if (Looper.getMainLooper() == Looper.myLooper()) {
            Schedulers.io().scheduleDirect(loadThread);
        } else {
            loadThread.run();
        }
    }

    /**
     * 视频类型分享
     *
     * @param videoUrl     视频链接，限制长度不超过 10KB
     *                     // @param videoLowBandUrl 供低带宽的环境下使用的视频链接，限制长度不超过 10KB
     * @param title        视频标题，限制长度不超过512Bytes
     * @param desc         视频描述，限制长度不超过1KB
     * @param thumbDataUrl 缩略图url，限制内容大小不超过32KB
     * @param scene        发送的目标场景
     */
    public void shareVideoToWx(String videoUrl, /*String videoLowBandUrl,*/ String title, String desc, final String thumbDataUrl, final int scene) {
        // 判断设备是否安装微信
        if (wxAppNotInstalled()) {
            return;
        }

        if (!isCanShare(scene)) {
            return;
        }

        // 初始化一个WXVideoObject，填写url
        WXVideoObject video = new WXVideoObject();
        // videoUrl 和 videoLowBandUrl 不能同时为空
        video.videoUrl = videoUrl;
        // video.videoLowBandUrl = videoLowBandUrl;

        // 用 WXVideoObject 对象初始化一个 WXMediaMessage 对象
        final WXMediaMessage msg = new WXMediaMessage(video);
        msg.title = title;
        msg.description = desc;
        LogUtils.d("原始分享缩略图链接 = " + thumbDataUrl);
        // 启动加载分享缩略图线程
        LoadThumbDataThread loadThread = new LoadThumbDataThread(thumbDataUrl, new LoadThumbDataThread.LoadThumbDataListener() {
            @Override
            public void onLoadThumbData(byte[] thumbData) {
                int thumbDataKbSize = thumbData == null ?
                        0 :
                        thumbData.length / 1024;
                // 设置缩略图，未获取到或者大小超过限制（实际byte大小限制为64kb）则不设置，优先保证分享成功
                if (thumbDataKbSize > 0 && thumbDataKbSize <= 32) {
                    msg.thumbData = thumbData;
                }
                // 构造一个Req
                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = buildTransaction("video");
                req.message = msg;
                req.scene = scene;
                // req.userOpenId = getOpenId();

                // 调用api接口，发送数据到微信
                mApi.sendReq(req);
            }
        });
        // 如果要在主线程放入线程池
        if (Looper.getMainLooper() == Looper.myLooper()) {
            Schedulers.io().scheduleDirect(loadThread);
        } else {
            loadThread.run();
        }
    }

    /**
     * 小程序类型分享
     * 发起分享的 App 与小程序属于同一微信开放平台帐号。 支持分享小程序类型消息至会话，暂不支持分享至朋友圈。 若客户端版本低于 6.5.6 或在 iPad 客户端接收，小程序类型分享将自动转成网页类型分享。开发者必须填写网页链接字段，确保低版本客户端能正常打开网页链接。
     *
     * @param webpageUrl   兼容低版本的网页链接，限制长度不超过 10KB
     * @param userName     小程序的原始 id，小程序原始 ID 获取方法：登录小程序管理后台-设置-基本设置-帐号信息
     * @param path         小程序的 path，小程序页面路径；对于小游戏，可以只传入 query 部分，来实现传参效果，如：传入 "?foo=bar"
     * @param title        小程序消息title
     * @param desc         小程序消息desc
     * @param thumbDataUrl 小程序消息封面图片，小于128k
     *                     // @param miniprogramType 小程序的类型，默认正式版，正式版: WXMiniProgramObject.MINIPTOGRAM_TYPE_RELEASE;测试版: WXMiniProgramObject.MINIPROGRAM_TYPE_TEST;预览版: WXMiniProgramObject.MINIPROGRAM_TYPE_PREVIEW
     *                     // @param withShareTicket 是否使用带 shareTicket 的分享，通常开发者希望分享出去的小程序被二次打开时可以获取到更多信息，例如群的标识。可以设置 withShareTicket 为 true，当分享卡片在群聊中被其他用户打开时，可以获取到 shareTicket，用于获取更多分享信息。详见小程序获取更多分享信息 ，最低客户端版本要求：6.5.13
     */
    public void shareMiniProgramToWx(String webpageUrl, String userName, String path, String title, String desc, final String thumbDataUrl/*int miniprogramType,*//*, boolean withShareTicket*/) {
        // 判断设备是否安装微信
        if (wxAppNotInstalled()) {
            return;
        }

        WXMiniProgramObject miniProgramObj = new WXMiniProgramObject();
        // 兼容低版本的网页链接
        miniProgramObj.webpageUrl = webpageUrl;
        // 正式版:0，测试版:1，体验版:2
        miniProgramObj.miniprogramType = WXMiniProgramObject.MINIPTOGRAM_TYPE_RELEASE;
        // 小程序原始id
        miniProgramObj.userName = userName;
        // 小程序页面路径；对于小游戏，可以只传入 query 部分，来实现传参效果，如：传入 "?foo=bar"
        miniProgramObj.path = path;
        final WXMediaMessage msg = new WXMediaMessage(miniProgramObj);
        // 小程序消息title
        msg.title = title;
        // 小程序消息desc
        msg.description = desc;
        LogUtils.d("原始小程序消息封面图片链接 = " + thumbDataUrl);
        // 启动加载小程序消息封面图片线程，小于128k
        LoadThumbDataThread loadThread = new LoadThumbDataThread(thumbDataUrl, new LoadThumbDataThread.LoadThumbDataListener() {
            @Override
            public void onLoadThumbData(byte[] thumbData) {
                int thumbDataKbSize = thumbData == null ?
                        0 :
                        thumbData.length / 1024;
                // 设置缩略图，未获取到或者大小超过限制（实际byte大小限制为128kb）则不设置，优先保证分享成功
                if (thumbDataKbSize > 0 && thumbDataKbSize <= 128) {
                    msg.thumbData = thumbData;
                }

                // 构造一个Req
                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = buildTransaction("miniProgram");
                req.message = msg;
                req.scene = SendMessageToWX.Req.WXSceneSession;  // 目前只支持会话

                // 调用api接口，发送数据到微信
                mApi.sendReq(req);
            }
        });
        // 如果要在主线程放入线程池
        if (Looper.getMainLooper() == Looper.myLooper()) {
            Schedulers.io().scheduleDirect(loadThread);
        } else {
            loadThread.run();
        }
    }

}
