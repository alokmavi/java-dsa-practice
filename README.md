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

## Day 53: Lowest Common Ancestor (Binary Tree)
- **Problem:** Locate the deepest shared parent node of two distinct target nodes.
- **Pattern:** Postorder DFS traversal returning discovered memory references upwards.
- **Decision Logic:** If both left and right subtrees return non-null references, the current node is the convergence point (LCA).
- **Complexity:** $O(N)$ execution time traversing the structure, $O(H)$ auxiliary space for stack frames.

## Day 54: Binary Search Tree (BST) Search & Insert
- **Concept:** Applied a systemic structural constraint (Left < Root < Right) to enable $O(\log N)$ search operations.
- **Validation:** Utilized Inorder DFS traversal to verify structural integrity (outputs strictly sorted data).
- **Efficiency:** Implemented iterative search to guarantee $O(1)$ auxiliary space during read-only access.
- **Insertion Logic:** Deployed recursive reassignment to wire new leaf nodes at the correct semantic depth.

## Day 55: BST Node Deletion
- **Problem:** Remove a specific node without violating the strict Left < Root < Right mathematical constraint.
- **Complexity Management:** Segmented logic into three structural cases: Leaf, Single Child, and Dual Children.
- **Resolution (Case 3):** Overwrote target value with its Inorder Successor (minimum node of right subtree), then recursively deleted the successor.
- **Efficiency:** $O(H)$ time for search and localized structural re-wiring, where $H$ is the tree height.

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

## Day 59: Min-Heap Architecture & Insertion
- **Concept:** Modeled a Complete Binary Tree utilizing strict contiguous array index arithmetic (omitting memory-heavy node references).
- **Constraint:** Maintained the Min-Heap property (Parent $\le$ Children) using algorithmic `bubbleUp` operations.
- **Defensive Design:** Implemented proactive capacity bounds checking to prevent `ArrayIndexOutOfBoundsException` on memory overflow.
- **Complexity:** $O(\log N)$ time for insertion, strict $O(1)$ auxiliary space during the index traversal.

## Day 60: Min-Heap Extraction & Heapify Down
- **Concept:** Extracted the root node while preserving the complete binary tree architecture.
- **Mechanism:** Transplanted the final leaf to the root and executed an iterative `bubbleDown` to restore the systemic constraint.
- **Defensive Design:** Guarded against memory underflow when attempting to extract from an empty operational boundary.
- **Complexity:** $O(\log N)$ extraction time, strict $O(1)$ auxiliary space.

## Day 61: Kth Largest Element
- **Problem:** Extract a rank-specific target from an unsorted data stream without sorting the entire dataset.
- **Data Structure:** Utilized `java.util.PriorityQueue` as a bounded Min-Heap.
- **Logic:** Maintained a strict size constraint of $K$. By constantly evicting the systemic minimum, only the $K$ maximum elements survived processing.
- **Complexity:** $O(N \log K)$ execution time, $O(K)$ auxiliary space. Superior to $O(N \log N)$ standard sorting, particularly when $K \ll N$.

## Day 62: Merge K Sorted Lists
- **Problem:** Combine an arbitrary number of sorted linked lists into a single continuous sorted list.
- **Architecture:** Fused Linked List pointer traversal with a Min-Heap.
- **Logic:** Seeded the heap with all list heads. As the minimum node is extracted and appended to the result, its immediate `nextReference` is pushed into the heap to replenish the evaluation pool.
- **Complexity:** $O(N \log K)$ execution time where $N$ is total nodes and $K$ is the number of lists. Auxiliary space is strictly bounded to $O(K)$ for the heap.

## Day 63: Graph Architecture & BFS
- **Concept:** Modeled an unconstrained network topology using a `HashMap` and `List` based Adjacency List.
- **Traversal:** Implemented Breadth-First Search using a `Queue` for level-by-level radial expansion.
- **Cycle Mitigation:** Deployed a `HashSet` to establish a strict `visitedRegistry`, preventing infinite execution loops.
- **Complexity:** $O(V + E)$ execution time where $V$ is total vertices and $E$ is total edges. Auxiliary space is bounded to $O(V)$ for the queue and registry.

## Day 64: Graph Depth-First Search (DFS)
- **Concept:** Navigated graph topology vertically using recursive backtracking.
- **Architecture:** Maintained a persistent `visitedRegistry` across recursive frames to serve as the systemic base case and prevent cycle-induced stack overflows.
- **Pattern:** Wrapper method initialized state, private helper method executed the call stack descent.
- **Complexity:** $O(V + E)$ execution time. Auxiliary space is bounded to $O(V)$ for both the recursion stack and the visited registry.

