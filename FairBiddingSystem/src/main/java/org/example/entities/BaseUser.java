package org.example.entities;

public class BaseUser {
    private String id;
    private String phoneNumber;
    private String emailId;

    public BaseUser(String id, String phoneNumber, String emailId) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.emailId = emailId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
