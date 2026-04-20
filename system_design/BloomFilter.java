package system_design;

import java.util.BitSet;

public class BloomFilter {

    private final BitSet memoryBlock;
    private final int bitArrayCapacity;
    // Distinct prime multipliers enforce orthogonal mathematical dispersion across the bit array
    private final int[] cryptographicSeeds = {31, 37, 41}; 

    public BloomFilter(int bitArrayCapacity) {
        if (bitArrayCapacity <= 0) {
            throw new IllegalArgumentException("System requires a positive bit array capacity.");
        }
        this.bitArrayCapacity = bitArrayCapacity;
        this.memoryBlock = new BitSet(bitArrayCapacity);
    }

    private int[] computeHashDispersions(String dataPayload) {
        int[] dispersionIndices = new int[cryptographicSeeds.length];
        
        for (int i = 0; i < cryptographicSeeds.length; i++) {
            long hashValue = 0;
            for (char evaluationChar : dataPayload.toCharArray()) {
                hashValue = (hashValue * cryptographicSeeds[i] + evaluationChar) % bitArrayCapacity;
            }
            dispersionIndices[i] = (int) Math.abs(hashValue);
        }
        
        return dispersionIndices;
    }

    public void registerPayload(String dataPayload) {
        if (dataPayload == null || dataPayload.isEmpty()) {
            return;
        }

        int[] allocationIndices = computeHashDispersions(dataPayload);
        for (int index : allocationIndices) {
            memoryBlock.set(index);
        }
    }

    public boolean mightContain(String dataPayload) {
        if (dataPayload == null || dataPayload.isEmpty()) {
            return false;
        }

        int[] evaluationIndices = computeHashDispersions(dataPayload);
        for (int index : evaluationIndices) {
            // Absolute certainty: The path was never fully initialized
            if (!memoryBlock.get(index)) {
                return false; 
            }
        }
        
        // Probabilistic certainty: Structural overlap exists, disk verification required
        return true; 
    }

    public static void main(String[] args) {
        // Allocating a constrained bit space to force structural collisions for demonstration
        BloomFilter cacheFilter = new BloomFilter(100);

        cacheFilter.registerPayload("system_admin");
        cacheFilter.registerPayload("root_access");
        cacheFilter.registerPayload("guest_user");

        System.out.println("Query 'system_admin': " + cacheFilter.mightContain("system_admin")); 
        // Expected: true
        
        System.out.println("Query 'root_access': " + cacheFilter.mightContain("root_access")); 
        // Expected: true
        
        System.out.println("Query 'anonymous': " + cacheFilter.mightContain("anonymous")); 
        // Expected: false (Absolute guarantee the user does not exist)

        // Simulating a False Positive: As the memoryBlock fills, orthogonal paths will eventually collide
        System.out.println("\n--- Initiating Structural Density Load ---");
        for (int i = 0; i < 50; i++) {
            cacheFilter.registerPayload("dummy_payload_" + i);
        }
        
        // The probability of this returning true despite never being inserted rises as the bit array saturates
        System.out.println("Query 'unregistered_user': " + cacheFilter.mightContain("unregistered_user")); 
    }
}