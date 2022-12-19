package com.johnebri.cutsession.exception;

/**
 * @author macbookpro on 12/18/22
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
