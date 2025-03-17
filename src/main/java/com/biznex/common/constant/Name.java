package com.biznex.common.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Name {
    private String uz;
    private String en;
    private String ru;

    public String getByLang(String lang) {
        return switch (lang) {
            case "en" -> en;
            case "ru" -> ru;
            default -> uz;
        };
    }
}