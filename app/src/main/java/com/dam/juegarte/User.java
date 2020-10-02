package com.dam.juegarte;

import androidx.annotation.NonNull;

public class User {

    public String username;
    public String password;
    public String email;
    public int points;
    public String image;

    public User() {

    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public User(String username, String email, int points, String image) {
        this.username = username;
        this.email = email;
        this.points = points;
        this.image = image;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPoints() {
        return points;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }


    @NonNull
    @Override
    public String toString() {
        return this.username + " " + this.password + " " + this.email + " " + this.points + " " + this.image;
    }
}
