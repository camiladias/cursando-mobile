package com.example.cursando.data.retrofit.service;

import com.example.cursando.data.model.LoggedInUser;

import retrofit2.Call;
import retrofit2.http.POST;

public interface LoginService {

    @POST("/login")
    Call<LoggedInUser> basicLogin();
}
