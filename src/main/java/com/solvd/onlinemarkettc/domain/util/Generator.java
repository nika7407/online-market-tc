package com.solvd.onlinemarkettc.domain.util;

public class Generator {

    public static String numberGenerator() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            sb.append(Math.round(Math.random() * 10));
        }
        return sb.toString();
    }
}
