package com.kitabisa.app.notification;
import com.kitabisa.app.util.Util;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import static com.kitabisa.app.util.Util.getSslContext;

public class VendorOne extends Vendor {
    @Override
    void send(String phoneNumber, String message) {
        try {
            String url = "https://api.nusasms.com/api/v3/sendsms/plain?user=XXXX&password=ZZZZ&SMSText={ctn}&GSM={nmbr}&otp=Y&output=json";
            getSslContext ();
            RestTemplate restTemplate = new RestTemplate();
            final String baseUrl = url.replace("{nmbr}",phoneNumber).replace("{ctn}", message);
            URI uri = new URI(baseUrl.replaceAll("\\s","%20"));
            ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
            Util.loggingHttp(result, baseUrl);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
