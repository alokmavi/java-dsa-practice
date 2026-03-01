package linkedlist;

public class ReverseLinkedList {

    static class Node {
        int value;
        Node nextReference;

        Node(int value) {
            this.value = value;
            this.nextReference = null;
        }
    }

    public static Node reverseList(Node headNode) {
        if (headNode == null || headNode.nextReference == null) {
            return headNode;
        }

        Node previousNode = null;
        Node currentNode = headNode;

        while (currentNode != null) {
            // Secure the unreversed portion of the list before mutating the current reference
            Node nextNode = currentNode.nextReference; 
            
            currentNode.nextReference = previousNode;
            
            previousNode = currentNode;
            currentNode = nextNode;
        }

        return previousNode;
    }

    public static void printList(Node headNode) {
        Node currentNode = headNode;
        while (currentNode != null) {
            System.out.print(currentNode.value + (currentNode.nextReference != null ? " -> " : ""));
            currentNode = currentNode.nextReference;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node headNode = new Node(10);
        headNode.nextReference = new Node(20);
        headNode.nextReference.nextReference = new Node(30);
        headNode.nextReference.nextReference.nextReference = new Node(40);

        System.out.print("Original List: ");
        printList(headNode);

        Node reversedHead = reverseList(headNode);

        System.out.print("Reversed List: ");
        printList(reversedHead);
    }
}