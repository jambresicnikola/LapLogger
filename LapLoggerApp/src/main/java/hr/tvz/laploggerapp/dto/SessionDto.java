package hr.tvz.laploggerapp.dto;

import java.time.LocalDate;

public record SessionDto(
        Long id,
        LocalDate sessionDate,
        String conditions,
        String notes,
        CarDto car,
        TrackDto track
) {
}
