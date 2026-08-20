package hr.tvz.laploggerapp.command;

import hr.tvz.laploggerapp.model.Drivetrain;
import hr.tvz.laploggerapp.validation.NullOrNotBlank;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

public record CarPatchCommand(
        @NullOrNotBlank(message = "Make cannot be empty.")
        @Length(max = 100, message = "Make cannot be longer than 100 characters.")
        String make,

        @NullOrNotBlank(message = "Model cannot be empty.")
        @Length(max = 100, message = "Model cannot be longer than 100 characters.")
        String model,

        @Min(value = 1900, message = "Year must be after 1900.")
        @Max(value = 2100, message = "Year must be before 2100.")
        Integer year,

        @Positive(message = "Power must be a positive number.")
        Integer powerHp,

        Drivetrain drivetrain,

        @Length(max = 50, message = "Category cannot be longer than 50 characters.")
        String category
) {
}
