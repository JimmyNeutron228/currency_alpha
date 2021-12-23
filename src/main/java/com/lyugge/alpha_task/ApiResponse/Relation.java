package com.lyugge.alpha_task.ApiResponse;

import lombok.Data;

import java.util.HashMap;

@Data
public class Relation {
    private String disclaimer;
    private String license;
    private Long timestamp;
    private String base;
    private HashMap<String, Double> rates;

    public Double curRelation(String currency) {
        return rates.get(currency);
    }
}
