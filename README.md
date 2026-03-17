# java-dsa-practice
Java + DSA daily practice
## Day 1
- Java introduction
- First Java program
- Array sum program

## Day 2
- Java variables and data types
- Maximum element in array

## Day 3
- Java operators
- Reverse an array using two pointers

## Day 4
- Java if-else conditions
- Count even and odd numbers in array

## Day 5
- Java for and while loops
- Star pattern using nested loops

## Day 6
- Java do-while loop
- Number pattern using loops
- Introduction to time complexity

## Day 7
- Array revision
- Min, count, and sorted array problems

## Day 8
- Organized array programs into folder
- Sum of digits
- Linear search (O(n))

## Day 9
- Implemented binary search on sorted array
- Compared linear search vs binary search
- Understood O(n) vs O(log n) time complexity

## Day 10
- Implemented Bubble Sort
- Implemented Selection Sort
- Learned basic sorting logic and time complexity

## Day 11
- Insertion Sort
- Time complexity of insertion sort

## Day 12
- Revised Linear Search implementation
- Edge case handling
- Interview-style explanation and complexity analysis

## Day 13
- Binary Search (iterative)
- Edge case handling
- Time complexity: O(log n)

## Day 14
- Java String basics
- Reverse a string using two pointers
- Count vowels and consonants

## Day 15
- String palindrome check
- Character frequency in string
- StringBuilder usage

## Day 16
- Binary search revision
- Edge cases in binary search
- Handling mid, low, and high correctly
- Common binary search mistakes

## Day 17
- Binary Search (iterative & recursive)
- First and last occurrence
- Count occurrences using binary search

## Day 18
- Second largest element in array
- Move all zeros to end
- Rotate array left by k positions
- Check if two arrays are equal

## Day 19
- Frequency of elements using HashMap
- First non-repeating element
- Check if array contains duplicates using HashSet
- Count distinct elements in array

## Day 20 
- Reverse a string using two pointers
- Check palindrome string
- Count vowels and consonants
- Check if two strings are anagrams

## Day 21 
- First non-repeating character using HashMap
- Remove duplicate characters using LinkedHashSet
- Longest common prefix
- String compression

## Day 22 
- Print numbers from 1 to N using recursion
- Factorial using recursion
- Fibonacci using recursion
- Sum of digits using recursion

## Day 23 
- Check if array is sorted using recursion
- Find first occurrence of element (recursion)
- Find last occurrence of element (recursion)
- Print all occurrences of an element

## Day 24 
- Print all subsequences of a string
- Print all subsets of an array
- Count subsequences with given sum
- Print one subsequence with given sum

## Day 25
- Backtracking fundamentals
- Generated all subsets (power set)
- Generated permutations using recursion
- Implemented basic combination sum problem

## Day 26 
- Generate permutations of an array using backtracking
- Generate permutations of a string
- Solved N-Queens problem using backtracking

## Day 27 
- Big-O time complexity overview
- Compared O(n) vs O(n²) using code
- Understood space complexity with examples
- Common interview complexity traps

## Day 28
- Understood that recursion internally uses a call stack
- Simulated recursion using Java Stack
- Prepared for Stack data structure

## Day 29 - Stacks

### Topics
- Stack implementation using array
- Valid Parentheses
- Next Greater Element (Monotonic Stack)

