package com.ruoyi.gas.module.price.exception;

public class NotExistOilTypeException extends RuntimeException {
    public NotExistOilTypeException() {
    }

    public NotExistOilTypeException(String message) {
        super(message);
    }

    public NotExistOilTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotExistOilTypeException(Throwable cause) {
        super(cause);
    }

    public NotExistOilTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
