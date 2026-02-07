# ADS Assignment 4: Graph, BFS and DFS Algorithms

## Student: Aitmukhanbet Nazerke
## Course: Algorithms and Data Structures

## Overview
This project implements a complete graph toolkit with BFS and DFS algorithms as per Assignment 4 requirements. The implementation includes a Graph ADT, BFS with shortest path finding, DFS (both recursive and iterative), cycle detection, and a maze solver application.

## Project Structure
ADS-Assignment4-Graph-BFS-DFS/
├── src/
│ ├── graph/ # Graph ADT and implementations
│ ├── bfs/ # BFS algorithms and shortest path
│ ├── dfs/ # DFS algorithms and cycle detection
│ ├── applications/ # Part D: Maze solver application
│ └── io/ # Input/Output handlers
├── test/ # Test cases (25+ individual tests)
├── data/ # Sample input files
├── scripts/ # Build and run scripts
├── report/ # Assignment report (PDF)
└── docs/ # Documentation and sample outputs

text

## How to Run

### Prerequisites
- Java JDK 8 or higher
- (Optional) JUnit 5 for running tests

### Compilation
```bash
# Make script executable
chmod +x scripts/compile.sh

# Compile the project
./scripts/compile.sh


Running the Program
bash
# Make script executable
chmod +x scripts/run.sh

# Option 1: Run Main example (default)
./scripts/run.sh

# Option 2: Run Maze Solver (Part D)
./scripts/run.sh 2

# Option 3: Run tests (requires JUnit)
./scripts/run.sh 3


Running Individual Classes
bash
# Compile first (if not already compiled)
./scripts/compile.sh

# Run Main class
java -cp build Main

# Run Maze Solver
java -cp build applications.MazeSolver

# Run specific test (example)
java -cp build:lib/junit.jar org.junit.runner.JUnitCore GraphTest


Neighbor Ordering
For deterministic output as required by the assignment, neighbors are ordered using:

Natural ordering if vertices implement Comparable (e.g., Integer, String)

String comparison for string vertices

Hash code ordering as fallback for custom objects

This ensures BFS and DFS produce consistent, reproducible traversal orders.

Input Formats Supported
1. Edge List Format
text
n m directed(0/1) weighted(0/1)
u1 v1 [w1]
u2 v2 [w2]
...
n: number of vertices

m: number of edges

directed: 0 for undirected, 1 for directed

weighted: 0 for unweighted, 1 for weighted

Vertices can be integers (0..n-1) or strings

Example file: data/sample_edgelist.txt

2. Maze/Grid Format (for Part D)
text
rows cols
row1_char1 row1_char2 ...
row2_char1 row2_char2 ...
...
S = Start position

T = Target position

# = Wall (impassable)

. = Free cell (passable)

Example file: data/maze_input.txt

Test Cases Included
The test suite includes 25+ individual test cases covering:

Empty graph - Graph with no vertices

Single vertex - Graph with one isolated vertex

Disconnected graph - Multiple disconnected components

Multiple components - Testing traversal within components

Cycle detection - Detecting cycles in both directed and undirected graphs

BFS shortest path - Correct shortest path finding in unweighted graphs

DFS traversal determinism - Consistent DFS output order

Invalid input handling - Graceful error handling

Stress test - Performance with large graphs (1000+ vertices)

Part D: Maze Solver Application
The maze solver implements the chosen application from Part D:

Input: N×M grid with walls (#) and free cells (.)

Algorithm: BFS for shortest path in unweighted grid

Output:

Shortest path length

Path coordinates

Visual representation with path marked as *

Complexity: O(rows × cols) time and space

Complexity Analysis
Operation	Adjacency List	Adjacency Matrix
Add Vertex	O(1)	        O(	V	²)
Add Edge	  O(1)	        O(1)
Remove Edge	O(deg(v))	    O(1)
Check Edge	O(deg(v))	    O(1)
BFS/DFS	    O(	V	+	E	)	  O(	V	²)
Memory	    O(	V	+	E	)  	O(	V	²)

Maze Solver: O(rows × cols) for grid to graph conversion + O(|V| + |E|) for BFS.
