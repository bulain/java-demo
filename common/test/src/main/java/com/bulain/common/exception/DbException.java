package com.bulain.common.exception;

public class DbException extends RuntimeException {
    private static final long serialVersionUID = -776521772526916216L;

    public DbException() {
        super();
    }

    public DbException(String message, Throwable cause) {
        super(message, cause);
    }

    public DbException(String message) {
        super(message);
    }

    public DbException(Throwable cause) {
        super(cause);
    }
}