## Day 65: Directed Graph Cycle Detection
- **Concept:** Modeled directional dependencies and verified structural integrity against infinite resolution loops.
- **State Management:** Differentiated between a `globalVisitedRegistry` (for $O(1)$ pruning of verified safe paths) and an `activePathRegistry` (to track the current recursive descent).
- **Logic:** Identified cycles exclusively when a traversal edge points to a node currently residing in the `activePathRegistry` (a back-edge).
- **Complexity:** $O(V + E)$ execution time, $O(V)$ auxiliary space for registries and call stack.

## Day 66: Topological Sort (Kahn's Algorithm)
- **Problem:** Determine a linear execution order for a Directed Acyclic Graph (DAG) respecting all dependency constraints.
- **Mechanism:** Implemented Kahn's Algorithm using an `inDegree` tracker and a Breadth-First Search `Queue`.
- **Validation:** Utilized the final output size to guarantee the absence of circular dependencies dynamically.
- **Complexity:** $O(V + E)$ execution time as every vertex and edge is evaluated exactly once. $O(V)$ auxiliary space for the queue and degree mapping.

## Day 67: Dijkstra's Algorithm
- **Problem:** Calculate the absolute minimum travel cost from a single origin to all available vertices in a weighted network topology.
- **Architecture:** Merged Graph Adjacency Lists with a Min-Heap (`PriorityQueue`) to enforce a greedy traversal sequence based on lowest cumulative cost.
- **Constraint Management:** Implemented strict path relaxation and stale-entry pruning to avoid redundant sub-network processing.
- **Complexity:** $O((V + E) \log V)$ execution time, where $V$ is vertices and $E$ is edges. The logarithmic factor originates from the Min-Heap operations.

## Day 68: Number of Islands
- **Problem:** Calculate the total number of distinct connected components within an implicit 2D graph.
- **Traversal:** Utilized linear matrix scanning coupled with orthogonal Depth-First Search for neighbor discovery.
- **State Optimization:** Mutated discovered nodes in-place (`'1'` $\rightarrow$ `'0'`) to eliminate redundant traversals without allocating an external memory registry.
- **Complexity:** $O(M \times N)$ execution time where $M$ is rows and $N$ is columns. Auxiliary space is bounded to $O(M \times N)$ in the absolute worst-case scenario (a grid entirely filled with land) due to the recursive call stack.

## Day 69: Multi-Source BFS (Rotting Oranges)
- **Problem:** Calculate the minimum time required for a radial effect to cover an entire implicit graph.
- **Architecture:** Bypassed nested loop simulations by seeding a `Queue` with all initial source nodes simultaneously.
- **State Management:** Used an integer array `int[][] directionalVectors` to cleanly execute orthogonal memory bounds checking.
- **Complexity:** $O(M \times N)$ execution time, as each cell is visited and mutated exactly once. $O(M \times N)$ auxiliary space for the Queue in the worst-case scenario.

## Day 70: Climbing Stairs (Intro to DP)
- **Problem:** Calculate the total distinct combinations of 1-step and 2-step increments to reach a target integer.
- **Concept:** Translated an exponential overlapping subproblem ($O(2^N)$ recursion) into a linear Bottom-Up mathematical sequence.
- **Optimization:** Discarded the $O(N)$ tabulation array in favor of two sliding memory pointers, tracking only the strictly necessary historical states.
- **Complexity:** $O(N)$ execution time, strict $O(1)$ auxiliary space.

## Day 71: House Robber (1D DP Selection)
- **Problem:** Maximize total integer extraction from an array given the constraint that adjacent indices cannot be concurrently selected.
- **Concept:** Modeled the Optimal Substructure decision: `max(skipCurrent, takeCurrent + maxTwoStatesPrior)`.
- **Optimization:** Eradicated the $O(N)$ tabulation matrix, utilizing two sliding state variables to cache the rolling maxima.
- **Complexity:** $O(N)$ execution time through a single linear scan. Strict $O(1)$ auxiliary space constraint maintained.

## Day 72: House Robber II (Circular 1D DP)
- **Problem:** Maximize total integer extraction from a circular array where index `0` and `N-1` are structurally adjacent.
- **Concept:** Deconstructed a complex cyclic constraint into two mutually exclusive linear subproblems.
- **Architecture:** Reused the sliding-window state optimizer from Day 71 to process the disjoint sub-arrays (`0` to `N-2` vs `1` to `N-1`).
- **Complexity:** $O(N)$ execution time, consisting of two isolated linear scans. Strict $O(1)$ auxiliary space constraint maintained.

