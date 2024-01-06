package org.comparus.ua.exception;

import lombok.Value;

@Value
public class BadRequestException extends RuntimeException {
    String message;
}
