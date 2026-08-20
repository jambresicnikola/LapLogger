package hr.tvz.laploggerapp.dto;

import java.math.BigDecimal;

public record TrackDto(
        Long id,
        String name,
        String country,
        BigDecimal lengthKm,
        String configuration
) {}
