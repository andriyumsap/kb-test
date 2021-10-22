package com.kitabisa.app.box;

import com.kitabisa.app.util.ResponseTemplate;

public interface BoxService {
    /**
     * process calculate box / package
     *
     * @param cakes
     * @param apples
     * @return
     * @throws Exception
     */
    ResponseTemplate process(Integer cakes, Integer apples)throws Exception;
}
