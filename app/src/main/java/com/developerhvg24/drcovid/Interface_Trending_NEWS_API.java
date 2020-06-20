package com.developerhvg24.drcovid;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Interface_Trending_NEWS_API {

    String BASE_URL_NEWS_API = "https://newsapi.org/v2/";

    @GET("top-headlines?q=corona&country=in&apiKey=aa5149f2db03433b9d54054b5d13fc67")
    Call<Trending_News_Data_Main> getNewsData();
}
