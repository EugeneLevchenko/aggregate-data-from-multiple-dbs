package org.comparus.ua.exception;

import lombok.Value;

@Value
public class InternalErrorException extends RuntimeException {
    String message;
}
