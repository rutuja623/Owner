package com.example.owner;

public class SingletonEdit {
    private static SingletonEdit instance;
    private String fullname , email , phone_no;

    public SingletonEdit() {
    }
    public static SingletonEdit getInstance(){
        if(instance == null){
            instance = new SingletonEdit();
        }
        return instance;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone_no() {
        return phone_no;
    }



}

