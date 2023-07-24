package com.example.springamqp.aula1.infra.jackson;

public class JsonConverterException extends RuntimeException {

    public JsonConverterException() {
    }

    public JsonConverterException(String message) {
        super(message);
    }

    public JsonConverterException(String message, Throwable cause) {
        super(message, cause);
    }

    public JsonConverterException(Throwable cause) {
        super(cause);
    }

    public JsonConverterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
