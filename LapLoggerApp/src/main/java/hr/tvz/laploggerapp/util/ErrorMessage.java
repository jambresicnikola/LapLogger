package hr.tvz.laploggerapp.util;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    CAR_NOT_FOUND("Car not found with id "),
    TRACK_NOT_FOUND("Track not found with id "),
    SESSION_NOT_FOUND("Session not found with id "),
    LAP_NOT_FOUND("Lap not found with id "),;

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }
}
