package com.coffeit.organizze.model;

import com.coffeit.organizze.config.ConfigFirebase;
import com.coffeit.organizze.helper.DateCustom;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class Financial {
    private String date;
    private String category;
    private String desc;
    private String type;
    private Double value;

    public Financial(){

    }

    public void save(){
        DatabaseReference firebase = ConfigFirebase.getDatabase();
        FirebaseAuth auth = ConfigFirebase.getFirebaseAuth();

        firebase.child("financial")
                .child(auth.getCurrentUser().getUid())
                .child(DateCustom.formatDateMY(this.getDate()))
                .push()
                .setValue(this);

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
