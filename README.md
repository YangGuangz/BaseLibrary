# BaseLibrary
    一：三方依赖：
    // --------------------------------以下依赖库需要单独依赖-----------------------------------

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
