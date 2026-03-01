package linkedlist;
public class LinkedListBasics {

    static class Node {
        int value;
        Node nextReference;

        Node(int value) {
            this.value = value;
            this.nextReference = null;
        }
    }

    public static void traverseAndPrint(Node headNode) {
        if (headNode == null) {
            System.out.println("Empty List.");
            return;
        }

        Node currentNode = headNode;
        while (currentNode != null) {
            System.out.print(currentNode.value + " -> ");
            currentNode = currentNode.nextReference;
        }
        System.out.println("null");
    }

    public static int calculateLength(Node headNode) {
        int nodeCount = 0;
        Node currentNode = headNode;
        
        while (currentNode != null) {
            nodeCount++;
            currentNode = currentNode.nextReference;
        }
        return nodeCount;
    }

    public static void main(String[] args) {
        Node headNode = new Node(10);
        Node secondNode = new Node(20);
        Node thirdNode = new Node(30);

        headNode.nextReference = secondNode;
        secondNode.nextReference = thirdNode;

        traverseAndPrint(headNode); 
        System.out.println("Total Nodes: " + calculateLength(headNode));
    }
}