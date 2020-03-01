package com.coffeit.organizze.model;

import com.coffeit.organizze.config.ConfigFirebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

public class User {

    private String IdUser;
    private String Name;
    private String Email;
    private String Password;

    public User() {
    }

    public void Save(){
        DatabaseReference firebase = ConfigFirebase.getDatabase();
        firebase.child("users").child(this.IdUser).setValue(this);
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    @Exclude
    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    @Exclude
    public String getIdUser() {
        return IdUser;
    }

    public void setIdUser(String idUser) {
        IdUser = idUser;
    }
}
