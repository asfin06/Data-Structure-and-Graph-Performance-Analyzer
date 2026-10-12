# Data Structure and Graph Performance Analyzer

**Module:** CIT300 Data Structures and Algorithms
**Assignment:** Graded Practical Assignment 2 (Week 12)
**Institution:** Sri Lanka Technology Campus (SLTC)

## Project Description

A Java console application that demonstrates data structures (Array, Stack, Queue, Linked List), searching
(Linear and Binary), graph representation and traversal (BFS and DFS), and a performance comparison that
records steps and execution time so the algorithms' complexity can be explained.

All data structures are implemented from scratch (no `java.util.Stack`, `Queue` or `LinkedList`).

## Team Members

| Student Name | Student ID |
|---|---|
| R. Susana Banu | 23DA2-0568 |
| NF. Nadha | 23DA2-0569 |
| T. Asfin Riffath | 23DA2-0744 |
| K.N. Suha | 23DA2-1120 |

### Individual Responsibilities and Contributions

**Student Name:** R. Susana Banu
**Student ID:** 23DA2-0568
**Assigned Responsibility:** Array, Searching and Performance Comparison
**Individual Contribution:**
- Implemented `ArrayOperations` (insert at end/position, delete by position/value, search, display, random fill)
- Implemented `SearchOperations` (linear search and binary search with step counting and timing)
- Implemented `PerformanceAnalyzer` (steps/time/complexity comparison table)
- Implemented `ResultLog` (stores results for "Display All Results")
- Tested array, searching and performance functionality and integrated them with the main application

**Student Name:** NF. Nadha
**Student ID:** 23DA2-0569
**Assigned Responsibility:** Stack, Queue, Main Menu and Integration
**Individual Contribution:**
- Implemented `ArrayStack` and `StackOperations` (push, pop, peek, display, overflow/underflow handling)
- Implemented `ArrayQueue` (circular array) and `QueueOperations` (enqueue, dequeue, peek/front, display, overflow/underflow handling)
- Implemented `Main` (main menu and integration of all components)
- Tested stack, queue and the complete menu flow

**Student Name:** T. Asfin Riffath
**Student ID:** 23DA2-0744
**Assigned Responsibility:** Linked List, Input Validation, Testing and Documentation
**Individual Contribution:**
- Implemented `LinkedListOperations` (insert at beginning/end, delete by value, search, display, empty-list handling)
- Implemented `InputHelper` (validated integer/text input used by every menu)
- Performed end-to-end testing (empty structures, invalid input, boundary cases)
- Wrote and maintained the README

**Student Name:** K.N. Suha
**Student ID:** 23DA2-1120
**Assigned Responsibility:** Graph Component
**Individual Contribution:**
- Implemented the `Graph` class using an adjacency list
- Implemented add vertex and add edge operations with validation (missing vertex, self-loop, duplicate edge)
- Implemented display graph, BFS traversal (using the custom `ArrayQueue`) and DFS traversal (recursive)
- Implemented vertex search and the sample graph loader
- Tested graph functionality and integrated the graph module with the main application

## Technologies Used

- Java (JDK 8 or newer)
- Console (text-based) interface using `Scanner`
- Git and GitHub for collaboration (branches, commits, pull requests, issues)

## Main System Features

- **Array:** insert, delete, search, display (capacity 50)
- **Stack:** push, pop, peek, display; handles pop from an empty stack and push onto a full stack
- **Queue:** enqueue, dequeue, peek/front, display; circular array; handles empty and full queue
- **Linked List:** insert at beginning/end, delete by value, search, display; handles empty list
- **Searching:** linear search and binary search, with step counts and time, on the array data or a large generated dataset
- **Graph:** add vertex, add edge, display (adjacency list), BFS, DFS, vertex search, sample graph
- **Performance Comparison:** table of steps, time and complexity for Linear vs Binary search, BFS vs DFS, and array vs linked-list insert at front
- **Display All Results:** shows every search, traversal and performance result recorded so far
- **Input validation:** invalid numbers, blank text and out-of-range choices are rejected and re-asked

## Complexity Summary

| Operation | Complexity |
|---|---|
| Array access / insert at end | O(1) |
| Array insert/delete in the middle | O(n) |
| Stack push / pop / peek | O(1) |
| Queue enqueue / dequeue / peek (circular array) | O(1) |
| Linked list insert at head | O(1) |
| Linked list insert at tail / search / delete | O(n) |
| Linear search | O(n) |
| Binary search (sorted data) | O(log n) |
| BFS / DFS (adjacency list) | O(V + E) |

## How to Run

Open a terminal in the project folder (the one containing `src`).

**Compile**

```
mkdir out
javac -d out src/*.java
```

**Run**

```
java -cp out Main
```

(On Windows PowerShell, use `mkdir out` once, then the same `javac` and `java` commands. If `src/*.java` is not accepted, use `javac -d out src\*.java`.)

## Project Structure

```
src/
  Main.java                  main menu and integration
  ArrayOperations.java       array component
  ArrayStack.java            stack data structure
  StackOperations.java       stack submenu
  ArrayQueue.java            circular queue data structure
  QueueOperations.java       queue submenu
  LinkedListOperations.java  singly linked list
  SearchOperations.java      linear and binary search
  Graph.java                 adjacency-list graph, BFS, DFS
  PerformanceAnalyzer.java   performance comparison
  ResultLog.java             stores results for "Display All Results"
  InputHelper.java           validated input
README.md
```
