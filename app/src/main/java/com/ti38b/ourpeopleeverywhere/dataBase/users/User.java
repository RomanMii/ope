package com.ti38b.ourpeopleeverywhere.dataBase.users;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

public class User {
    private int id;
    private String username;
    private String email;

    public User(int id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    @NonNull
    @NotNull
    @Override
    public String toString() {
        return "id:" + id + " username:" + username + " email:" + email;
    }
}
