package com.barry.baseandroidarchitecture.network;

import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIService {
    @GET("albums/1")    // 設置一個GET連線，路徑為albums/1
    Call<ResponseBody> getAlbums();    // 取得的回傳資料用Albums物件接收，連線名稱取為getAlbums

    @GET("albums/{id}") // 用{}表示路徑參數，@Path會將參數帶入至該位置
    Call<ResponseBody> getAlbumsById(@Path("id") int id);

    @POST("albums") // 用@Body表示要傳送Body資料
    Call<ResponseBody> postAlbums(@Body RequestBody requestBody);
}
