package com.library.models;

public enum Permission {
    DEVELOPERS_READ("READ"),
    DEVELOPERS_WRITE("WRITE");

    private final String permission;


    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
