package com.example.cursando.ui.login;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Handler;
import android.util.Patterns;

import com.example.cursando.data.LoginRepository;
import com.example.cursando.data.Result;
import com.example.cursando.data.model.LoggedInUser;
import com.example.cursando.R;
/**
 * responsável por validar o e-mail e a senha recebe login da login activity propaga para login repository,
 * espera resposta do login repository, depois da resposta define se o usuario está logado ou não
 * */

public class LoginViewModel extends ViewModel {
/**
 * o mutable torna a entidade ‘observável’
 * */
    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private LoginRepository loginRepository;

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login(String username, String password) {

        Handler h = new Handler();
        h.post(() -> {
            Result<LoggedInUser> result = loginRepository.login(username, password);

            if (result instanceof Result.Success) {
                LoggedInUser data = ((Result.Success<LoggedInUser>) result).getData();

                if (data == null) {
                    loginResult.setValue(new LoginResult(R.string.login_failed));
                    return;
                }
                loginResult.setValue(new LoginResult(new LoggedInUserView(data.getEmail())));
            } else {
                loginResult.setValue(new LoginResult(R.string.login_failed));
            }
        });
    }

    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 3;
    }
}
