# retrofit-mock
一个用于Retrofit mock response数据的工具
## 为什么要使用retrofit-mock
在Android日常开发中，我们使用retrofit获取接口数据。经常会遇到下面两种情况：
1. Android端开发完成了，后端的接口还没有完成，联调不了，心里好烦...
2. 联调的时候，要验证在某种场景下，界面显示对不对，但是，要模拟出这种场景，真的好难...

retrofit-mock就是为了解决使用retrofit联调的时候各种麻烦，方便的mock出你想要的各种数据。

## 适用场景
适用于retrofit2+okhttp3

## 如何使用retrofit-mock
#### 设置repository引用

```
dependencies {
    implementation 'com.jeremyliao:retrofit-mock:0.1.0'
}
```

#### 初始化
在Application的onCreate方法中初始化：

```java
RetrofitMock.init(this, "mock_demo.json");
```
其中“mock_demo.json”是你定义在Assets中的mock数据文件。
#### 创建mock数据
mock数据是放在Android Assets文件夹（建议放在src/debug/assets中）中的mock_demo.json：
```
{
  "/api/getBean": {
    "code": 200,
    "protocol": "http/1.1",
    "message": "",
    "contentType": "application/json;charset=UTF-8",
    "header": {
      "header1": "a",
      "header2": "b"
    },
    "body": {
      "name": "Jeremy",
      "id": 1
    }
  },
  "/api/testMock": {
    "code": 200,
    "protocol": "http/1.1",
    "message": "test mock",
    "contentType": "application/json;charset=UTF-8",
    "header": {
    },
    "body": {
      "name": "Jeremy",
      "id": 1
    }
  }
}
```
mock数据是json格式，定义每一条mock数据都需要定义其request path和response：

![mock_data](https://github.com/JeremyLiao/retrofit-mock/blob/master/imgs/mock_data.png)

#### 使用mock数据
要使用mock数据，只需要在创建Retrofit实例设置OkHttpClient的时候配置MockInterceptor(new MockInterceptor())即可。例如：

```java
public static Retrofit createMockRetrofit(String host) {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(host)
            .client(new OkHttpClient.Builder()
                    .addInterceptor(new SimpleMockInterceptor(true))
                    .build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
    return retrofit;
}
```
