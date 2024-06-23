package com.oops_project.bits_loco.User;


import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserModel {
    @Id
    private int id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String profilePicture;

    @Column
    private String userType;

    @Column
    private String phoneNumber;

    public UserModel() {}

    public UserModel(int id, String name, String email, String profilePicture, String userType, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.profilePicture = profilePicture;
        this.userType = userType;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
