package com.example.loginpage;
//NOT USED, but intended for implementation of profile
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user's profile with their username, email, and a list of characters.
 */
public class Profile {

    // Attributes
    private String username;
    private String email;
    private List<Character> characters; // List of characters associated with the user

    // Constructor
    /**
     * Creates a new Profile with the given username and email.
     * Initializes an empty list of characters.
     *
     * @param username the username for this profile
     * @param email the email address for this profile
     */
    public Profile(String username, String email) {
        this.username = username;
        this.email = email;
        this.characters = new ArrayList<>(); // Initialize the list of characters
    }

    // Methods

    /**
     * Adds a character to the profile's list of characters.
     * @param character the character to add
     */
    public void addCharacter(Character character) {
        characters.add(character);
    }


    /**
     * Replaces an existing character in the list with a new one.
     * If the old character is found, it is updated.
     *
     * @param oldCharacter the character to be replaced
     * @param newCharacter the new character to add
     */
    public void editCharacter(Character oldCharacter, Character newCharacter) {
        int index = characters.indexOf(oldCharacter);
        if (index != -1) {
            characters.set(index, newCharacter); // Replace the old character with the new one
        }
    }

    /**
     * Gets the username of the profile.
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the email of the profile.
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the list of characters linked to this profile.
     *
     * @return a list of characters
     */
    public List<Character> getCharacters() {
        return characters;
    }
}
