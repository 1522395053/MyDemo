package com.ssyh.mydemo.test.retrofit.callback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class MyCallBack<T> implements Callback<T> {
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        onSuccess(response.body());
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onFail(call,t);
    }

    public abstract void onSuccess(T t);
    public abstract void onFail(Call<T> call, Throwable t);
}
