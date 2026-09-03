package hr.tvz.laploggerapp.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record SessionCommand(
        @NotNull(message = "Session date cannot be empty.")
        @PastOrPresent(message = "Session date cannot be in the future.")
        LocalDate sessionDate,

        @Length(max = 100, message = "Conditions cannot be longer than 100 characters.")
        String conditions,

        String notes,

        @NotNull(message = "Car must be selected.")
        Long carId,

        @NotNull(message = "Track must be selected.")
        Long trackId
) {
}
