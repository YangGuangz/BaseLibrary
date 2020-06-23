# BaseLibrary
    一：项目已有三方依赖：
    open_sdk_r6140_lite.jar-QQ

    // appcompat，暂时不要更新版本，1.1.0 don't handle bug with webview in android 5.1.1
    api 'androidx.appcompat:appcompat:1.0.2'
    // support-compat
    api 'androidx.core:core:1.0.2'
    // constraintlayout
    api 'androidx.constraintlayout:constraintlayout:1.1.3'
    // recyclerview
    api 'androidx.recyclerview:recyclerview:1.1.0-beta01'
    // cardview
    api 'androidx.cardview:cardview:1.0.0'
    // fragment
    api 'androidx.fragment:fragment:1.0.0'
    // viewpager
    api 'androidx.viewpager:viewpager:1.0.0'
    // support-v4
    api 'androidx.legacy:legacy-support-v4:1.0.0'
    // support-v13
    api 'androidx.legacy:legacy-support-v13:1.0.0'
    // support-annotations
    api 'androidx.annotation:annotation:1.1.0'

    // design
    api 'com.google.android.material:material:1.1.0-alpha08'
    // gson
    api 'com.google.code.gson:gson:2.8.5'

    // retrofit
    api 'com.squareup.retrofit2:retrofit:2.6.0'
    // Json转换器
    api 'com.squareup.retrofit2:converter-gson:2.4.0'
    // Log拦截器
    api 'com.squareup.okhttp3:logging-interceptor:4.0.1'
    // RxJava适配器
    api 'com.squareup.retrofit2:adapter-rxjava2:2.4.0'
    // rxjava
    api 'io.reactivex.rxjava2:rxjava:2.2.10'
    // rxandroid
    api 'io.reactivex.rxjava2:rxandroid:2.1.1'
    // RxJava生命周期
    api 'com.trello.rxlifecycle3:rxlifecycle-android:3.0.0'

    // fragmentation
    api 'me.yokeyword:fragmentationx:1.0.1'

    // Banner
    api 'com.youth.banner:banner:1.4.10'

    // RecyclerView适配器，不要升级，会有getMultiTypeDelegate报错问题
    api 'com.github.CymChad:BaseRecyclerViewAdapterHelper:2.9.47-androidx'

    // 下拉刷新上拉加载
    api 'com.scwang.smartrefresh:SmartRefreshLayout:1.1.0-x'
    api 'com.scwang.smartrefresh:SmartRefreshHeader:1.1.0-x'

    // 微信（包含统计功能）
    api 'com.tencent.mm.opensdk:wechat-sdk-android-with-mta:+'

    // 屏幕适配
    api 'me.jessyan:autosize:1.2.0'

    // 日志
    api 'com.orhanobut:logger:2.2.0'

    // 运行时权限
    api 'com.github.tbruyelle:rxpermissions:0.10.2'

    // 图表
    api 'com.github.PhilJay:MPAndroidChart:v3.1.0'

    // 友盟
    api 'com.umeng.umsdk:analytics:8.0.0'
    api 'com.umeng.umsdk:common:2.0.0'

    // 日历
    api 'com.haibin:calendarview:3.6.7'

    // 版本更新
    api 'com.github.yjfnypeu:UpdatePlugin:3.1.3'

    // --------------------------------以下依赖项目需要单独依赖-----------------------------------

    // 图片加载
    implementation 'com.github.bumptech.glide:glide:4.9.0'
    // glide兼容
    annotationProcessor 'com.github.bumptech.glide:compiler:4.9.0'
    // glide-transformations
    implementation 'jp.wasabeef:glide-transformations:4.0.0'

    // 事件总线
    implementation 'org.greenrobot:eventbus:3.2.0'
    annotationProcessor "org.greenrobot:eventbus-annotation-processor:3.2.0"

    // Butterknife
    implementation 'com.jakewharton:butterknife:10.1.0'
    annotationProcessor 'com.jakewharton:butterknife-compiler:10.1.0'
    
    二：引入依赖后需要在项目Application中做以下初始化
    private void initLogger() {
        LogUtils.init(BuildConfig.ISDEBUG);
    }
    
    private void initEventBus() {
        EventBusUtils.openIndex(BuildConfig.ISDEBUG, new MyEventBusIndex());
    }

    private void initLoading() {
        LoadingDialog.init(R.style.dialog_style_loading);
    }
