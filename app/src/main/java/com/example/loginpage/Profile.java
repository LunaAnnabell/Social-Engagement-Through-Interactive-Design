package com.example.loginpage;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user profile domain model.
 * Encapsulates credentials, contact information, and saved persona characters.
 */
public class Profile {

    private String username;
    private String email;
    private List<Characters> characters;

    /**
     * Creates a new Profile with the given username and email.
     * Initializes an empty list of associated characters.
     *
     * @param username the display username for this profile
     * @param email    the email address associated with the account
     */
    public Profile(String username, String email) {
        this.username = username;
        this.email = email;
        this.characters = new ArrayList<>();
    }

    /**
     * Associates a persona character with this profile.
     *
     * @param character the character persona to add
     */
    public void addCharacter(Characters character) {
        characters.add(character);
    }

    /**
     * Replaces an existing character in the list with an updated persona.
     *
     * @param oldCharacter the character to be replaced
     * @param newCharacter the updated character instance
     */
    public void editCharacter(Characters oldCharacter, Characters newCharacter) {
        int index = characters.indexOf(oldCharacter);
        if (index != -1) {
            characters.set(index, newCharacter);
        }
    }

    /**
     * Gets the profile username.
     *
     * @return username string
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the profile email.
     *
     * @return email string
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the list of persona characters linked to this profile.
     *
     * @return list of Characters
     */
    public List<Characters> getCharacters() {
        return characters;
    }
}
