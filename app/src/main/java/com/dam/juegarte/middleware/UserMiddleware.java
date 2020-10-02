package com.dam.juegarte.middleware;


public class UserMiddleware {

    public UserMiddleware() {

    }

    public String validateUser(String username, String password, String passwordConfirm, String email) {

        if (username.length() < 3) {
            return "Please enter a username with at least 3 chars";
        }
        // password min 6 chars
        if (password.length() < 6) {
            return "Please enter a password 6 chars min";
        }
        if (password.length() > 25) {
            return "Please enter a password 25 chars max";
        }
        // password (repeat) does not match
        if (!password.equals(passwordConfirm)) {
            return "Both passwords must match";
        }
        return "";
    }
}
