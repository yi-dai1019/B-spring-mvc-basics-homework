package com.thoughtworks.capacity.gtb.mvc.Exception;

public class WrongUsernameOrPasswordException extends Throwable {
    public WrongUsernameOrPasswordException(String message) {
        super(message);
    }
}
