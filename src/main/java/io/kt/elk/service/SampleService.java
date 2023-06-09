package io.kt.elk.service;

import io.kt.elk.rest.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public final class SampleService {

    private static final int ERROR_VALUE = 2;
    private static final int MAX_VALUE = 5;

    private static final int SECONDS_VALUE = 3;

    private static final long[] DURATIONS = {70, 150, 300, 500, 850, 1500, 2000};

    private static final String AMOUNT = "amount";
    private static final String PRODUCT = "product";

    private static final Set<String> ALLOWED_PRODUCTS = Set.of("auto", "student", "mortgage");

    private static final String UNKNOWN = "unknown";

    private final Random random = new Random(System.currentTimeMillis());

    public Map<String, Object> echo(String product, Double amount) {
        if (UNKNOWN.equals(product)) {
            throw new UnsupportedOperationException("Intentionally thrown");
        } else if (!ALLOWED_PRODUCTS.contains(product)) {
            throw new NotFoundException(product);
        } else if (random.nextInt(MAX_VALUE) == ERROR_VALUE) {
            throw new IllegalStateException("Unexpected race condition");
        } else if (random.nextInt(MAX_VALUE) == SECONDS_VALUE) {
            try {
                TimeUnit.MILLISECONDS.sleep(DURATIONS[random.nextInt(DURATIONS.length) - 1]);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        return Map.of(AMOUNT, amount, PRODUCT, product);
    }

}
