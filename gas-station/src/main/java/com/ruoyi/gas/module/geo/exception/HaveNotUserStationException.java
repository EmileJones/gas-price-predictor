package com.ruoyi.gas.module.geo.exception;

public class HaveNotUserStationException extends RuntimeException{
    public HaveNotUserStationException() {
    }

    public HaveNotUserStationException(String message) {
        super(message);
    }

    public HaveNotUserStationException(String message, Throwable cause) {
        super(message, cause);
    }

    public HaveNotUserStationException(Throwable cause) {
        super(cause);
    }

    public HaveNotUserStationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
