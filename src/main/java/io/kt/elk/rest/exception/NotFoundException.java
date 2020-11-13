package io.kt.elk.rest.exception;

public final class NotFoundException extends RuntimeException {

    public NotFoundException(String resource) {
        super(String.format("Resource [%s] not found", resource));
    }

}
