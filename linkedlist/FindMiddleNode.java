package linkedlist;
public class FindMiddleNode {

    static class Node {
        int nodeValue;
        Node nextReference;

        Node(int nodeValue) {
            this.nodeValue = nodeValue;
            this.nextReference = null;
        }
    }

    public static Node locateMiddle(Node listHead) {
        if (listHead == null) {
            return null;
        }

        Node slowPointer = listHead;
        Node fastPointer = listHead;

        // Fast pointer moves at 2x speed. When it exhausts the list bounds, slow pointer is safely anchored at the midpoint.
        while (fastPointer != null && fastPointer.nextReference != null) {
            slowPointer = slowPointer.nextReference;
            fastPointer = fastPointer.nextReference.nextReference;
        }

        return slowPointer;
    }

    public static void printList(Node listHead) {
        Node currentNode = listHead;
        while (currentNode != null) {
            System.out.print(currentNode.nodeValue + (currentNode.nextReference != null ? " -> " : ""));
            currentNode = currentNode.nextReference;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Test Case 1: Odd number of nodes
        Node oddListHead = new Node(10);
        oddListHead.nextReference = new Node(20);
        oddListHead.nextReference.nextReference = new Node(30);
        oddListHead.nextReference.nextReference.nextReference = new Node(40);
        oddListHead.nextReference.nextReference.nextReference.nextReference = new Node(50);

        System.out.print("Odd List: ");
        printList(oddListHead);
        
        Node oddMiddleNode = locateMiddle(oddListHead);
        System.out.println("Middle Node Value: " + (oddMiddleNode != null ? oddMiddleNode.nodeValue : "null")); // Expected: 30

        // Test Case 2: Even number of nodes
        Node evenListHead = new Node(10);
        evenListHead.nextReference = new Node(20);
        evenListHead.nextReference.nextReference = new Node(30);
        evenListHead.nextReference.nextReference.nextReference = new Node(40);

        System.out.print("\nEven List: ");
        printList(evenListHead);

        Node evenMiddleNode = locateMiddle(evenListHead);
        System.out.println("Middle Node Value (2nd middle): " + (evenMiddleNode != null ? evenMiddleNode.nodeValue : "null")); // Expected: 30
    }
}