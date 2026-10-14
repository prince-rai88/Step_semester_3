# Session 10: DSA Sorting & OOP Concepts - Solutions and Explanations

## Part B — Quiz Solutions

| Question | Answer | Explanation |
| :--- | :--- | :--- |
| **Q1** | **A, B, D, E** | • **A is TRUE**: A concrete class can always be instantiated.<br>• **B is TRUE**: An abstract class can have constructors and hold instance fields/state.<br>• **C is FALSE**: An interface cannot define per-instance mutable state (only `public static final` constants).<br>• **D is TRUE**: Any type with at least one abstract operation must be declared abstract.<br>• **E is TRUE**: Java 8+ interfaces can define default operations. |
| **Q2** | **A** | • The first ("family relationship sharing identity and state") represents an **IS-A** relationship (typically modeling inheritance / abstract class hierarchy).<br>• The second ("capability shared by unrelated types") represents a **CAN-DO** relationship (modeled by interfaces). |
| **Q3** | **A, C, E** | • **A is TRUE**: Operations declared in an interface are implicitly `public`.<br>• **B is FALSE**: Interfaces cannot define instance-specific state.<br>• **C is TRUE**: Interface methods are implicitly abstract unless declared `default`, `static`, or `private`.<br>• **D is FALSE**: Interfaces do not have constructors.<br>• **E is TRUE**: An interface can extend multiple other interfaces using the `extends` keyword. |
| **Q4** | **C** | Overriding `toString()` is intended to provide a concise, informative, and human-readable textual representation of an object for logging, debugging, and display purposes. |
| **Q5** | **C** | According to the Java `equals` and `hashCode` contract, if two objects are equal according to `equals(Object)`, calling `hashCode()` on each of the two objects must produce the exact same integer value. |
| **Q6** | **C** | In a shallow copy, only field values and object references are copied. If a field references a mutable object, both the original and copied objects point to the exact same reference in memory; modifying it via one copy reflects in the other. |
| **Q7** | **C** | In a fixed-size contiguous array, inserting or deleting an element in the middle requires shifting on average $O(n)$ subsequent elements to the right or left. |
| **Q8** | **A** | Fixed-size arrays support direct random access in **$O(1)$** constant time using base pointer address arithmetic: $\text{address} = \text{base} + \text{index} \times \text{element\_size}$. |
| **Q9** | **B** | Binary search relies on monotonicity to eliminate half of the search space at each step; therefore, the collection **must be sorted**. |
| **Q10** | **C** | Linear search scans elements one-by-one in the worst case taking **$O(n)$** time, whereas binary search halves the search space each step taking **$O(\log n)$** time. |

---

## Part C — Concept Questions

### Question 1: Abstract Type vs. Interface
- **Primary Use Cases**:
  - An **abstract class** is used when defining a closely related hierarchy of classes that share common state, identity, and behavior (an **IS-A** relationship), providing default code and partial implementation for subclasses.
  - An **interface** is used to declare a contract or capability (a **CAN-DO** relationship) that can be implemented by completely unrelated classes.
- **State and Implementation**:
  - Abstract classes can hold instance fields (state), declare constructors, and have arbitrary access modifiers (`protected`, `private`).
  - Interfaces cannot hold instance state (only `public static final` constants) and have no constructors. They define behaviors via abstract methods, `default` methods, and `static` methods.

### Question 2: The Equality Contract & Hash-Based Collections
- When overriding `equals(Object)` to establish content-based equality, you **must also override `hashCode()`**.
- **The Contract**: If `a.equals(b)` is `true`, then `a.hashCode()` must be strictly equal to `b.hashCode()`.
- **Implications in Hash Collections (`HashSet`, `HashMap`)**:
  - Hash collections find an object's storage bucket using its `hashCode()`.
  - If two equal objects have different hash codes, the collection places them in different buckets. Consequently, operations like `contains()`, `get()`, or `remove()` will fail to locate the element, leading to duplicate entries and broken set invariants.

### Question 3: Instance-Bound (Inner) vs. Independent (Static) Nested Types
- **Instance-bound Inner Class**:
  - Tied directly to an enclosing instance of the outer class.
  - Has implicit access to all instance fields and methods of the outer class (including private members).
  - Cannot exist without an outer instance (`outerInstance.new Inner()`). Used when the inner object is tightly coupled to the state and lifecycle of the outer object (e.g., custom iterators).
- **Static Nested Class**:
  - Independent of any enclosing instance; behaves like a regular top-level class packaged inside the outer class namespace.
  - Cannot access instance variables or methods of the outer class directly (only static members).
  - Can be instantiated directly (`new Outer.StaticNested()`). Used for helper or builder classes that logically belong together with the outer class without needing its instance context (e.g., `Map.Entry`, `Builder`).

### Question 4: Performance of Insert/Delete in Array & Mitigation
- **Performance**:
  - Both insertion and deletion in the middle of a fixed-size array have a time complexity of **$O(n)$**.
- **Reason for High Cost**:
  - Array elements are stored in a contiguous block of memory. Inserting an element requires shifting all subsequent elements one position to the right; deleting requires shifting subsequent elements one position to the left to maintain contiguity without gaps.
- **Mitigation Strategies**:
  - Use linked structures (e.g., `LinkedList`) if frequent insertions/deletions occur at known references ($O(1)$ pointer updates).
  - For dynamic arrays (`ArrayList`), insertions/deletions at the tail are $O(1)$ amortized.
  - Use tree-based structures (e.g., balanced BST, B-trees) or skip lists where insertions/deletions take $O(\log n)$.

### Question 5: Linear Search vs. Binary Search Trade-offs
- **Linear Search**:
  - **Complexity**: $O(n)$ time, $O(1)$ auxiliary space.
  - **Preconditions**: None (works on both unsorted and sorted collections).
  - **Best Used When**: The data is unsorted and queries are rare (one-off searches), or the collection size $n$ is very small where cache locality and low constant overhead make it fast. Sorting beforehand would cost $O(n \log n)$, which is not worthwhile for a single query.
- **Binary Search**:
  - **Complexity**: $O(\log n)$ time, $O(1)$ space.
  - **Preconditions**: Data must be stored in a random-access sorted structure (like a sorted array).
  - **Best Used When**: The dataset is already sorted or queries are frequent. Even if sorting costs $O(n \log n)$ once, doing $q$ queries at $O(\log n)$ results in an overall cost of $O(n \log n + q \log n)$, which heavily outperforms repeated linear searches ($O(q \times n)$).
