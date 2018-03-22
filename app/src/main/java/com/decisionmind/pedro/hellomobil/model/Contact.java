package com.decisionmind.pedro.hellomobil.model;

/**
 * Created by pedro on 22/03/18.
 */

public class Contact {

    private String userID;
    private String name;
    private String email;

    public Contact() {
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
