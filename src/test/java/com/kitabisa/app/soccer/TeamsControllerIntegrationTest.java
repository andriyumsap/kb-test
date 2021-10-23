package com.kitabisa.app.soccer;

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
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestKitabisaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"sms.url.vendor-one=one", "sms.url.vendor-two=two"})
@Sql({ "classpath:soccer-data.sql" })
public class TeamsControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void whenAddTeam_correctDate_returnCode200(){
        HttpHeaders headers = new HttpHeaders();

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String> ();
        map.add ("name", "Real Madrid");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);


        ResponseEntity<String> responseEntity = this.restTemplate
                .postForEntity("/api/soccer/team", request, String.class);

        Gson gson = new Gson();
        DataResponse respBody = gson.fromJson(responseEntity.getBody(), DataResponse.class);

        assertEquals("200", respBody.getCode ().toString());
    }

    @Test
    public void whenAddTeam_emptyData_returnCode201(){
        HttpHeaders headers = new HttpHeaders();

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String> ();
        map.add ("name", " ");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);


        ResponseEntity<String> responseEntity = this.restTemplate
                .postForEntity("/api/soccer/team", request, String.class);

        Gson gson = new Gson();
        DataResponse respBody = gson.fromJson(responseEntity.getBody(), DataResponse.class);

        assertEquals("201", respBody.getCode ().toString());
    }

    @Test
    public void whenGetTeams_returnDateNotEmpty(){
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange("/api/soccer/team",
                HttpMethod.GET, entity, String.class);
        Gson gson = new Gson();
        DataResponse respBody = gson.fromJson(response.getBody(), DataResponse.class);
        assertNotNull(respBody.getData ().toString ());
    }

}
