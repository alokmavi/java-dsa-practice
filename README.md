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