package com.kitabisa.app.box;

import com.kitabisa.app.util.DataResponse;
import com.kitabisa.app.util.ResponseTemplate;
import org.springframework.stereotype.Service;

@Service
public class BoxServiceImpl implements BoxService {

    @Override
    public ResponseTemplate process(Integer cakes, Integer apples) throws Exception {
        Packages pck = countBox(cakes, apples);
        if(pck == null){
            return new DataResponse<>(null, 201, "Failed");
        }
        return new DataResponse<>(pck);
    }

    public Packages countBox(Integer cakes, Integer apples){
        if(validateData(cakes, apples)) {
            Items item = countcakesAndapples (cakes, apples);
            if (item != null) {
                return new Packages (item.getCakes (), item.getApples (), apples / item.getApples ());
            }
        }
        return null;
    }

    public Items countcakesAndapples(Integer cakes, Integer apples){
        Items item = new Items();
        if(validateData(cakes, apples)) {
            for (Integer a = 1; a < cakes; a++) {
                if (cakes % a == 0) {
                    if (apples % a == 0) {
                        item.setCakes (cakes / a);
                        item.setApples (apples / a);
                    }
                }
            }
        }
        return item;
    }

    public boolean validateData(Integer cakes, Integer apples){
        if(cakes <= 0 || apples <= 0){
            return false;
        }
        return true;
    }

}
