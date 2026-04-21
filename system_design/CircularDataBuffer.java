package system_design;

public class CircularDataBuffer {

    private final int[] bufferMemory;
    private final int maximumCapacity;
    private int writeHead;
    private int readTail;
    private int activeElementCount;

    public CircularDataBuffer(int maximumCapacity) {
        if (maximumCapacity <= 0) {
            throw new IllegalArgumentException("Buffer capacity must be strictly positive.");
        }
        this.maximumCapacity = maximumCapacity;
        this.bufferMemory = new int[maximumCapacity];
        this.writeHead = 0;
        this.readTail = 0;
        this.activeElementCount = 0;
    }

    public boolean enqueuePacket(int dataPacket) {
        if (isAtMaximumCapacity()) {
            // System backpressure: Buffer is saturated, upstream must throttle
            return false; 
        }
        
        bufferMemory[writeHead] = dataPacket;
        // Core Mathematical Transition: Wrap the pointer using modulo arithmetic
        writeHead = (writeHead + 1) % maximumCapacity;
        activeElementCount++;
        
        return true;
    }

    public int dequeuePacket() {
        if (isCompletelyEmpty()) {
            throw new IllegalStateException("Buffer underflow: No data packets available for consumption.");
        }
        
        int retrievedPacket = bufferMemory[readTail];
        readTail = (readTail + 1) % maximumCapacity;
        activeElementCount--;
        
        return retrievedPacket;
    }

    public int peekFront() {
        if (isCompletelyEmpty()) {
            throw new IllegalStateException("Buffer underflow: No data packets available.");
        }
        return bufferMemory[readTail];
    }

    public int peekRear() {
        if (isCompletelyEmpty()) {
            throw new IllegalStateException("Buffer underflow: No data packets available.");
        }
        // Offset logic: If the write head just wrapped to 0, the last written element is at the very end of the array
        int rearIndex = (writeHead == 0) ? maximumCapacity - 1 : writeHead - 1;
        return bufferMemory[rearIndex];
    }

    public boolean isCompletelyEmpty() {
        return activeElementCount == 0;
    }

    public boolean isAtMaximumCapacity() {
        return activeElementCount == maximumCapacity;
    }

    public static void main(String[] args) {
        // Allocate a constrained buffer capable of holding exactly 3 packets
        CircularDataBuffer networkBuffer = new CircularDataBuffer(3);

        System.out.println("Enqueue Packet 1: " + networkBuffer.enqueuePacket(100)); // Expected: true
        System.out.println("Enqueue Packet 2: " + networkBuffer.enqueuePacket(200)); // Expected: true
        System.out.println("Enqueue Packet 3: " + networkBuffer.enqueuePacket(300)); // Expected: true
        
        // System is saturated
        System.out.println("Enqueue Packet 4: " + networkBuffer.enqueuePacket(400)); // Expected: false
        
        // Consume the oldest packet (100) to free up memory
        System.out.println("Dequeue Packet: " + networkBuffer.dequeuePacket()); // Expected: 100
        
        // The buffer wrapped around. Packet 400 will physically overwrite the memory slot at index 0.
        System.out.println("Enqueue Packet 4: " + networkBuffer.enqueuePacket(400)); // Expected: true
        
        System.out.println("Rear Packet (Most Recent): " + networkBuffer.peekRear()); // Expected: 400
        System.out.println("Front Packet (Oldest): " + networkBuffer.peekFront()); // Expected: 200
    }
}