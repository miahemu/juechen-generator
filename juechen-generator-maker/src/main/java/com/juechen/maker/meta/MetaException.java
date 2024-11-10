package com.juechen.maker.meta;

/**
 * @author Juechen
 * @version : MetaException.java
 */
public class MetaException extends RuntimeException {

    public MetaException(String message) {
        super(message);
    }

    public MetaException(String message, Throwable cause){
        super(message, cause);
    }
}
