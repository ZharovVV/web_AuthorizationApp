package accounts;

/**
 *  Профиль пользователя.
 */
public class UserProfile {
    private final String login;
    private final String password;
    private final String email;

    public UserProfile(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    @Deprecated
    public UserProfile(String login) {
        this.login = login;
        this.password = login;
        this.email = login + '@' + login;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
