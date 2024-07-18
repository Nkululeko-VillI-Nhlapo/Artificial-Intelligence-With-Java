import java.util.*;

class Node implements Comparable<Node> {
    private final int row, col;
    private final int gValue, hValue;
    private final Node parent;
    private final Set<int[]> visited;

    // Constructor to initialize Node object
    public Node(int row, int col, int gValue, int hValue, Node parent, Set<int[]> visited) {
        this.row = row;
        this.col = col;
        this.gValue = gValue;
        this.hValue = hValue;
        this.parent = parent;
        this.visited = new HashSet<>(visited);
    }

    // Compare nodes based on f-value (gValue + hValue) for PriorityQueue ordering
    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.gValue + this.hValue, other.gValue + other.hValue);
    }

    // Getter methods for node properties
    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getGValue() {
        return gValue;
    }

    public int getHValue() {
        return hValue;
    }

    public Node getParent() {
        return parent;
    }

    public Set<int[]> getVisited() {
        return new HashSet<>(visited);
    }

    // Override equals method for Node comparison
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Node node = (Node) obj;
        return row == node.row && col == node.col;
    }

    // Override hashCode method for Node hashing
    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}

public class AStarDeliveryRouting {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Possible movement directions
    private static final int[][] DELIVERY_POINTS = {{0, 3}, {2, 2}, {3, 0}, {4, 4}}; // Example delivery points

    public static void main(String[] args) {
        // Define the grid (0: passable cell, 1: obstacle)
        int[][] grid = {
            {0, 0, 0, 0, 0},
            {0, 1, 0, 1, 0},
            {0, 0, 0, 1, 0},
            {0, 1, 0, 0, 0},
            {0, 0, 0, 1, 0}
        };

        int[] start = {0, 0}; // Starting position

        // Perform A* search to find the path
        List<int[]> path = aStarSearch(grid, start, DELIVERY_POINTS);
        if (path != null) {
            for (int[] node : path) {
                System.out.println(Arrays.toString(node));
            }
        } else {
            System.out.println("No path found.");
        }
    }

    private static List<int[]> aStarSearch(int[][] grid, int[] start, int[][] deliveryPoints) {
        PriorityQueue<Node> openList = new PriorityQueue<>(); // Nodes to be explored
        Set<Node> closedList = new HashSet<>(); // Explored nodes

        Set<int[]> initialVisited = new HashSet<>();
        Node startNode = new Node(start[0], start[1], 0, calculateHeuristic(start, deliveryPoints, initialVisited), null, initialVisited);
        openList.add(startNode);

        while (!openList.isEmpty()) {
            Node currentNode = openList.poll(); // Get node with lowest f-value
            closedList.add(currentNode);

            // Check if all delivery points are visited
            if (currentNode.getVisited().size() == deliveryPoints.length) {
                return constructPath(currentNode);
            }

            // Explore neighbors
            for (int[] direction : DIRECTIONS) {
                int newRow = currentNode.getRow() + direction[0];
                int newCol = currentNode.getCol() + direction[1];

                // Validate the move
                if (isValidMove(grid, newRow, newCol)) {
                    Set<int[]> newVisited = currentNode.getVisited();
                    for (int[] deliveryPoint : deliveryPoints) {
                        if (newRow == deliveryPoint[0] && newCol == deliveryPoint[1]) {
                            newVisited.add(deliveryPoint);
                        }
                    }
                    int newGValue = currentNode.getGValue() + 1;
                    int newHValue = calculateHeuristic(new int[]{newRow, newCol}, deliveryPoints, newVisited);
                    Node neighborNode = new Node(newRow, newCol, newGValue, newHValue, currentNode, newVisited);

                    // Add neighbor to open list if not in closed list
                    if (!closedList.contains(neighborNode) && !openList.contains(neighborNode)) {
                        openList.add(neighborNode);
                    }
                }
            }
        }
        return null; // No path found
    }

    private static int calculateHeuristic(int[] start, int[][] deliveryPoints, Set<int[]> visited) {
        int heuristic = 0;
        for (int[] point : deliveryPoints) {
            if (!visited.contains(point)) {
                heuristic += Math.abs(start[0] - point[0]) + Math.abs(start[1] - point[1]);
            }
        }
        return heuristic;
    }

    private static boolean isValidMove(int[][] grid, int row, int col) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && grid[row][col] == 0;
    }

    private static List<int[]> constructPath(Node node) {
        List<int[]> path = new ArrayList<>();
        while (node != null) {
            path.add(new int[]{node.getRow(), node.getCol()});
            node = node.getParent();
        }
        Collections.reverse(path);
        return path;
    }
}
