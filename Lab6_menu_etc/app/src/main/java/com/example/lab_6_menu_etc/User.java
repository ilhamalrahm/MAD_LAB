package com.example.lab_6_menu_etc;

import java.io.Serializable;

public class User implements Serializable {

    private  int id=0;
    private String name;
    private String email;

    public User(int id,String name, String email) {
        this.id=id;
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return name + " (" + email + ")";
    }

//    public static void setId()
//    {
//        id=id-1;
//    }
//    public static int getId()
//    {
//        id=id+1;
//        return id-1;
//    }
}

