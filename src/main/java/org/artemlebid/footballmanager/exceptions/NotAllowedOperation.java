package org.artemlebid.footballmanager.exceptions;

public class NotAllowedOperation extends RuntimeException {
    public NotAllowedOperation(String message) {
        super(message);
    }
}
