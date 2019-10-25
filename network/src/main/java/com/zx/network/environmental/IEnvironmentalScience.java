package com.zx.network.environmental;

/**
 * 所有{@link com.zx.network.base.NetworkApi}的子类实现该接口
 */
public interface IEnvironmentalScience {


    /**
     * @return 正式环境地址
     */
    String getReleaseBaseUrl();

    /**
     * @return 测试环境地址
     */
    String getDabugBaseUrl();

}
