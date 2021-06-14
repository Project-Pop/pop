package com.pop.common;


import org.springframework.lang.NonNull;

public class Response<T> {

    private String message;

    private String error;

    @NonNull
    private int code;

    private T data;

    private boolean containsError;


    public Response(T data, String message, int code) {
        this.message = message;
        this.code = code;
        this.data = data;
    }


    public Response(String error, int code) {
        this.error = error;
        this.code = code;
        this.containsError = true;
    }

    public static Response ok(String message, int code) {
        return new Response(null, message, code);
    }


    public static Response error(String error, int code) {
        return new Response(error, code);
    }

    public boolean isContainsError() {
        return containsError;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
