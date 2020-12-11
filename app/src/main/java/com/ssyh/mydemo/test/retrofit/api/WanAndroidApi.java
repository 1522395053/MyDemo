package com.ssyh.mydemo.test.retrofit.api;

import com.ssyh.mydemo.test.entity.GZHao;
import com.ssyh.mydemo.test.entity.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WanAndroidApi {

    String baseUrl = "https://www.wanandroid.com";

    @GET("wxarticle/chapters/json")
    Call<List<GZHao>> listGZHao();


    @GET("wxarticle/chapters/json")
    Call<ResponseBody> listGZHaoResponseBody();


    /**
     *
     * @return
     */
    @GET("")
    Call<ResponseBody> test();

    /**
     *
     * @return
     */
    @GET("")
    Call<? extends User> test2();

    /**
     *
     * @return
     */
    @GET("")
    Call<? super User> test3();
}
