package movie.bloder.repository.response_god;

import java.util.HashMap;
import java.util.function.Function;

public interface ResponseGod {

    HashMap<Integer, Function<Void, Void>> responseMapper = new HashMap<>();

    default Function<Void, Void> on200() { return null; }
    default void onUnknown(int code) {}

    default void handle(int code) {
        responseMapper.put(200, on200());
        try {
            responseMapper.get(code).apply(null);
        } catch (Exception e) {
            onUnknown(code);
        }
    }
}
