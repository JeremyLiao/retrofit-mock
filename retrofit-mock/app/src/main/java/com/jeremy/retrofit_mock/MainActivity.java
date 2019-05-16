package com.jeremy.retrofit_mock;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.jeremy.retrofit_mock.api.DemoApi;
import com.jeremy.retrofit_mock.bean.TestBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static final String HOST = "http://api.mock.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @SuppressLint("CheckResult")
    public void testLoad(View v) {
        Retrofit retrofit = RetrofitFactory.createMockRetrofit(HOST);
        DemoApi api = retrofit.create(DemoApi.class);
        api.getBean()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TestBean>() {
                    @Override
                    public void accept(TestBean testBean) throws Exception {
                        Toast.makeText(MainActivity.this, testBean.toString(),
                                Toast.LENGTH_SHORT).show();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(MainActivity.this, "onFailure: " + throwable, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
