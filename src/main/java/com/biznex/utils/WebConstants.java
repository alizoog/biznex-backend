package com.biznex.utils;

public interface WebConstants {

    String BASE_URL = "/api";
    String VERSION = "/v1";
    String ADMIN_URL = BASE_URL + VERSION + "/admin";
    String CLIENT_URL = BASE_URL + VERSION + "/client";
    String BASE_URL_WITH_VERSION = BASE_URL + VERSION;


    // open apis for security
    String[] OPEN_APIS = {
            BASE_URL_WITH_VERSION + "/auth/**",
            CLIENT_URL + "/**",
            BASE_URL_WITH_VERSION + "/references/**",
            "/swagger-ui/**",
            "/api-docs/**"
    };

}
