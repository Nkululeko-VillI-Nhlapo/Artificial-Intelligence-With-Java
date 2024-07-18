## AI with Java Repository
### Nhlapo Nkululeko
<hr>

This repository contains two projects that demonstrate the implementation of two popular AI algorithms in Java: Greedy Best-First Search and A* Search. Both projects showcase the application of these algorithms to solve real-world problems, using appropriate data structures to manage the state and search process.

### Projects Summary

#### Nine Soldiers Puzzle Solver

This project implements an AI algorithm to solve the Nine Soldiers puzzle using Greedy Best-First Search. The goal is to arrange nine soldiers on a board such that they are in a specific target order, avoiding obstacles. The program reads the initial state of the board from a file, calculates heuristic values to prioritize moves, and generates child states until it reaches the goal or a maximum number of moves. The solution leverages a `PriorityQueue` for state exploration, a `State` class to represent board configurations, and heuristic calculations based on the soldiers' positions.

**Data Structures Used:**
- `PriorityQueue<State>`: To manage the frontier nodes based on their heuristic values.
- `List<State>`: To store child states generated from the current state.
- `State` class: To encapsulate the board configuration and heuristic value.

#### A* Delivery Routing

This project demonstrates the A* search algorithm for optimizing delivery routes in a city grid. The objective is to find the shortest path for a delivery vehicle to visit multiple delivery locations and return to the starting warehouse, avoiding obstacles. The grid is represented as a 2D array, with `0` for passable cells and `1` for obstacles. The solution uses a `PriorityQueue` to manage nodes for exploration, a `Node` class to store state information, and a `HashSet` to track visited nodes. The A* algorithm combines the cost to reach the current state and a heuristic estimate to determine the optimal path.

**Data Structures Used:**
- `PriorityQueue<Node>`: To manage the open list of nodes to be explored, ordered by their f-value (g + h).
- `HashSet<Node>`: To manage the closed list of nodes that have already been explored.
- `Node` class: To represent the state in the search, including position, cost values, parent node, and visited delivery points.



