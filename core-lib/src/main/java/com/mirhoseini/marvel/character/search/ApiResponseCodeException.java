package com.mirhoseini.marvel.character.search;

/**
 * Created by Mohsen on 11/11/2016.
 */

public class ApiResponseCodeException extends Exception {
    private int code;
    private String status;

    public ApiResponseCodeException(int code, String status) {
        this.code = code;
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

}
