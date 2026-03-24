package heap;
import java.util.PriorityQueue;

public class KthLargestElement {

    public static int locateKthLargest(int[] valueStream, int targetRank) {
        if (valueStream == null || valueStream.length == 0) {
            throw new IllegalArgumentException("Data stream cannot be null or empty.");
        }
        if (targetRank <= 0 || targetRank > valueStream.length) {
            throw new IllegalArgumentException("Target rank is outside the bounds of the active stream.");
        }

        // Java's PriorityQueue defaults to a Min-Heap configuration.
        PriorityQueue<Integer> topKTracker = new PriorityQueue<>(targetRank);

        for (int currentValue : valueStream) {
            topKTracker.offer(currentValue);

            // Evict the systemic minimum to strictly maintain a maximum capacity of K
            if (topKTracker.size() > targetRank) {
                topKTracker.poll();
            }
        }

        return topKTracker.peek();
    }

    public static void main(String[] args) {
        int[] serverTelemetry = {3, 2, 1, 5, 6, 4};
        int rankToFind = 2;

        int targetElement = locateKthLargest(serverTelemetry, rankToFind);
        System.out.println("The " + rankToFind + "nd largest metric is: " + targetElement); // Expected: 5

        int[] transactionStream = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int nextRank = 4;
        
        int targetTransaction = locateKthLargest(transactionStream, nextRank);
        System.out.println("The " + nextRank + "th largest metric is: " + targetTransaction); // Expected: 4
    }
}