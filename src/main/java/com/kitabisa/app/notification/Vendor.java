package com.kitabisa.app.notification;

public abstract class Vendor {
    String phoneNumber;
    String password;
    public abstract boolean send(String phoneNumber, String message);
}
