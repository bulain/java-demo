package com.bulain.common.exception;

public class OptimisticLockingException extends ServiceException {
    private static final long serialVersionUID = 1267055595332341752L;

    public OptimisticLockingException() {
        super();
    }

    public OptimisticLockingException(String message, Throwable cause) {
        super(message, cause);
    }

    public OptimisticLockingException(String message) {
        super(message);
    }

    public OptimisticLockingException(Throwable cause) {
        super(cause);
    }

}
