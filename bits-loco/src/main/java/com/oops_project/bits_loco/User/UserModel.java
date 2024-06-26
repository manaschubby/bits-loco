package com.oops_project.bits_loco.User;


import com.oops_project.bits_loco.Utils.Constants.Gender;
import com.oops_project.bits_loco.Utils.Constants.UserTypes;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String profilePicture;

    @Column
    @Enumerated(EnumType.STRING)
    private UserTypes userType;

    @Column
    private String phoneNumber;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;

    public UserModel() {}

    public UserModel(int id, String name, String email, String profilePicture, UserTypes userType, String phoneNumber) {
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

    public UserTypes getUserType() {
        return userType;
    }

    public void setUserType(UserTypes userType) {
        this.userType = userType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
