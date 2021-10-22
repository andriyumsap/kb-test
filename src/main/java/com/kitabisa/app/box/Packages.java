package com.kitabisa.app.box;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Packages extends Items {
    Integer box;

    public Packages(Integer cakes, Integer apples, Integer box) {
        super (cakes, apples);
        this.box = box;
    }
}
