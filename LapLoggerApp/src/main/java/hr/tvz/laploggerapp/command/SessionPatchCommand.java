package hr.tvz.laploggerapp.command;

import jakarta.validation.constraints.PastOrPresent;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record SessionPatchCommand(
        @PastOrPresent(message = "Session date cannot be in the future.")
        LocalDate sessionDate,

        @Length(max = 100, message = "Conditions cannot be longer than 100 characters.")
        String conditions,

        String notes,
        Long carId,
        Long trackId
) {}
