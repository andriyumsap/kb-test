package com.kitabisa.app.notification;
import com.kitabisa.app.util.Util;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import static com.kitabisa.app.util.Util.getSslContext;

@Component
public class VendorOne extends Vendor{
    @Value("${sms.url.vendor-one}")
    private String URL_VENDOR_ONE;

    public boolean send(String phoneNumber, String message) {
        try {
            getSslContext ();
            RestTemplate restTemplate = new RestTemplate();
            final String baseUrl = URL_VENDOR_ONE.replace("{nmbr}",phoneNumber).replace("{ctn}", message);
            URI uri = new URI(baseUrl.replaceAll("\\s","%20"));
            ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
            Util.loggingHttp(result, baseUrl);
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }
}
