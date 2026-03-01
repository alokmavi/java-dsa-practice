package linkedlist;
public class LinkedListOperations {

    static class Node {
        int value;
        Node nextReference;

        Node(int value) {
            this.value = value;
            this.nextReference = null;
        }
    }

    public static Node insertAtTail(Node headNode, int newValue) {
        Node newNode = new Node(newValue);

        if (headNode == null) {
            return newNode;
        }

        Node currentNode = headNode;
        // Traverse until currentNode is the very last node
        while (currentNode.nextReference != null) {
            currentNode = currentNode.nextReference;
        }

        currentNode.nextReference = newNode;
        return headNode;
    }

    public static Node deleteByValue(Node headNode, int targetValue) {
        if (headNode == null) {
            return null;
        }

        // Edge case: the target is the head itself
        if (headNode.value == targetValue) {
            return headNode.nextReference;
        }

        Node currentNode = headNode;
        // Look ahead to find the target, so we can modify the current node's pointer
        while (currentNode.nextReference != null && currentNode.nextReference.value != targetValue) {
            currentNode = currentNode.nextReference;
        }

        // If we found the target, stitch the current node to the target's next node
        if (currentNode.nextReference != null) {
            currentNode.nextReference = currentNode.nextReference.nextReference;
        }

        return headNode;
    }

    public static void printList(Node headNode) {
        Node currentNode = headNode;
        while (currentNode != null) {
            System.out.print(currentNode.value + " -> ");
            currentNode = currentNode.nextReference;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        Node head = new Node(10);
        
        head = insertAtTail(head, 20);
        head = insertAtTail(head, 30);
        head = insertAtTail(head, 40);
        System.out.print("After Insertions: ");
        printList(head); // 10 -> 20 -> 30 -> 40 -> null

        head = deleteByValue(head, 30);
        System.out.print("After Deleting 30: ");
        printList(head); // 10 -> 20 -> 40 -> null
        
        head = deleteByValue(head, 10);
        System.out.print("After Deleting Head (10): ");
        printList(head); // 20 -> 40 -> null
    }
}