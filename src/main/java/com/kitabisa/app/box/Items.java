package com.kitabisa.app.box;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Items {
    Integer cakes;
    Integer apples;

    public Items() {
    }

    public Items(Integer cakes, Integer apples) {
        this.cakes = cakes;
        this.apples = apples;
    }
}
