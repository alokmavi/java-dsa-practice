package linkedlist;

public class ReverseDoublyLinkedList {

    static class DoublyNode {
        int nodeValue;
        DoublyNode nextReference;
        DoublyNode prevReference;

        DoublyNode(int nodeValue) {
            this.nodeValue = nodeValue;
            this.nextReference = null;
            this.prevReference = null;
        }
    }

    public static DoublyNode reverseList(DoublyNode headNode) {
        if (headNode == null || headNode.nextReference == null) {
            return headNode;
        }

        DoublyNode currentNode = headNode;
        DoublyNode newHeadReference = null;

        while (currentNode != null) {
            // 1. Swap the forward and backward references
            DoublyNode tempReferenceSwap = currentNode.prevReference;
            currentNode.prevReference = currentNode.nextReference;
            currentNode.nextReference = tempReferenceSwap;

            // 2. Track the last valid node, which becomes the new head
            newHeadReference = currentNode;

            // 3. Move to the next node in the ORIGINAL sequence
            // Crucial: Because we just swapped them, the original 'next' is now 'prev'
            currentNode = currentNode.prevReference;
        }

        return newHeadReference;
    }

    public static void printForward(DoublyNode currentHead) {
        DoublyNode traversalNode = currentHead;
        while (traversalNode != null) {
            System.out.print(traversalNode.nodeValue + (traversalNode.nextReference != null ? " <-> " : ""));
            traversalNode = traversalNode.nextReference;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Constructing the DLL: 10 <-> 20 <-> 30 <-> 40
        DoublyNode node1 = new DoublyNode(10);
        DoublyNode node2 = new DoublyNode(20);
        DoublyNode node3 = new DoublyNode(30);
        DoublyNode node4 = new DoublyNode(40);

        node1.nextReference = node2;
        
        node2.prevReference = node1;
        node2.nextReference = node3;
        
        node3.prevReference = node2;
        node3.nextReference = node4;
        
        node4.prevReference = node3;

        System.out.print("Original List: ");
        printForward(node1);

        DoublyNode reversedHead = reverseList(node1);

        System.out.print("Reversed List: ");
        printForward(reversedHead); // Expected: 40 <-> 30 <-> 20 <-> 10
    }
}