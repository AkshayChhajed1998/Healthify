package com.example.workstation.healthify;

public class User {
    private String Uid;
    private String EmailID;
    private String Username;
    private String FirstName;
    private String LastName;

    public User()
    {

    }

    public User(String uid,String emailID, String username, String firstName, String lastName) {
        Uid = uid;
        EmailID = emailID;
        Username = username;
        FirstName = firstName;
        LastName = lastName;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getEmailID() {
        return EmailID;
    }

    public void setEmailID(String emailID) {
        EmailID = emailID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }
}
