package com.example.cursando.data;

import com.example.cursando.data.model.LoggedInUser;
import com.example.cursando.data.retrofit.RetrofitConfig;
import com.example.cursando.data.retrofit.service.LoginService;

import java.io.IOException;

import retrofit2.Response;

/**
 * Classe que lida com autenticação com credenciais de logon e recupera informações do usuário.
 * Recebe um user name e e-mail do login repository passa para o retrofit fazer a requisição
 * http para autenticar o aluno na api rest, com as resposta se 200 sucesso retorna usuário logado para
 * o repository caso contrário retorna resultado de erro chama retrofit
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
