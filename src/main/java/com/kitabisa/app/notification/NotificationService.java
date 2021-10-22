package com.kitabisa.app.notification;

import com.kitabisa.app.util.ResponseTemplate;

public interface NotificationService {
    /**
     * Process send message
     * message will be send to active vendor
     * check vendor on database table vendor notification ( is active = true )
     *
     * @param phoneNumber
     * @param message
     * @return
     */
    ResponseTemplate send(String phoneNumber, String message)throws Exception;
}
