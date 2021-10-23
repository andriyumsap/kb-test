package com.kitabisa.app.notification;
import com.kitabisa.app.util.DataResponse;
import com.kitabisa.app.util.ResponseTemplate;
import com.kitabisa.app.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private VendorOne vendorOne;

    @Autowired
    private VendorTwo vendorTwo;

    @Override
    public ResponseTemplate send(String phoneNumber, String message)throws Exception {

        if(!Util.isNumeric(phoneNumber) || phoneNumber.trim ().length() < 11){
            return new DataResponse<> (null, 201, "Failed, Phone number does not match, at least 11 numbers and must not contain characters");
        }

        if(message.trim().length() == 0){
            return new DataResponse<>(null, 202, "Failed, message cannot be empty");
        }

        if(sendToVendor (phoneNumber, message)){
            return new DataResponse<> ();
        }

        return new DataResponse<>(null, 203,"Failed, vendor not active");

    }

    public boolean sendToVendor(String phoneNumber, String message){
        if(VendorOptions.VENDOR_ONE.isActive ()){
            return vendorOne.send (phoneNumber, message);
        }else if(VendorOptions.VENDOR_TWO.isActive ()){
            return vendorTwo.send (phoneNumber,message);
        }
        return false;
    }

}
