package com.kitabisa.app.notification;

public abstract class Vendor {
    String phoneNumber;
    String password;

    abstract void send(String phoneNumber, String message);
}
