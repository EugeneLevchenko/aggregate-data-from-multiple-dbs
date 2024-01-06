package org.comparus.ua.exception;

import lombok.Value;

@Value
public class NotFoundException extends RuntimeException {
    String message;
}
