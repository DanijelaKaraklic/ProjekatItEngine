package com.example.nikolakosmajac.user.model;

/**
 * Created by nikola.kosmajac on 26-Dec-17.
 */

public class User {

    private int idUser;
    private String userName;
    private String password;
    private String name;
    private String surname;
    private String adress;
    private String image;

    public User() {
    }

    public User(int idUser, String userName, String password, String name, String surname, String adress,String image) {
        this.idUser = idUser;
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.adress = adress;
        this.image = image;
    }

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }


    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    @Override
    public String toString() {
        return name + surname;
    }
}
