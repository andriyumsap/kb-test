package com.kitabisa.app.notif;

import com.kitabisa.app.notification.NotificationServiceImpl;
import com.kitabisa.app.notification.VendorOptions;
import com.kitabisa.app.util.ResponseTemplate;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.togglz.junit.TogglzRule;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class NotificationServiceTest {
    @Rule
    public TogglzRule togglzRule = TogglzRule.allEnabled(VendorOptions.class);

    @InjectMocks
    private NotificationServiceImpl notificationService;


    @Test
    public void whenSendMessage_phoneNumberIsNotNumeric_shouldReturnCode201() throws Exception {
        ResponseTemplate response = notificationService.send ("xyz", "test");
        assertEquals("201", response.getCode ().toString());
    }

    @Test
    public void whenSendMessage_phoneNumberIsNumericAndLessThan11_shouldReturnCode201() throws Exception {
        ResponseTemplate response = notificationService.send ("0899", "test");
        assertEquals("201", response.getCode ().toString());
    }

    @Test
    public void whenSendMessage_messageNullOrEmpty_shouldReturnCode201() throws Exception {
        ResponseTemplate response = notificationService.send ("08999999999", " ");
        assertEquals("202", response.getCode ().toString());
    }

    @Test
    public void whenSendMessage_vendorOne_shouldReturnCodeSuccess() throws Exception {
        togglzRule.disable(VendorOptions.VENDOR_TWO);
        ResponseTemplate response = notificationService.send ("08999999999", "test");
        assertEquals("200", response.getCode ().toString());
    }

    @Test
    public void whenSendMessage_vendorTwo_shouldReturnCodeSuccess() throws Exception {
        togglzRule.disable(VendorOptions.VENDOR_ONE);
        ResponseTemplate response = notificationService.send ("08999999999", "test");
        assertEquals("200", response.getCode ().toString());
    }

    @Test
    public void whenSendMessage_noActiveVendor_shouldReturnCode203()throws Exception {
        togglzRule.disable(VendorOptions.VENDOR_ONE);
        togglzRule.disable(VendorOptions.VENDOR_TWO);
        ResponseTemplate response = notificationService.send ("08999999999", "test");
        assertEquals("203", response.getCode ().toString());
    }
}
