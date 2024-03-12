package com.tiny.lang.java.interview;

import java.util.concurrent.TimeUnit;

/**
 * rate limiter.
 */
public class RateLimiter {

    private final long ratePerSecond;

    private final long stepLength;

    private long latestTokenNs;

    public RateLimiter(long ratePerSecond) {
        this.ratePerSecond = ratePerSecond;
        this.stepLength = ratePerSecond * 1000_000_000 / ratePerSecond;
        this.latestTokenNs = 0;
    }

    public synchronized boolean acquire() {
        long time = System.nanoTime();
        long interval = time - latestTokenNs;
        if (interval >= stepLength) {
            latestTokenNs = time;
        } else {
            try {
                TimeUnit.NANOSECONDS.sleep(stepLength - interval);
            } catch (Exception ignored) {
            }

            latestTokenNs = System.nanoTime();
        }

        return true;
    }
}
