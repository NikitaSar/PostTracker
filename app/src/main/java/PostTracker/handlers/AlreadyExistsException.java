package PostTracker.handlers;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AlreadyExistsException extends RuntimeException {
    @Getter
    private final String msg;
}
