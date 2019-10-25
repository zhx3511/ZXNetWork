package com.zx.network.base;

import android.app.Application;

/**
 * 初始化：拿到APP配置信息
 */
public interface INetWorkInit {

    /**
     * 获取当前环境，正式and 测试
     *
     * @return
     */
    boolean isDebug();

    /**
     * @return 获取当前APP的版本Name
     */
    String getAppVersionName();

    /**
     * @return 获取当前APP的版本Code
     */
    String getAppVersionCode();

    /**
     * @return 获取当前APP的上下文对象
     */
    Application getApplicationContext();

}
