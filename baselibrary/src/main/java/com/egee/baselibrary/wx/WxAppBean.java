package com.egee.baselibrary.wx;

/**
 * @Date: 2020/4/13 16:51
 * @Author: YGZ
 * @Description:
 * @Version:
 */
public class WxAppBean {

    private String packageName;
    private String appId;

    public WxAppBean() {
    }

    public WxAppBean(String packageName, String appId) {
        this.packageName = packageName;
        this.appId = appId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}
