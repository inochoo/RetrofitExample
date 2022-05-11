package com.sibaamap.retrofitexample.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sibaamap.retrofitexample.model.Currency;
import com.sibaamap.retrofitexample.model.Post;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiService {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    //Link API : http://apilayer.net/api/live?access_key=843d4d34ae72b3882e3db642c51e28e6&currencies=VND&source=USD&format=1
    //https://jsonplaceholder.typicode.com/posts
    //khoi tao retrofit
    ApiService apiService = new Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);
    //call api
    @GET("api/live")
    Call<Currency> convertUsdToVnd(@Query("access_key") String access_key,
                                   @Query("currencies") String currencies,
                                   @Query("source") String source,
                                   @Query("format") int format);

    @GET("http://apilayer.net/api/live?access_key=843d4d34ae72b3882e3db642c51e28e6&currencies=VND&source=USD&format=1")
    Call<Currency> convertUsdToVnd1();

    @GET("api/live")
    Call<Currency> convertUsdToVnd2(@QueryMap Map<String, String> options);

    @POST("posts")
    Call<Post> sendPost(@Body Post post);




}
