package concurrency;

import java.util.concurrent.locks.ReentrantLock;

public class SystemRateLimiter {

    private final long maximumCapacity;
    private final double regenerationRatePerNanos;
    
    private double availableTokens;
    private long lastEvaluationTimestampNs;
    private final ReentrantLock concurrencyLock;

    public SystemRateLimiter(long maximumCapacity, long regenerationRatePerSecond) {
        if (maximumCapacity <= 0 || regenerationRatePerSecond <= 0) {
            throw new IllegalArgumentException("System constraints must be strictly positive integers.");
        }
        
        this.maximumCapacity = maximumCapacity;
        // Pre-compute the nanosecond fractional rate to avoid continuous floating-point division under load
        this.regenerationRatePerNanos = (double) regenerationRatePerSecond / 1_000_000_000.0;
        
        this.availableTokens = maximumCapacity;
        this.lastEvaluationTimestampNs = System.nanoTime();
        this.concurrencyLock = new ReentrantLock();
    }

    public boolean acquirePermit(int requiredPermits) {
        if (requiredPermits <= 0) {
            throw new IllegalArgumentException("Permit request must be strictly positive.");
        }

        concurrencyLock.lock();
        try {
            long currentTimestampNs = System.nanoTime();
            long elapsedNanos = currentTimestampNs - lastEvaluationTimestampNs;

            // Lazy Evaluation: Temporally regenerate tokens without requiring dedicated background thread allocation
            double generatedTokens = elapsedNanos * regenerationRatePerNanos;
            availableTokens = Math.min(maximumCapacity, availableTokens + generatedTokens);
            lastEvaluationTimestampNs = currentTimestampNs;

            // State Transition: Approve traffic and decrement tokens if capacity allows
            if (availableTokens >= requiredPermits) {
                availableTokens -= requiredPermits;
                return true;
            }
            
            // System Backpressure: Reject traffic (HTTP 429)
            return false;
            
        } finally {
            // Structural constraint: Guarantee lock release to prevent permanent thread deadlock
            concurrencyLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Initialize a system capable of 5 concurrent requests, regenerating 1 token every second
        SystemRateLimiter trafficController = new SystemRateLimiter(5, 1);

        System.out.println("--- Initial Burst Traffic ---");
        for (int i = 1; i <= 6; i++) {
            boolean isAllowed = trafficController.acquirePermit(1);
            System.out.println("Request " + i + " processed. Status: " + (isAllowed ? "ACCEPTED" : "REJECTED"));
        }
        // Expected: Requests 1-5 ACCEPTED. Request 6 REJECTED.

        System.out.println("\n--- Simulating Network Latency (2.5 seconds) ---");
        Thread.sleep(2500);

        System.out.println("--- Secondary Burst Traffic ---");
        for (int i = 1; i <= 3; i++) {
            boolean isAllowed = trafficController.acquirePermit(1);
            System.out.println("Request " + i + " processed. Status: " + (isAllowed ? "ACCEPTED" : "REJECTED"));
        }
        // Expected: Requests 1-2 ACCEPTED (2.5 seconds = ~2.5 tokens regenerated). Request 3 REJECTED.
    }
}