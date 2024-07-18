// 4129962
// Nhlapo Nkululeko

import java.io.*;
import java.util.*;

// Represents the state of the board in the puzzle
class State implements Comparable<State> {
    private final int[] board;
    private final int hValue;

    // Constructor initializes the board and heuristic value
    public State(int[] board, int hValue) {
        this.board = Arrays.copyOf(board, board.length);
        this.hValue = hValue;
    }

    // Overrides compareTo method for PriorityQueue to prioritize states with lower heuristic values
    @Override
    public int compareTo(State o) {
        return Integer.compare(this.hValue, o.hValue);
    }

    // Accessor method for hValue
    public int getHValue() {
        return hValue;
    }

    // Accessor method for board
    public int[] getBoard() {
        return Arrays.copyOf(board, board.length);
    }
}

public class NineSoldiers {
    private static final int BOARD_SIZE = 13;
    private static final int MAX_MOVES = 700;

    // Main method to run the puzzle solver
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java NineSoldiers <input_filename>");
            return;
        }

        String inputFilename = args[0];
        String outputFilename = inputFilename.replace("input", "Output").replace(".txt", "Output.txt");

        int[] initialState = readInitialState(inputFilename);
        if (initialState == null) return;

        State startState = new State(initialState, calculateHeuristic(initialState));
        PriorityQueue<State> frontier = new PriorityQueue<>();
        frontier.add(startState);

        performSearch(frontier, outputFilename);
    }

    // Performs the Greedy Best First Search to solve the puzzle
    private static void performSearch(PriorityQueue<State> frontier, String outputFilename) {
        int moves = 0;
        while (!frontier.isEmpty() && moves <= MAX_MOVES) {
            State currentState = frontier.poll();
            if (isGoalState(currentState)) {
                outputSolution(currentState, outputFilename);
                return;
            }

            frontier.addAll(generateChildren(currentState));
            outputState(currentState);
            moves++;
        }

        System.out.println("Halted execution: Goal state not reached after 700 moves.");
    }

    // Reads the initial state of the board from the input file
    private static int[] readInitialState(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            int[] initialState = new int[BOARD_SIZE];
            for (int i = 0; i < BOARD_SIZE; i++) {
                initialState[i] = Integer.parseInt(reader.readLine().trim());
            }
            return initialState;
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Calculates the heuristic value for a given board state
    private static int calculateHeuristic(int[] board) {
        int heuristic = 0;
        for (int i = 1; i <= 9; i++) {
            if (board[i - 1] != i) {
                heuristic++;
            }
        }
        return heuristic;
    }

    // Checks if the current state is the goal state
    private static boolean isGoalState(State state) {
        int[] board = state.getBoard();
        for (int i = 1; i <= 9; i++) {
            if (board[i - 1] != i) {
                return false;
            }
        }
        return true;
    }

    // Generates possible children states by moving soldiers to adjacent empty cells
    private static List<State> generateChildren(State state) {
        List<State> children = new ArrayList<>();
        int[] board = state.getBoard();

        for (int i = 1; i <= 9; i++) {
            int soldierIndex = findSoldierIndex(board, i);
            if (soldierIndex == -1) continue;

            for (int j = -1; j <= 1; j++) {
                int newIndex = soldierIndex + j;
                if (newIndex >= 0 && newIndex < BOARD_SIZE && board[newIndex] == 0) {
                    int[] newBoard = Arrays.copyOf(board, board.length);
                    newBoard[soldierIndex] = 0;
                    newBoard[newIndex] = i;

                    State childState = new State(newBoard, calculateHeuristic(newBoard));
                    children.add(childState);
                }
            }
        }

        return children;
    }

    // Finds the index of a given soldier number on the board
    private static int findSoldierIndex(int[] board, int soldierNumber) {
        for (int i = 0; i < board.length; i++) {
            if (board[i] == soldierNumber) {
                return i;
            }
        }
        return -1;
    }

    // Outputs the current state to the console
    private static void outputState(State state) {
        int[] board = state.getBoard();
        int hValue = state.getHValue();

        System.out.println("h=" + hValue);
        System.out.println(Arrays.toString(board));
    }

    // Outputs the final solution state to a file
    private static void outputSolution(State state, String outputFilename) {
        try (PrintWriter writer = new PrintWriter(outputFilename)) {
            int[] board = state.getBoard();
            int hValue = state.getHValue();

            writer.println("h=" + hValue);
            writer.println(Arrays.toString(board));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

