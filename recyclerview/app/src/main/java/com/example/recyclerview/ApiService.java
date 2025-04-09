package com.example.recyclerview;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("api/")
    Call<UserResponse> getUsers(@Query("results") int count);
}