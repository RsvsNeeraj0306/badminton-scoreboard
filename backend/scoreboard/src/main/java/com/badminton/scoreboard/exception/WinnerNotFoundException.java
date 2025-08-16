package com.badminton.scoreboard.exception;

public class WinnerNotFoundException extends RuntimeException {
    public WinnerNotFoundException(String message) {
        super(message);
    }

}
