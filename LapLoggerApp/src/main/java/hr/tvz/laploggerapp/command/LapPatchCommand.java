package hr.tvz.laploggerapp.command;

import jakarta.validation.constraints.Positive;

public record LapPatchCommand(
        @Positive(message = "Lap number must be positive.")
        Integer lapNumber,

        @Positive(message = "Lap time must be positive.")
        Long lapTimeMs,

        @Positive(message = "Sector time must be positive.")
        Long sector1Ms,

        @Positive(message = "Sector time must be positive.")
        Long sector2Ms,

        @Positive(message = "Sector time must be positive.")
        Long sector3Ms,

        Boolean isValid,
        Long sessionId
) {}
