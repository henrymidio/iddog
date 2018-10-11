package idwall.iddog.data.model;

public class AuthRequest {
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;

    public AuthRequest(String email) {
        this.email = email;
    }
}
