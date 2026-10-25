package com.example.loginpage;

/**
 * Validates user credentials against authentication credentials.
 * Stores authenticated session data for access by profile activities.
 */
public class Userlogin {

    private static String username;
    private static String password;

    private final String validUsername = "admin";
    private final String validPassword = "1234";

    /**
     * Constructs a Userlogin instance with the supplied username and password.
     *
     * @param username the entered username
     * @param password the entered password
     */
    public Userlogin(String username, String password) {
        Userlogin.username = username;
        Userlogin.password = password;
    }

    /**
     * Verifies whether the provided username and password match authorized credentials.
     *
     * @return true if valid credentials, false otherwise
     */
    public boolean isValidLogin() {
        return validUsername.equals(username) && validPassword.equals(password);
    }

    /**
     * Returns the username of the currently logged-in user.
     *
     * @return current username
     */
    public static String getUsername() {
        return username;
    }

    /**
     * Returns the password of the currently logged-in user.
     *
     * @return current password
     */
    public static String getPassword() {
        return password;
    }
}
