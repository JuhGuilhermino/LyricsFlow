package com.example.lyricsflow_backend.exception;

public class FlashcardNotFoundException extends RuntimeException {
    public FlashcardNotFoundException(String message) {
        super(message);
    }
}