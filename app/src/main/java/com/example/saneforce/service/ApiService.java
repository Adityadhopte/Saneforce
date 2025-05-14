package com.example.saneforce.service;

import com.example.saneforce.model.Product;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ApiService {

    @GET("server/native_Db_V13.php")
    Call<List<Product>> getProducts(
            @Query("axn") String axn,
            @Query("divisionCode") String divisionCode
    );

    @POST("server/native_Db_V13.php?axn=save/taskproddets&divisionCode=258")
    Call<ResponseBody> saveProductDetails(@Body RequestBody body);

}
