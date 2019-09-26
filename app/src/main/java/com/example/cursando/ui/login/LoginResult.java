package  com.example.cursando.ui.login;

import android.support.annotation.Nullable;

/**
 * Armazena o resultado da autenticação: sucesso (detalhes do usuário) ou mensagem de erro.
 */
class LoginResult {
    @Nullable
    private LoggedInUserView success;
    @Nullable
    private Integer error;

    LoginResult(@Nullable Integer error) {
        this.error = error;
    }

    LoginResult(@Nullable LoggedInUserView success) {
        this.success = success;
    }

    @Nullable
    LoggedInUserView getSuccess() {
        return success;
    }

    @Nullable
    Integer getError() {
        return error;
    }
}
