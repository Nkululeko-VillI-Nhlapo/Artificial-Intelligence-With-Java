# Artificial-Intelligence-With-Java
# Nine Soldiers Puzzle Solver
### Nhlapo Nkululeko
<hr>

## Problem Description

The Nine Soldiers puzzle involves arranging nine soldiers numbered 1 through 9 into a specific goal configuration. The puzzle is represented as a 13-slot board where the goal is to arrange these soldiers in sequential order from 1 to 9, with the remaining slots empty. The challenge is to achieve this arrangement using a series of valid moves.

## Solution Approach

The provided Java program solves the Nine Soldiers puzzle using a **Greedy Best First Search** algorithm. This approach explores possible states of the board by prioritizing states that appear closer to the goal. The heuristic used is a simple count of the soldiers not in their goal positions.

### How the Algorithm Works

1. **Initialization:**
   - The initial board configuration is read from an input file.
   - The initial state of the board is created and added to a priority queue.

2. **Search Process:**
   - The algorithm uses a priority queue to explore states with the lowest heuristic values first.
   - It extracts the state with the lowest heuristic value, checks if it is the goal state, and generates new states by moving soldiers to adjacent empty cells.
   - The process continues until the goal state is found or a maximum of 700 moves is reached.

3. **State Generation:**
   - For each soldier, possible moves to adjacent empty cells are considered to generate new states.
   - Each new state is evaluated using the heuristic function and added to the priority queue.

4. **Output:**
   - The program prints the board configuration and heuristic value of each state to the console.
   - If the goal state is reached, it writes the final configuration to an output file.

## Files

- **`NineSoldiers.java`**: Contains the Java implementation of the puzzle solver.
- **`inputc.txt`**: Example input file with an initial board configuration.
- **`inputd.txt`**: Another example input file with a different board configuration.

## Usage

1. **Compile the Java Code:**
   ```sh
   javac NineSoldiers.java
