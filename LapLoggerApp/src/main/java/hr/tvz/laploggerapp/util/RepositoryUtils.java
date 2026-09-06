package hr.tvz.laploggerapp.util;

import hr.tvz.laploggerapp.exception.ResourceNotFoundException;

import java.util.Optional;

public class RepositoryUtils {
    private RepositoryUtils() {}

    public static <T> T findOrThrow(Optional<T> optional, String errorMessage) {
        return optional.orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }
}
