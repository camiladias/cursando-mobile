package com.example.cursando.data;

import com.example.cursando.data.model.LoggedInUser;
import com.example.cursando.data.retrofit.RetrofitConfig;
import com.example.cursando.data.retrofit.service.LoginService;

import java.io.IOException;

import retrofit2.Response;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {

        try {

            LoginService loginService = RetrofitConfig.createService(LoginService.class, username, password);

            Response<LoggedInUser> response = loginService.basicLogin().execute();

            return new Result.Success<>(response.body());
        } catch (Exception e) {
            e.printStackTrace();
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
