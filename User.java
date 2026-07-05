public class User {
    private String username;
    private Library library;

    public User(String usn, Library lib) {
        username = usn;
        library = lib;
    }

    public String getUsername() {
        return username;
    }
}