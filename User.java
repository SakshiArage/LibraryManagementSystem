public class User {
    private String username;
    private String role;

    public User(String username, String role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() { 
        return username; 
    }
    public String getRole() { 
        return role; 
    }

    @Override
    public String toString() {
        return username + "," + role;
    }

    public static User fromString(String data) {
        String[] parts = data.split(",");
        return new User(parts[0], parts[1]);
    }
}
