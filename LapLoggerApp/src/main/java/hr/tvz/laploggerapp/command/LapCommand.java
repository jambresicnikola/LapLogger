package hr.tvz.laploggerapp.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record LapCommand(
        @Positive(message = "Lap number must be positive.")
        @NotNull(message = "Lap number cannot be empty.")
        Integer lapNumber,

        @Positive(message = "Lap time must be positive.")
        @NotNull(message = "Lap time cannot be empty.")
        Long lapTimeMs,

        @Positive(message = "Sector time must be positive.")
        Long sector1Ms,

        @Positive(message = "Sector time must be positive.")
        Long sector2Ms,

        @Positive(message = "Sector time must be positive.")
        Long sector3Ms,

        @NotNull(message = "Lap validation cannot be empty.")
        Boolean isValid,

        @NotNull(message = "Session must be selected.")
        Long sessionId
) {}
