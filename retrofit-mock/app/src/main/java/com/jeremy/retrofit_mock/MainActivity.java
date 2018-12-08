package com.jeremy.retrofit_mock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.jeremy.retrofit_mock.api.DemoApi;
import com.jeremy.retrofit_mock.bean.TestBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static final String HOST = "http://api.mock.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void testLoad(View v) {
        Retrofit retrofit = RetrofitFactory.createMockRetrofit(HOST);
        DemoApi api = retrofit.create(DemoApi.class);
        api.getBean()
                .enqueue(new Callback<TestBean>() {
                    @Override
                    public void onResponse(Call<TestBean> call, Response<TestBean> response) {
                        Toast.makeText(MainActivity.this, response.body().toString(),
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<TestBean> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "onFailure", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
