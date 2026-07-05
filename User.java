import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private Library library;

    public User(String username) {
        this.username = username;
        this.library =  new Library();
    }

    public String getUsername() {
        return username;
    }

    public Library getLibrary() {
        return library;
    }
}
