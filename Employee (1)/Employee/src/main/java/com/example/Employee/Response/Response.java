package com.example.Employee.Response;

import lombok.Getter;

@Getter
public class Response<T> {

    // GETTERS (REQUIRED FOR JSON)
    private final String message;
    private final T data;
    private final boolean success;

    public Response(String message, T data, boolean success) {
        this.message = message;
        this.data = data;
        this.success = success;
    }

}
