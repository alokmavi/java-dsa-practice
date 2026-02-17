package queue;

import java.util.ArrayDeque;
import java.util.Queue;

public class GenerateBinary {
    public static void solve(int n) {
        Queue<String> q = new ArrayDeque<>();
        q.offer("1");

        for (int i = 0; i < n; i++) {
            // 1. Remove the front
            String current = q.poll();
            
            // 2. Print it (or store it)
            System.out.print(current + " ");
            
            // 3. Generate the next two children using the current value
            String child1 = current + "0";
            String child2 = current + "1";
            
            // 4. Add them to the back of the line
            q.offer(child1);
            q.offer(child2);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int n = 10;
        System.out.println("Binary numbers from 1 to " + n + ":");
        solve(n);
    }
}