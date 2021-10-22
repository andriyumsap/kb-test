package com.kitabisa.app.notification;
import com.kitabisa.app.util.DataResponse;
import com.kitabisa.app.util.ResponseTemplate;
import com.kitabisa.app.util.Util;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Override
    public ResponseTemplate send(String phoneNumber, String message)throws Exception {

        if(!Util.isNumeric(phoneNumber) || phoneNumber.trim ().length() < 11){
            return new DataResponse<> (null, 201, "Failed, Phone number does not match, at least 11 numbers and must not contain characters");
        }

        if(message.trim().length() == 0){
            return new DataResponse<>(null, 202, "Failed, message cannot be empty");
        }

        Vendor vendor = null;
        if(VendorOptions.VENDOR_ONE.isActive ()){
            vendor = new VendorOne ();
            vendor.send (phoneNumber, message);
            return new DataResponse<>();
        }else if(VendorOptions.VENDOR_TWO.isActive ()){
            vendor = new VendorTwo ();
            vendor.send (phoneNumber, message);
            return new DataResponse<>();
        }

        return new DataResponse<>(null, 203,"Failed, vendor not active");

    }

}
