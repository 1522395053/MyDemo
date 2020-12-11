package com.ssyh.mydemo.test.retrofit;

import com.ssyh.mydemo.test.entity.GZHao;
import com.ssyh.mydemo.test.retrofit.api.WanAndroidApi;
import com.ssyh.mydemo.test.retrofit.callback.MyCallBack;
import com.ssyh.mydemo.test.retrofit.converter.MyGsonConverterFactory;

import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Test {
    public static void main(String[] args){
        retrofit1();
    }

    public static void retrofit1(){

        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url(WanAndroidApi.baseUrl)
                .post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), "{}"))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WanAndroidApi.baseUrl)
                .addConverterFactory(MyGsonConverterFactory.create())
                .build();

        WanAndroidApi wanAndroidApi = retrofit.create(WanAndroidApi.class);


        wanAndroidApi.listGZHaoResponseBody().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    System.out.println("Callback===by ResponseBody"+response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });


        /*wanAndroidApi.listGZHao()
                .enqueue(new Callback<List<GZHao>>() {
                    @Override
                    public void onResponse(Call<List<GZHao>> call, Response<List<GZHao>> response) {
                        System.out.println("Callback==="+response.body().size());
                    }

                    @Override
                    public void onFailure(Call<List<GZHao>> call, Throwable t) {
                        System.out.println("Callback==="+t.getMessage());
                    }
                });*/



        /*wanAndroidApi.listGZHao()
                .enqueue(new MyCallBack<List<GZHao>>() {
                    @Override
                    public void onSuccess(List<GZHao> gzHaos) {
                        System.out.println("Callback===my"+gzHaos.size());
                    }

                    @Override
                    public void onFail(Call<List<GZHao>> call, Throwable t) {
                        System.out.println("Callback===my"+t.getMessage());
                    }
                });*/


        /*wanAndroidApi.test().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody body = response.body();
                try {
                    String string = body.string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });*/
    }
}
