package entity;

/**
 * A simple implementation of the User interface.
 */
public class CommonUser implements User {

    private final String name;
    private final String password;
    private final String email;
    private final int userId;

    public CommonUser(String name, String password, String email, int userId) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.userId = userId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public int getUserId() {
        return userId;
    }

}
