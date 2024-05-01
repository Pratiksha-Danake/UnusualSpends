package com.amaap.unusualspends.controller.dto;

import java.util.Objects;

public class Response {
    private final HttpStatus httpStatus;
    private final String msg;

    public Response(HttpStatus httpStatus, String msg) {
        this.httpStatus = httpStatus;
        this.msg = msg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response response = (Response) o;
        return httpStatus == response.httpStatus && Objects.equals(msg, response.msg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(httpStatus, msg);
    }
}
