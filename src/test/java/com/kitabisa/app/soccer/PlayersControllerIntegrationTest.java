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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestKitabisaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql({ "classpath:soccer-data.sql" })
public class PlayersControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void whenAddPlaye_correctData_returnCode200(){
        HttpHeaders headers = new HttpHeaders();

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String> ();
        map.add ("name", "Ronaldo");
        map.add ("age", "35");
        map.add ("number", "7");
        map.add ("teamId", "1");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);


        ResponseEntity<String> responseEntity = this.restTemplate
                .postForEntity("/api/soccer/player", request, String.class);

        Gson gson = new Gson();
        DataResponse respBody = gson.fromJson(responseEntity.getBody(), DataResponse.class);

        assertEquals("200", respBody.getCode ().toString());
    }

    @Test
    public void whenAddPlayers_EmptyData_returnCode300(){
        HttpHeaders headers = new HttpHeaders();

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String> ();
        map.add ("name", "test");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);


        ResponseEntity<String> responseEntity = this.restTemplate
                .postForEntity("/api/soccer/player", request, String.class);

        Gson gson = new Gson();
        DataResponse respBody = gson.fromJson(responseEntity.getBody(), DataResponse.class);

        assertEquals("300", respBody.getCode ().toString());
    }

    @Test
    public void whenGetPlayers_returnDataNotEmpty(){
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange("/api/soccer/player",
                HttpMethod.GET, entity, String.class);
        Gson gson = new Gson();
        DataResponse respBody = gson.fromJson(response.getBody(), DataResponse.class);
        System.out.println("data :"+respBody.getData ().toString());
        assertNotNull(respBody.getData ().toString ());
    }
}
