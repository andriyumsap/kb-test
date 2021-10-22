package com.kitabisa.app.notification;
import com.kitabisa.app.notification.NotificationService;
import com.kitabisa.app.util.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/notifications")
public class NotificationController {
    @Autowired
    private NotificationService notifications;

    @PostMapping(value = "/send")
    public ResponseTemplate create(@RequestParam(value = "phoneNumber") String phoneNumber,
                                   @RequestParam(value = "message") String message)throws Exception {
        return notifications.send (phoneNumber, message);
    }
}
