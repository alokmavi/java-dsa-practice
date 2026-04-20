package system_design;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    static class CacheNode {
        int recordKey;
        int recordValue;
        CacheNode previousLink;
        CacheNode nextLink;

        CacheNode(int recordKey, int recordValue) {
            this.recordKey = recordKey;
            this.recordValue = recordValue;
        }
    }

    private final int maximumCapacity;
    private final Map<Integer, CacheNode> lookupRegistry;
    private final CacheNode headSentinel;
    private final CacheNode tailSentinel;

    public LRUCache(int maximumCapacity) {
        if (maximumCapacity <= 0) {
            throw new IllegalArgumentException("System cache capacity must be strictly positive.");
        }
        
        this.maximumCapacity = maximumCapacity;
        this.lookupRegistry = new HashMap<>();
        
        // Sentinel nodes structurally isolate the list, preventing NullPointer edge cases during mutations
        this.headSentinel = new CacheNode(-1, -1);
        this.tailSentinel = new CacheNode(-1, -1);
        this.headSentinel.nextLink = this.tailSentinel;
        this.tailSentinel.previousLink = this.headSentinel;
    }

    public int retrieveRecord(int targetKey) {
        if (!lookupRegistry.containsKey(targetKey)) {
            return -1;
        }

        CacheNode accessedNode = lookupRegistry.get(targetKey);
        
        // State update: Elevate the accessed record to the Most Recently Used position (immediately behind head)
        severNodeLinks(accessedNode);
        insertBehindHead(accessedNode);

        return accessedNode.recordValue;
    }

    public void insertRecord(int targetKey, int targetValue) {
        if (lookupRegistry.containsKey(targetKey)) {
            CacheNode existingNode = lookupRegistry.get(targetKey);
            existingNode.recordValue = targetValue;
            
            severNodeLinks(existingNode);
            insertBehindHead(existingNode);
            return;
        }

        if (lookupRegistry.size() >= maximumCapacity) {
            // Structural eviction: The LRU node is physically located immediately preceding the tail sentinel
            CacheNode lruNode = tailSentinel.previousLink;
            severNodeLinks(lruNode);
            lookupRegistry.remove(lruNode.recordKey);
        }

        CacheNode newNode = new CacheNode(targetKey, targetValue);
        lookupRegistry.put(targetKey, newNode);
        insertBehindHead(newNode);
    }

    private void severNodeLinks(CacheNode targetNode) {
        targetNode.previousLink.nextLink = targetNode.nextLink;
        targetNode.nextLink.previousLink = targetNode.previousLink;
    }

    private void insertBehindHead(CacheNode targetNode) {
        targetNode.nextLink = headSentinel.nextLink;
        targetNode.previousLink = headSentinel;
        
        headSentinel.nextLink.previousLink = targetNode;
        headSentinel.nextLink = targetNode;
    }

    public static void main(String[] args) {
        LRUCache systemCache = new LRUCache(2);

        systemCache.insertRecord(1, 100);
        systemCache.insertRecord(2, 200);
        
        System.out.println("Retrieve Key 1: " + systemCache.retrieveRecord(1)); 
        // Expected: 100 (Key 1 is now designated as Most Recently Used)
        
        systemCache.insertRecord(3, 300); 
        // Capacity exceeded. Evicts Key 2 (Least Recently Used).
        
        System.out.println("Retrieve Key 2: " + systemCache.retrieveRecord(2)); 
        // Expected: -1 (System miss: Record evicted)
        
        systemCache.insertRecord(4, 400); 
        // Capacity exceeded. Evicts Key 1.
        
        System.out.println("Retrieve Key 1: " + systemCache.retrieveRecord(1)); 
        // Expected: -1 (System miss: Record evicted)
        System.out.println("Retrieve Key 3: " + systemCache.retrieveRecord(3)); 
        // Expected: 300
        System.out.println("Retrieve Key 4: " + systemCache.retrieveRecord(4)); 
        // Expected: 400
    }
}
