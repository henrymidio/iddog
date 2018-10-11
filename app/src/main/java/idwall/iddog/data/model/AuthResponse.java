package idwall.iddog.data.model;

public class AuthResponse {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AuthResponse(User user) {

        this.user = user;
    }
}
