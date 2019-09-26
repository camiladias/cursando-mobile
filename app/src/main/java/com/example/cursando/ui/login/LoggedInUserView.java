package  com.example.cursando.ui.login;

/**
 * Classe que contém as informaçoes do usuário autenticado e
 * disponibiliza detalhes do usuário à interface do usuário.
 */
class LoggedInUserView {
    private String displayName;


    LoggedInUserView(String displayName) {
        this.displayName = displayName;
    }

    String getDisplayName() {
        return displayName;
    }
}