## Day 73: Unique Paths (2D DP)
- **Problem:** Calculate the total combinatorial paths from the top-left to the bottom-right of a grid given strict directional constraints (Right and Down).
- **Concept:** Modeled the 2D overlapping subproblem where the state of cell `(r, c)` is the sum of `(r-1, c)` and `(r, c-1)`.
- **Memory Optimization:** Collapsed the standard $O(M \times N)$ tabulation matrix into a 1D `horizontalStateBoundary` array by exploiting the top-down sequential dependency.
- **Complexity:** $O(M \times N)$ execution time. Auxiliary space strictly bounded to $O(N)$ where $N$ is the number of columns.

## Day 74: Unique Paths II (Obstacles)
- **Problem:** Calculate unique traversal routes through a grid while navigating physical blockages.
- **Concept:** Expanded the 2D DP state transition to accommodate systemic failure points (`Paths(r, c) = 0` if obstacle exists).
- **Optimization:** Refactored the initialization logic to allow the $O(N)$ sliding state array to process the 0th row dynamically, preventing manual out-of-bounds checks.
- **Complexity:** $O(M \times N)$ execution time scanning the matrix. Strict $O(N)$ auxiliary space maintained.

## Day 75: Longest Common Subsequence
- **Problem:** Calculate the maximum disjoint sequence alignment between two strings.
- **Architecture:** Applied a 2D Dynamic Programming matrix transitioning via `Diagonal + 1` (on match) or `Max(Above, Left)` (on mismatch).
- **Optimization:** Collapsed the $O(M \times N)$ tabulation matrix into a 1D `horizontalStateBoundary` array. Orchestrated a sliding variable (`previousDiagonalState`) to prevent premature overwriting of necessary subproblem data.
- **Complexity:** $O(M \times N)$ execution time. Auxiliary space strictly bounded to $O(\min(M, N))$ by dynamically swapping the sequence parameters based on string length.

## Day 76: Edit Distance (Levenshtein)
- **Problem:** Calculate the minimum operational cost (Insert, Delete, Replace) required to mutate a source string into a target string.
- **Concept:** Modeled the DP state transitions evaluating three distinct historical routing paths upon character mismatch: `1 + min(Left, Above, Diagonal)`.
- **Memory Optimization:** Maintained the $O(N)$ sliding array architecture from the LCS implementation, actively mutating the row initialization state to track target deletions.
- **Complexity:** $O(M \times N)$ execution time where $M$ and $N$ are the respective string lengths. Auxiliary space is strictly bounded to $O(N)$.

## Day 77: 0/1 Knapsack Problem
- **Problem:** Maximize total accumulated value given a strict maximum weight capacity and a catalog of indivisible items.
- **Concept:** Modeled bounded capacity using a choice state transition: `max(state[capacity], state[capacity - weight] + value)`.
- **Memory Optimization:** Collapsed the $O(N \times W)$ matrix into a 1D `optimalValueAtCapacity` array. Deployed a **reverse-iteration strategy** to strictly enforce the single-use item constraint, preventing array-overwrite corruption.
- **Complexity:** $O(N \times W)$ execution time where $N$ is the number of assets and $W$ is the maximum capacity. Auxiliary space strictly bounded to $O(W)$.

## Day 78: Target Sum (Mathematical Reduction)
- **Problem:** Calculate all valid `+/-` assignments across an array of integers that evaluate to a specific target integer.
- **Concept:** Reduced a complex dual-state combinatorial problem into a standard subset-sum search by deriving the mathematical target $P = (Target + Sum) / 2$.
- **Architecture:** Re-deployed the reverse-traversal 1D array DP engine from the 0/1 Knapsack algorithm, modifying the state transition to summate valid paths rather than maximizing values.
- **Complexity:** $O(N \times P)$ execution time where $P$ is the derived subset target. Auxiliary space strictly bounded to $O(P)$.

## Day 79: Coin Change (Unbounded Knapsack)
- **Problem:** Calculate the absolute minimum integer count of elements required to sum to a target, given an infinite supply of each element.
- **Concept:** Reversed the strict 0/1 Knapsack constraints. Transitioned from state maximization to state minimization (`Math.min`).
- **Memory Optimization:** Deployed a 1D state array, utilizing **forward-iteration** to deliberately allow combinatorial compounding (infinite asset reuse).
- **Complexity:** O(C * A) execution time where C is the number of coin denominations and A is the target amount. Auxiliary space is strictly bounded to O(A).

