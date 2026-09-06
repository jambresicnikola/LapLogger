package hr.tvz.laploggerapp.dto;

public record LapDto(
        Long id,
        Integer lapNumber,
        Long lapTimeMs,
        Long sector1Ms,
        Long sector2Ms,
        Long sector3Ms,
        Boolean isValid,
        SessionDto session
) {}
