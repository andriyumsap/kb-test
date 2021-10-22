package com.kitabisa.app.box;

import com.kitabisa.app.util.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/box")
public class BoxController {
    @Autowired
    private BoxService boxService;

    @PostMapping(value = "/send")
    public ResponseTemplate create(@RequestParam(value = "cakes") Integer cakes,
                                   @RequestParam(value = "apples") Integer apples)throws Exception {
        return boxService.process (cakes, apples);
    }
}