## Day 80: Longest Increasing Subsequence (LIS)
- **Problem:** Identify the maximum strictly increasing chain of elements within an unsorted array, omitting contiguous constraints.
- **Concept:** Expanded 1D DP state transition mechanics to require a full historical scan (`0` to `i-1`) for every evaluated index to verify structural sequence legality.
- **Architecture:** Maintained a `globalMaximumLength` tracker independent of the terminal index, acknowledging that the optimal sequence may conclude prior to the end of the data stream.
- **Complexity:** $O(N^2)$ execution time due to the nested historical scanning loop. Auxiliary space bounded to $O(N)$ for the state tracking array.

## Day 81: Word Break (Partition DP)
- **Problem:** Determine if an unspaced string can be perfectly segmented into a sequence of dictionary words.
- **Concept:** Expanded the historical scanning DP pattern to string partitioning. Evaluated substrings against a cached `HashSet` only if the preceding prefix state evaluated to `true`.
- **Optimization:** Deployed a `break` statement inside the inner loop upon finding the first valid partition point, pruning redundant historical checks.
- **Complexity:** $O(N^3)$ execution time. The outer loop runs $N$ times, the inner loop runs $N$ times, and the `substring()` extraction takes $O(N)$ time. Auxiliary space is bounded to $O(N)$ for the boolean array, plus the size of the dictionary cache.

## Day 82: Longest Palindromic Substring (Interval DP)
- **Problem:** Identify the maximum contiguous palindromic sequence within an unsorted string.
- **Concept:** Applied Interval Dynamic Programming, utilizing the state transition: `dp[i][j] = (s[i] == s[j]) && dp[i+1][j-1]`.
- **Traversal Architecture:** Bypassed standard row/column matrix traversal. Iterated dynamically by `intervalSpan` (length) to guarantee inner substrings were historically evaluated prior to outer boundary checks.
- **Complexity:** $O(N^2)$ execution time. Auxiliary space strictly bound to $O(N^2)$ due to the 2D boolean tracking matrix. (Note: "Expand Around Center" two-pointer approach is preferred for $O(1)$ production memory constraints).

## Day 83: Lexical Trie (Prefix Tree)
- **Concept:** Modeled a high-performance character-routing tree to enable instantaneous prefix validation.
- **Architecture:** Deployed fixed `LexicalNode[26]` arrays utilizing ASCII index mapping for strict $O(1)$ adjacent node retrieval.
- **State Management:** Differentiated sub-paths from complete strings using a terminal boolean flag (`isTerminalNode`).
- **Complexity:** $O(L)$ execution time for insertion and search, where $L$ is the character length of the target string. Auxiliary space is $O(T \times L)$ in the worst-case (no overlapping prefixes), where $T$ is total words.

## Day 84: Trie DFS & Wildcard Matching
- **Problem:** Design a data structure supporting $O(L)$ insertion and search capabilities while accommodating ambiguous wildcard characters (`.`).
- **Architecture:** Extended the standard Trie topology with a recursive Depth-First Search mechanism to handle non-deterministic routing.
- **State Transition:** Upon encountering a wildcard, the algorithm iteratively spans the call-stack across all non-null indices within the current `LexicalNode`'s character array.
- **Complexity:** Insertion remains strict $O(L)$. Deterministic search remains $O(L)$. Worst-case wildcard search degrades to $O(26^L)$ time complexity when queried with purely ambiguous patterns (e.g., `.......`), bounded by the topological density of the tree.

## Day 85: Disjoint Set (Union-Find)
- **Problem:** Dynamically track network connectivity and detect cyclical graph redundancies in near-constant time.
- **Architecture:** Implemented a 1D state array simulating a hierarchical tree structure. 
- **Optimizations:** 1. **Path Compression:** Flattened traversal chains recursively during the `Find` operation.
  2. **Union by Size:** Bound total depth growth by continuously appending smaller independent networks beneath larger primary networks.
- **Complexity:** Amortized $O(\alpha(N))$ time complexity for both Find and Union, effectively establishing $O(1)$ execution time. Auxiliary space strictly bound to $O(N)$ for state tracking arrays.

## Day 86: Kruskal's Algorithm (Minimum Spanning Tree)
- **Problem:** Calculate the absolute minimum cumulative edge weight required to connect all vertices in an undirected graph without forming cycles.
- **Concept:** Fused a greedy sorting mechanism with a Disjoint Set architecture. Evaluated edges in strictly ascending cost order, leveraging `establishConnection()` to reject cyclical redundancies dynamically.
- **Optimization:** Implemented a short-circuit termination condition, aborting the loop the moment exactly $V - 1$ edges are successfully committed to the structural state.
- **Complexity:** $O(E \log E)$ execution time dominated entirely by the array sorting phase. Auxiliary space is bounded to $O(V)$ for the Disjoint Set tracking arrays.