## Day 30
- New Data Structure: Queue.
- Implementation: ArrayDeque (Java's standard modern queue).
- Pattern: Breadth-First Generation (using a queue to generate combinations layer-by-layer).

### Sliding Window Maximum (Monotonic Queue)
**Problem:** Find max in moving window `k`. Naive $O(N \times K)$ is too slow.
**Solution:** Use a Deque to store **indices** of useful candidates only.
**Logic:** Maintain strictly **decreasing order**. If `current > back`, remove back (it's useless).
**Algorithm:** 1. `pollFirst()` if index is out of window bounds.
2. `pollLast()` if `current val > back val`.
3. `offerLast(current index)`. Front is always the Max.

## Day 31: Sliding Window & Monotonic Queue
- Introduced the **Sliding Window** technique (processing fixed-size subarrays).
- Solved **First Negative Integer** in every window of size `k`.
- Solved **Sliding Window Maximum** using a Deque.
- Learned the **Monotonic Queue** pattern:
  - Storing indices instead of values to track window bounds.
  - Removing elements from the **back** to maintain decreasing order (removing "useless" elements).
  - Removing elements from the **front** when they slide out of the window.
- **Key Insight:** A Deque allows $O(1)$ access to the maximum in a window, reducing total time from $O(N \times K)$ to $O(N)$.

## Day 32
- Designed a Stack using a single Queue (Push $O(N)$, Pop $O(1)$)
- Designed a Queue using two Stacks (Amortized $O(1)$)
- Handled edge cases with strict defensive coding exceptions
- Mastered manual data flow reversal to simulate structural behaviors

## Day 33
- Transitioned from contiguous memory (Arrays) to scattered memory (Linked Lists).
- Designed a custom Singly Linked List `Node` class.
- Implemented $O(N)$ linear traversal and length calculation using references.
- Implemented $O(1)$ head insertion, demonstrating structural advantages over arrays.

## Day 34: Tail Operations & Deletion
- Implemented $O(N)$ tail insertion by traversing to `nextReference == null`.
- Handled empty list edge cases (`headNode == null`) during insertion.
- Implemented node deletion by value.
- **Key Insight:** To delete a node in a Singly Linked List, you must stop traversing one node *before* the target to re-link `currentNode.nextReference = currentNode.nextReference.nextReference`.
- Addressed the specific edge case of deleting the head node itself.

## Day 35: Reverse a Linked List
- **Problem:** Reverse a singly linked list in $O(N)$ time and $O(1)$ space.
- **Logic:** Requires three pointers (`previousNode`, `currentNode`, `nextNode`) to prevent memory loss.
- **Critical Step:** Always store `currentNode.nextReference` before changing it.
- **Return Value:** `previousNode` becomes the new head when the loop terminates.

## Day 36: Middle of Linked List (Tortoise & Hare)
- **Concept:** Solved middle node retrieval in a single pass using velocity-based dual pointers.
- **Logic:** `fastPointer` moves two steps, `slowPointer` moves one.
- **Boundary Handling:** Defensively checked `fastPointer != null && fastPointer.nextReference != null` to prevent traversal faults in even-length lists.
- **Time/Space:** Strict $O(N)$ time and $O(1)$ space.

## Day 37: Linked List Cycle Detection
- **Problem:** Determine if a Linked List contains an infinite memory loop.
- **Algorithm:** Applied Floyd’s Cycle-Finding Algorithm (Tortoise and Hare).
- **Logic:** If `fastTracker` and `slowTracker` pointer addresses eventually collide, a cycle is proven.
- **Complexity:** $O(N)$ time, $O(1)$ space. Safe from infinite `while` loop execution faults.

## Day 38: Locating Cycle Start Node
- **Problem:** Find the exact node where a memory cycle begins.
- **Algorithm:** Floyd's Algorithm (Phase 2).
- **Logic:** After the collision, reset `slowTracker` to head. Move both pointers at 1x speed. Their next collision is the cycle start.
- **Complexity:** $O(N)$ time, $O(1)$ space.

## Day 39: Merge Two Sorted Linked Lists
- **Problem:** Combine two sorted lists into a single sorted list.
- **Concept:** The "Zipper" technique utilizing two pointers.
- **Pattern:** Deployed a `dummyAnchor` node to gracefully handle head-initialization and empty list edge cases.
- **Efficiency:** $O(N + M)$ time to traverse both lists, $O(1)$ auxiliary space by strictly re-wiring existing memory references.

## Day 40: Palindrome Linked List
- **Problem:** Verify if a list is a palindrome in $O(N)$ time and strict $O(1)$ space.
- **Architecture:** Compounded previous patterns: `findMiddle` + `reverseList`.
- **Logic:** Reversed the second half in-place, compared dual pointers, then restored the list to its original state.
- **Key Insight:** Modularizing helper methods keeps complex pointer logic readable and interview-ready.

## Day 41: Doubly Linked Lists
- **Concept:** Upgraded node architecture with a `prevReference` for bidirectionality.
- **Advantage:** Enables O(1) backward traversal and allows node deletion without maintaining a trailing pointer.
- **Trade-off:** Requires twice the reference maintenance during mutations (updating both forward and backward links).
- **Implementation:** Built robust head insertion and value-based deletion handling all boundary conditions (head, tail, middle).

## Day 42: Reverse a Doubly Linked List
- **Logic:** Iterate through the list and strictly swap `nextReference` and `prevReference` for each node.
- **Trap Avoidance:** Advanced traversal by assigning `currentNode = currentNode.prevReference` after the swap.
- **Complexity:** $O(N)$ time complexity, strict $O(1)$ auxiliary space.
- **Edge Cases:** Handled `null` head and single-node structures defensively.

## Day 46: Breadth-First Search (BFS)
- **Concept:** Horizontal tree traversal processing nodes by structural proximity to the root.
- **Data Structure:** Utilized an explicit `Queue` (FIFO) instead of the recursive Call Stack.
- **Pattern:** Captured queue `size()` prior to processing to strictly isolate nodes by their depth level.
- **Complexity:** $O(N)$ execution time, $O(W)$ auxiliary space where $W$ is the maximum width of the tree.

## Day 47: Maximum Depth of Binary Tree
- **Problem:** Calculate the longest path from the root node down to the farthest leaf node.
- **Pattern:** Deployed Postorder DFS traversal to calculate depth bottom-up.
- **Logic:** Base case returns 0. Recursive step returns `Math.max(leftDepth, rightDepth) + 1`.
- **Complexity:** $O(N)$ time to visit every node, $O(H)$ auxiliary space for the recursion stack (where $H$ is the tree height).

## Day 48: Diameter of a Binary Tree
- **Problem:** Find the longest path between any two nodes (may not pass through root).
- **Architecture:** Leveraged Postorder depth calculation while simultaneously tracking a global maximum.
- **State Management:** Used a 1-element array `int[]` to bypass Java's pass-by-value limitation without relying on unsafe static variables.
- **Complexity:** $O(N)$ execution time, $O(H)$ auxiliary space for recursive frames.

## Day 49: Invert a Binary Tree
- **Problem:** Mirror the structural memory references of an entire tree.
- **Logic:** Deployed Postorder DFS. Saved original left/right child references before recursive reassignment to prevent memory loss.
- **Mutation:** Modified `currentNode.leftReference` to point to the inverted right subtree, and vice versa.
- **Complexity:** $O(N)$ execution time to visit all nodes, $O(H)$ space for the recursive call stack.

## Day 50: Identical Trees
- **Problem:** Verify strict structural and value equivalence between two distinct Binary Trees.
- **Pattern:** Synchronized parallel recursion. Passed two memory references simultaneously through the call stack.
- **Boundary Logic:** Validated `null` states explicitly before evaluating `nodeValue` to guarantee memory safety.
- **Complexity:** $O(\min(N, M))$ execution time. The algorithm short-circuits and returns `false` at the first mismatch.

## Day 51: Symmetric Tree Check
- **Problem:** Verify if a tree's structure and values are a perfect mirror reflection around its center.
- **Pattern:** Divergent parallel recursion. `Alpha` traversed left while `Beta` traversed right.
- **Logic:** Validated `leftBranch.nodeValue == rightBranch.nodeValue`, then checked `outerEdges` and `innerEdges` recursively.
- **Complexity:** O(N) execution time, O(H) auxiliary space for the call stack.

## Day 52: Balanced Binary Tree Detection
- **Problem:** Verify that no node possesses subtrees with a height differential strictly greater than 1.
- **Pattern:** Upgraded Postorder depth calculation to $O(N)$ bottom-up verification.
- **Error Propagation:** Utilized `-1` as an early-exit signal to avoid $O(N^2)$ redundant re-evaluations.
- **Complexity:** $O(N)$ execution time, $O(H)$ auxiliary space for the recursive call stack.

## Day 56: Validate Binary Search Tree
- **Problem:** Ensure a standard binary tree strictly adheres to the global Left < Root < Right structural constraint.
- **Vulnerability Avoidance:** Bypassed the local-comparison trap by passing dynamic structural boundaries down the recursive call stack.
- **Defensive Sizing:** Deployed `Long` primitives to prevent integer overflow collisions during extreme-value edge cases.
- **Complexity:** O(N) traversal time, O(H) auxiliary space for recursive boundary tracking.

## Day 57: Kth Smallest Element in a BST
- **Concept:** Exploited the strict ascending order of an Inorder BST traversal to locate ranked elements.
- **Optimization:** Implemented an early-exit short-circuit to abort the traversal exactly when the Kth element is found, bypassing $O(N)$ full-tree scans.
- **State Management:** Mutated a 2-element array across recursive frames to track countdown state and capture the target reference.
- **Complexity:** Time complexity is $O(H + K)$ where $H$ is the depth to reach the minimum node. Auxiliary space is $O(H)$ for the recursion stack.
