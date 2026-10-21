package com.example.loginpage;

/**
 * Creates public class userlogin.
 * It stores a username and password entered by the user and provides
 * a method to validate the credentials against predefined valid values
 */
public class Userlogin {
    /**
     * the username entered by the user
     */
    private static String username;
    /**
     * the password entered by the user
     */
    private static String password;
    /**
     * the predefinbed value for username and password
     */

    private final String validUsername = "admin";
    private final String validPassword = "1234";

    /**
     * Construct a Userlogin object with the specified username and password
     *
     * @param username the username entered by the user
     * @param password the password entered by the user
     */

    public Userlogin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * checks whether the provided username and password match
     * the predefined valid credentials
     *
     * @return true if both username and password is valid, false otherwise
     */
    public boolean isValidLogin() {
        return username.equals(validUsername) && password.equals(validPassword);
    }

    /**
     * Returns the username entered by the user
     *
     * @return the username
     */
    public static String getUsername() {

        return username;
    }

    public static String getPassword() {
        return password;
    }
}