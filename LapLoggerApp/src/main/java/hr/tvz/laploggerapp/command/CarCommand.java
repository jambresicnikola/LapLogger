package hr.tvz.laploggerapp.command;

import hr.tvz.laploggerapp.model.Drivetrain;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

public record CarCommand(
        @NotBlank(message = "Make cannot be empty.")
        @Length(max = 100, message = "Make cannot be longer than 100 characters.")
        String make,

        @NotBlank(message = "Model cannot be empty.")
        @Length(max = 100, message = "Model cannot be longer than 100 characters.")
        String model,

        @NotNull(message = "Year cannot be empty.")
        @Min(value = 1900, message = "Year must be after 1900.")
        @Max(value = 2100, message = "Year must be before 2100.")
        Integer year,

        @NotNull(message = "Power cannot be empty.")
        @Positive(message = "Power must be a positive number.")
        Integer powerHp,

        @NotNull(message = "Drivetrain cannot be empty.")
        Drivetrain drivetrain,

        @Length(max = 50, message = "Category cannot be longer than 50 characters.")
        String category
) {}
