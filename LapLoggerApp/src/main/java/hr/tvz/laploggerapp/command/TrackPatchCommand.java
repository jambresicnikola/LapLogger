package hr.tvz.laploggerapp.command;

import hr.tvz.laploggerapp.validation.NullOrNotBlank;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

public record TrackPatchCommand(
        @NullOrNotBlank(message = "Name cannot be empty.")
        @Length(max = 150, message = "Name cannot be longer than 150 characters.")
        String name,

        @NullOrNotBlank(message = "Country cannot be empty.")
        @Length(max = 100, message = "Country cannot be longer than 100 characters.")
        String country,

        @DecimalMin(value = "0.0", inclusive = false, message = "Length must be greater than 0.")
        @Digits(integer = 3, fraction = 3, message = "Length must have at most 3 integer and 3 decimal digits.")
        BigDecimal lengthKm,

        @Length(max = 100, message = "Configuration cannot be longer than 100 characters.")
        String configuration
) {
}
