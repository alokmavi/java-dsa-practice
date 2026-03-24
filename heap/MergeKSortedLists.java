package heap;

import java.util.PriorityQueue;

public class MergeKSortedLists {

    static class Node {
        int nodeValue;
        Node nextReference;

        Node(int nodeValue) {
            this.nodeValue = nodeValue;
            this.nextReference = null;
        }
    }

    public static Node mergePipelines(Node[] sortedListArray) {
        if (sortedListArray == null || sortedListArray.length == 0) {
            return null;
        }

        // Custom comparator instructs the heap to maintain priority based strictly on nodeValue
        PriorityQueue<Node> nodeMinHeap = new PriorityQueue<>(
            sortedListArray.length, 
            (nodeAlpha, nodeBeta) -> Integer.compare(nodeAlpha.nodeValue, nodeBeta.nodeValue)
        );

        // Seed the heap with the initial boundary of every valid pipeline
        for (Node listHead : sortedListArray) {
            if (listHead != null) {
                nodeMinHeap.offer(listHead);
            }
        }

        Node dummyAnchor = new Node(-1);
        Node mergeTail = dummyAnchor;

        while (!nodeMinHeap.isEmpty()) {
            Node absoluteMinimumNode = nodeMinHeap.poll();
            
            mergeTail.nextReference = absoluteMinimumNode;
            mergeTail = mergeTail.nextReference;

            // Immediately replenish the heap from the pipeline that just yielded the minimum
            if (absoluteMinimumNode.nextReference != null) {
                nodeMinHeap.offer(absoluteMinimumNode.nextReference);
            }
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
        // Pipeline 1: 1 -> 4 -> 5
        Node listOne = new Node(1);
        listOne.nextReference = new Node(4);
        listOne.nextReference.nextReference = new Node(5);

        // Pipeline 2: 1 -> 3 -> 4
        Node listTwo = new Node(1);
        listTwo.nextReference = new Node(3);
        listTwo.nextReference.nextReference = new Node(4);

        // Pipeline 3: 2 -> 6
        Node listThree = new Node(2);
        listThree.nextReference = new Node(6);

        Node[] systemPipelines = new Node[]{listOne, listTwo, listThree};

        System.out.println("Merging distributed pipelines...");
        Node mergedResult = mergePipelines(systemPipelines);
        
        System.out.print("System Output: ");
        printList(mergedResult); // Expected: 1 -> 1 -> 2 -> 3 -> 4 -> 4 -> 5 -> 6
    }
}
