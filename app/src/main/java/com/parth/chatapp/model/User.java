package com.parth.chatapp.model;

public class User {

    public static final String USERNAME = "username";
    public static final String USER_TABLE_NAME = "user_table";
    public static final String USER_TABLE = "CREATE TABLE " + USER_TABLE_NAME + " ("+USERNAME+" TEXT)";

    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
