package com.barry.baseandroidarchitecture.network;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIManager {

    private String TAG = this.getClass().getSimpleName();

    private EndPoint endPoint;

    private static APIManager INSTANCE = null;

    private Retrofit retrofit;

    private OkHttpClient okHttpClient;

    private APIService apiService;

    // singleton get func
    public synchronized static APIManager getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new APIManager();
        }
        return INSTANCE;
    }

    // private constructor
    private APIManager() {
        // init endpoint
        endPoint = new EndPoint();
        // init okhttp client
        initOkhttp();
        // init retrofit
        initRetrofit();
        // create APIService
        apiService = retrofit.create(APIService.class);
    }

    // create okhttp client
    private void initOkhttp() {
        okHttpClient = new OkHttpClient.Builder()
                .build();
    }

    // create retrofit
    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(endPoint.getBaseurl())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public void getAlbum(APIInterface.GetAlbum getAlbum_callback) {
        Call<ResponseBody> call = apiService.getAlbums();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                try {
                    if (response.code() == 200) {
                        // suc
                        try {
                            getAlbum_callback.onSuc(response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        // error
                        getAlbum_callback.onError("API Error");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // fail
                t.printStackTrace();
                try {
                    getAlbum_callback.onFail("API Fail");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void postAlbums(String par1, String par2, APIInterface.PostAlbum postAlbum_callback)
    {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("param1", par1)
                .addFormDataPart("param2", par2)
                .build();

        Call<ResponseBody> call = apiService.postAlbums(requestBody);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                try {
                    if (response.code() == 201) {
                        // suc
                        try {
                            postAlbum_callback.onSuc(response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        // error
                        Log.e(TAG,String.valueOf(response.code()));
                        postAlbum_callback.onError("API Error");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                try {
                    postAlbum_callback.onFail("API Fail");
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    public interface APIInterface
    {
        interface GetAlbum
        {
            void onSuc(String s);
            void onError(String message);
            void onFail(String message);
        }
        interface PostAlbum
        {
            void onSuc(String s);
            void onError(String message);
            void onFail(String message);
        }
    }
}
