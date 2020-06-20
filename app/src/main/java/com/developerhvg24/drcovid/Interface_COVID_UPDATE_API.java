package com.developerhvg24.drcovid;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Interface_COVID_UPDATE_API {

    String BASE_URL_COVID_UPDATE_API = "https://api.apify.com/v2/key-value-stores/";

    @GET("toDWvRj1JpTXiM8FF/records/LATEST?disableRedirect=true")
    Call<Whole_India_Data> getData();
}
