package com.grapefruit;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Stu {

    String uniqueId;

    int age;

    public Stu(String uniqueId, int age) {
        this.uniqueId = uniqueId;
        this.age = age;
    }
}
