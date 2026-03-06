package linkedlist;
public class AddTwoNumbers {

    static class Node {
        int nodeValue;
        Node nextReference;

        Node(int nodeValue) {
            this.nodeValue = nodeValue;
            this.nextReference = null;
        }
    }

    public static Node computeSum(Node listOne, Node listTwo) {
        Node dummyAnchor = new Node(-1);
        Node resultTail = dummyAnchor;
        
        int carryValue = 0;

        // Loop continues as long as there is data in either list OR a leftover carry
        while (listOne != null || listTwo != null || carryValue > 0) {
            int currentSum = carryValue;

            if (listOne != null) {
                currentSum += listOne.nodeValue;
                listOne = listOne.nextReference;
            }

            if (listTwo != null) {
                currentSum += listTwo.nodeValue;
                listTwo = listTwo.nextReference;
            }

            // Extract the single digit and update the carry for the next iteration
            carryValue = currentSum / 10;
            Node sumDigitNode = new Node(currentSum % 10);
            
            resultTail.nextReference = sumDigitNode;
            resultTail = resultTail.nextReference;
        }

        return dummyAnchor.nextReference;
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
        // Constructing 342 (reversed: 2 -> 4 -> 3)
        Node numOneHead = new Node(2);
        numOneHead.nextReference = new Node(4);
        numOneHead.nextReference.nextReference = new Node(3);

        // Constructing 465 (reversed: 5 -> 6 -> 4)
        Node numTwoHead = new Node(5);
        numTwoHead.nextReference = new Node(6);
        numTwoHead.nextReference.nextReference = new Node(4);

        System.out.print("Number 1 (Reversed): ");
        printList(numOneHead);
        
        System.out.print("Number 2 (Reversed): ");
        printList(numTwoHead);

        Node resultHead = computeSum(numOneHead, numTwoHead);
        
        System.out.print("Sum Result (Reversed): ");
        printList(resultHead); // Expected: 7 -> 0 -> 8
        
        // Edge Case Test: 99 + 1 = 100 (reversed: 9->9 + 1 -> null = 0->0->1)
        Node edgeOne = new Node(9);
        edgeOne.nextReference = new Node(9);
        Node edgeTwo = new Node(1);
        
        System.out.print("\nEdge Case Sum Result: ");
        printList(computeSum(edgeOne, edgeTwo)); // Expected: 0 -> 0 -> 1
    }
}