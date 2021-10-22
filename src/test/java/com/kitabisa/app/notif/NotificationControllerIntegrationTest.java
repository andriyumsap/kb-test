package com.kitabisa.app.notif;

import com.google.gson.Gson;
import com.kitabisa.app.TestKitabisaApplication;
import com.kitabisa.app.notification.VendorOptions;
import com.kitabisa.app.util.DataResponse;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.togglz.junit.TogglzRule;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestKitabisaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NotificationControllerIntegrationTest {
    @Rule
    public TogglzRule togglzRule = TogglzRule.allEnabled(VendorOptions.class);

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void whenSendMessage_phoneNumberNotNumber_returnCode201(){
        togglzRule.disable(VendorOptions.VENDOR_ONE);

        HttpHeaders headers = new HttpHeaders();
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String> ();
        map.add ("phoneNumber", "asd");
        map.add ("message", "test");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);


        ResponseEntity<String> responseEntity = this.restTemplate
                .postForEntity("/api/notifications/send", request, String.class);

        Gson gson = new Gson();
        DataResponse respBody = gson.fromJson(responseEntity.getBody(), DataResponse.class);

        assertEquals("201", respBody.getCode ().toString());
    }

    @Test
    public void whenSendMessage_phoneNumberLessThanEleven_returnCode201(){
        togglzRule.disable(VendorOptions.VENDOR_ONE);

        HttpHeaders headers = new HttpHeaders();
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String> ();
        map.add ("phoneNumber", "08991561");
        map.add ("message", "test");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);


        ResponseEntity<String> responseEntity = this.restTemplate
                .postForEntity("/api/notifications/send", request, String.class);

        Gson gson = new Gson();
        DataResponse respBody = gson.fromJson(responseEntity.getBody(), DataResponse.class);

        assertEquals("201", respBody.getCode ().toString());
    }

    @Test
    public void whenSendMessage_messageNullOrEmpty_returnCode202(){
        togglzRule.disable(VendorOptions.VENDOR_ONE);

        HttpHeaders headers = new HttpHeaders();
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String> ();
        map.add ("phoneNumber", "08999999999");
        map.add ("message", " ");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);


        ResponseEntity<String> responseEntity = this.restTemplate
                .postForEntity("/api/notifications/send", request, String.class);

        Gson gson = new Gson();
        DataResponse respBody = gson.fromJson(responseEntity.getBody(), DataResponse.class);

        assertEquals("202", respBody.getCode ().toString());
    }

    @Test
    public void whenSendMessage_phoneNumberAndMessageValid_returnCode200(){
        HttpHeaders headers = new HttpHeaders();

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String> ();
        map.add ("phoneNumber", "08999999999");
        map.add ("message", "test message");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);


        ResponseEntity<String> responseEntity = this.restTemplate
                .postForEntity("/api/notifications/send", request, String.class);

        Gson gson = new Gson();
        DataResponse respBody = gson.fromJson(responseEntity.getBody(), DataResponse.class);

        assertEquals("200", respBody.getCode ().toString());
    }
}
