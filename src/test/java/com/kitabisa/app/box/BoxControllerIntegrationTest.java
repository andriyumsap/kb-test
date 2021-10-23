package com.kitabisa.app.box;

import com.google.gson.Gson;
import com.kitabisa.app.TestKitabisaApplication;
import com.kitabisa.app.util.DataResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@TestPropertySource(properties = {"sms.url.vendor-one=one", "sms.url.vendor-two=two"})
@SpringBootTest(classes = TestKitabisaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BoxControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void whenProcess_correctData_returnCode200(){
        HttpHeaders headers = new HttpHeaders();

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String> ();
        map.add ("cakes", "25");
        map.add ("apples", "20");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);


        ResponseEntity<String> responseEntity = this.restTemplate
                .postForEntity("/api/box/send", request, String.class);

        Gson gson = new Gson();
        DataResponse respBody = gson.fromJson(responseEntity.getBody(), DataResponse.class);
        assertEquals("200", respBody.getCode ().toString());

        Gson gData = new Gson();
        Packages pkg = gData.fromJson(respBody.getData ().toString(), Packages.class);
        assertEquals(4, pkg.getApples ().intValue ());
        assertEquals(5, pkg.getCakes ().intValue ());
    }

    @Test
    public void testProcess_cakesZero_returnCode201(){
        HttpHeaders headers = new HttpHeaders();

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String> ();
        map.add ("cakes", "0");
        map.add ("apples", "20");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);


        ResponseEntity<String> responseEntity = this.restTemplate
                .postForEntity("/api/box/send", request, String.class);

        Gson gson = new Gson();
        DataResponse respBody = gson.fromJson(responseEntity.getBody(), DataResponse.class);

        assertEquals("201", respBody.getCode ().toString());
    }

    @Test
    public void testProcess_applesZero_returnCode201(){
        HttpHeaders headers = new HttpHeaders();

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String> ();
        map.add ("cakes", "10");
        map.add ("apples", "0");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);


        ResponseEntity<String> responseEntity = this.restTemplate
                .postForEntity("/api/box/send", request, String.class);

        Gson gson = new Gson();
        DataResponse respBody = gson.fromJson(responseEntity.getBody(), DataResponse.class);

        assertEquals("201", respBody.getCode ().toString());
    }
}