## Day 87: LRU Cache
- **Problem:** Design a fixed-capacity memory cache that automatically evicts the least recently accessed temporal data in strict $O(1)$ time.
- **Architecture:** Engineered a hybrid data structure bridging a `HashMap` (for instant memory address lookup) with a custom `DoublyLinkedList` (for instant pointer-based recency mutations).
- **Optimization:** Deployed fixed `headSentinel` and `tailSentinel` nodes to guarantee unhindered pointer reassignment boundaries, structurally eliminating Null Reference exceptions.
- **Complexity:** $O(1)$ execution time for both `insertRecord` and `retrieveRecord`. Auxiliary space strictly bounded to $O(C)$ where $C$ is the parameterized `maximumCapacity`.

## Day 88: Bloom Filter
- **Problem:** Filter millions of database queries in constant time without allocating unbounded memory for string storage.
- **Architecture:** Engineered a probabilistic data structure utilizing a `BitSet` and multiple orthogonal hash functions (`computeHashDispersions`).
- **State Guarantee:** Yields a $0\%$ false-negative rate, aggressively aborting queries for nonexistent data. Accepts a predictable false-positive rate dependent on load factor and bit array size.
- **Complexity:** $O(K \times L)$ execution time where $K$ is the number of hash functions and $L$ is the string length. Auxiliary space is strict $O(1)$ relative to the number of stored items, bound exclusively by the predefined `bitArrayCapacity`.

## Day 89: Circular Buffer (Ring Buffer)
- **Problem:** Design a fixed-size queue for continuous time-series data streams avoiding $O(N)$ index shifts and GC reallocation overhead.
- **Architecture:** Engineered a fixed-size integer array governed by independent `writeHead` and `readTail` pointers tracking the cyclical state.
- **State Guarantee:** Utilized modulo arithmetic `(index + 1) % capacity` to seamlessly wrap pointers across the array boundaries, decoupling data ingestion from data consumption.
- **Complexity:** Strict $O(1)$ execution time for all read, write, and state query operations. Auxiliary space is firmly capped at the pre-allocated $O(C)$ capacity.

## Day 90: Rabin-Karp Algorithm (Rolling Hash)
- **Problem:** Locate a substring within a continuous character stream avoiding $O(N \times M)$ redundant boundary checks.
- **Architecture:** Engineered a Rolling Hash mechanism operating across an `ALPHABET_BASE` bounded by a cryptographic `PRIME_MODULUS`.
- **State Guarantee:** Window mutation executes in strict $O(1)$ constant time by subtracting the leading coefficient and appending the trailing coefficient dynamically. False positives via integer collisions are structurally mitigated by a physical fallback verification.
- **Complexity:** $O(N + M)$ average execution time. Worst-case scenario degrades to $O(N \times M)$ exclusively in pathological architectures resulting in continuous, unbroken hash collisions. Auxiliary space is bounded to strict $O(1)$.

## Day 91: Knuth-Morris-Pratt (KMP) Algorithm
- **Problem:** Locate a substring within a continuous character stream while strictly eliminating `textStream` pointer backtracking.
- **Architecture:** Engineered a deterministic finite automaton utilizing a pre-computed Longest Prefix Suffix (LPS) array.
- **State Guarantee:** Mismatches trigger an instantaneous $O(1)$ internal pattern shift based on historical prefix overlaps, guaranteeing that the primary data stream is read exactly once, strictly forwards.
- **Complexity:** $O(N + M)$ execution time where $N$ is the stream length and $M$ is the pattern length. Absolute worst-case scenario remains $O(N + M)$ (unlike Rabin-Karp). Auxiliary space is strictly bounded to $O(M)$ for the state tracking LPS array.

## Day 92: Aho-Corasick Algorithm
- **Problem:** Execute multi-pattern substring detection against a continuous character stream in strict linear time.
- **Architecture:** Engineered a deterministic finite automaton fusing a Lexical Trie with a pre-compiled failure-link network via Breadth-First Search.
- **State Guarantee:** Employed defensive architectural states (`isCompiled`) separating the structural mutation phase (ingestion) from the operational read phase (search). Sub-pattern overlaps (e.g., subsets within larger matched strings) are pre-computed during the BFS to guarantee strict $O(1)$ terminal resolution during execution.
- **Complexity:** - Compilation: $O(M)$ time and space where $M$ is the combined length of all ingested vocabulary signatures.
  - Execution: $O(N + Z)$ time where $N$ is the length of the data stream and $Z$ is the total number of physical signature matches identified. Auxiliary space during execution is strict $O(1)$ relative to the automaton topology.