package hr.tvz.laploggerapp.dto;

import hr.tvz.laploggerapp.model.Drivetrain;

public record CarDto(
        Long id,
        String make,
        String model,
        Integer year,
        Integer powerHp,
        Drivetrain drivetrain,
        String category
) {}
