package com.zx.network.callBack;

/**
 * 回调接口
 *
 * @param <T>
 */
public interface OnHttpCallBack<T> {
    void onSuccessful(T response);//成功了就回调这个方法,可以传递任何形式的数据给调用者

    void onFaild(String errorMsg);//失败了就调用这个方法,可以传递错误的请求消息给调用者
}
