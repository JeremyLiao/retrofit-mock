# retrofit-mock
一个用于Retrofit mock response数据的工具
## 为什么要使用retrofit-mock
在Android日常开发中，我们使用retrofit获取接口数据。经常会遇到下面两种情况：
1. Android端开发完成了，后端的接口还没有完成，联调不了，心里好烦...
2. 联调的时候，要验证在某种场景下，界面显示对不对，但是，要模拟出这种场景，真的好难...

retrofit-mock就是为了解决使用retrofit联调的时候各种麻烦，方便的mock出你想要的各种数据。

## 适用场景
适用于retrofit2+okhttp3的场景

## 如何使用retrofit-mock
#### 设置repository引用
#### 初始化
在Application的onCreate方法中初始化：

```java
RetrofitMock.init(this, "mock_demo.json");
```
其中“mock_demo.json”是你定义在Assets中的mock数据文件。
#### 创建mock数据
mock数据放在Android Assets文件夹中，可参考[mock_demo.json](https://github.com/JeremyLiao/retrofit-mock/blob/master/retrofit-mock/app/src/main/assets/mock_demo.json)

mock数据是json格式，定义每一条mock数据都需要定义其request path和response，如下图所示：

![mock_data](https://github.com/JeremyLiao/retrofit-mock/blob/master/imgs/mock_data.png)

#### 使用mock数据
要使用mock数据，只需要在创建Retrofit实例设置OkHttpClient的时候addInterceptor(new MockInterceptor())即可。比如在Demo [RetrofitFactory](https://github.com/JeremyLiao/retrofit-mock/blob/master/retrofit-mock/app/src/main/java/com/jeremy/retrofit_mock/RetrofitFactory.java)中，创建了一个返回mock Retrofit的方法：

```java
public static Retrofit createMockRetrofit(String host) {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(host)
            .client(new OkHttpClient.Builder()
                    .addInterceptor(new MockInterceptor())
                    .build())
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    return retrofit;
}
```
