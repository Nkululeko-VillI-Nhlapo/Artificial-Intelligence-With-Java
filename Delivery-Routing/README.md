# A* Delivery Routing

This project demonstrates the implementation of the A* search algorithm to find the shortest path for a delivery vehicle navigating a city grid. The vehicle starts at a warehouse and visits multiple delivery locations, then returns to the warehouse.

## Problem Description

The objective is to navigate a grid from a start position (warehouse) to multiple delivery locations, avoiding obstacles and finding the shortest path that visits all delivery points and returns to the warehouse. The grid is represented as a 2D array where `0` represents a passable cell and `1` represents an obstacle.

## Solution

The A* search algorithm is used to solve this problem. A* is a popular pathfinding and graph traversal algorithm that uses both the cost to reach the current state (g-value) and a heuristic estimate of the cost to reach the goal (h-value) to determine the best path.

In this implementation:
- The g-value is the number of moves taken from the start to the current cell.
- The h-value is the Manhattan distance from the current cell to the nearest unvisited delivery point.

## Application

The program starts from a specified start position (warehouse) and uses the A* algorithm to explore possible moves. It maintains an open list of nodes to be explored and a closed list of nodes that have already been explored. The algorithm continues until all delivery locations are visited and the vehicle returns to the warehouse.

## Data Structures Implemented

### Node Class

The `Node` class represents a state in the search. It stores:
- The current position (row and column).
- The g-value (cost to reach this node).
- The h-value (heuristic estimate to reach the goal).
- The parent node (for path reconstruction).
- A set of visited delivery points.

### PriorityQueue

A `PriorityQueue` is used to manage the open list of nodes to be explored, ordered by their f-value (g + h).

### HashSet

A `HashSet` is used to manage the closed list of nodes that have already been explored.

## How to Run

1. Compile the Java program:

    ```sh
    javac AStarDeliveryRouting.java
    ```

2. Run the program:

    ```sh
    java AStarDeliveryRouting
    ```

    The program will output the sequence of moves to visit all delivery locations and return to the warehouse.

## Example

Given the following grid:

