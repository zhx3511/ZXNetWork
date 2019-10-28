# ZXNetWork
基于RxJava+Retrofit+OkHTTP3.0封装的网络请求框架，支持多域名、多环境、多模块

## 使用方法：
  例子：
  ```java
    NewsNetWorkApi.getInstence().toSubscribe(NewsNetWorkApi.getInstence().serviceApi(NewsServices.class)
                                .gettoutiao("top", "28ae2eb3e23ae6sb4e072c8bcc44f6af5"),
                        new OnHttpCallBack<NewsNetRequestResult<List<Toutiao>>>() {
                            @Override
                            public void onSuccessful(NewsNetRequestResult<List<Toutiao>> response) {
                                Log.d("Tag", response.toString());
                            }

                            @Override
                            public void onFaild(String errorMsg) {
                                Log.d("Tag", errorMsg);

                            }
                        });
  ```
  
