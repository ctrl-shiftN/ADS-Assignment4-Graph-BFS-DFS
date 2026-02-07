package applications;

import bfs.ShortestPath;
import graph.AdjacencyListGraph;
import graph.Graph;
import java.util.*;

public class MazeSolver {
    
    public static class Cell {
        public int row;
        public int col;
        
        public Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Cell cell = (Cell) obj;
            return row == cell.row && col == cell.col;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
        
        @Override
        public String toString() {
            return "(" + row + "," + col + ")";
        }
    }
    
    public static List<Cell> solveMaze(char[][] grid) {
        Cell start = null;
        Cell target = null;
        
        // Find start (S) and target (T)
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 'S') {
                    start = new Cell(i, j);
                } else if (grid[i][j] == 'T') {
                    target = new Cell(i, j);
                }
            }
        }
        
        if (start == null || target == null) {
            System.out.println("Error: Start (S) or Target (T) not found in maze");
            return new ArrayList<>();
        }
        
        // Build graph from grid
        Graph<Cell> graph = buildGraphFromGrid(grid);
        
        // Use BFS to find shortest path
        ShortestPath<Cell> shortestPath = new ShortestPath<>();
        return shortestPath.shortestPathUnweighted(graph, start, target);
    }
    
    private static Graph<Cell> buildGraphFromGrid(char[][] grid) {
        Graph<Cell> graph = new AdjacencyListGraph<>(false, false);
        int rows = grid.length;
        int cols = grid[0].length;
        
        // Add all passable cells as vertices
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] != '#') { // Not a wall
                    Cell cell = new Cell(i, j);
                    graph.addVertex(cell);
                }
            }
        }
        
        // Add edges between adjacent passable cells
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; // Down, Up, Right, Left
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] != '#') {
                    Cell current = new Cell(i, j);
                    
                    for (int[] dir : directions) {
                        int newRow = i + dir[0];
                        int newCol = j + dir[1];
                        
                        if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                            if (grid[newRow][newCol] != '#') {
                                Cell neighbor = new Cell(newRow, newCol);
                                graph.addEdge(current, neighbor);
                            }
                        }
                    }
                }
            }
        }
        
        return graph;
    }
    
    public static void printMazeWithPath(char[][] grid, List<Cell> path) {
        if (path.isEmpty()) {
            System.out.println("No path found!");
            return;
        }
        
        // Create a copy of the grid
        char[][] display = new char[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            System.arraycopy(grid[i], 0, display[i], 0, grid[i].length);
        }
        
        // Mark the path (excluding start and target)
        for (int i = 1; i < path.size() - 1; i++) {
            Cell cell = path.get(i);
            display[cell.row][cell.col] = '*';
        }
        
        // Print the maze
        System.out.println("Maze with shortest path (S=start, T=target, *=path, #=wall, .=free):");
        for (char[] row : display) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        // Example maze from assignment
        char[][] maze = {
            {'.', '.', '.', '#', '.', '.', '.'},
            {'.', '#', '.', '#', '.', '#', '.'},
            {'.', '#', '.', '.', '.', '#', '.'},
            {'.', '#', '#', '#', '.', '#', '.'},
            {'.', '.', '.', '.', '.', '#', '.'},
            {'#', '#', '#', '#', '.', '#', '.'},
            {'S', '.', '.', '.', '.', '.', 'T'}
        };
        
        System.out.println("=== Maze Solver - Part D Application ===");
        System.out.println("Finding shortest path from S to T using BFS...\n");
        
        List<Cell> path = solveMaze(maze);
        
        if (path.isEmpty()) {
            System.out.println("No path exists from start to target!");
        } else {
            System.out.println("Shortest path found!");
            System.out.println("Path length: " + (path.size() - 1) + " steps");
            System.out.println("Path coordinates:");
            for (int i = 0; i < path.size(); i++) {
                System.out.print(path.get(i));
                if (i < path.size() - 1) {
                    System.out.print(" → ");
                }
            }
            System.out.println("\n");
            
            printMazeWithPath(maze, path);
        }
    }
}    public List<Cell> solveMaze(char[][] grid, Cell start, Cell target) {
        // Build graph from grid
        Graph<Cell> graph = buildGraphFromGrid(grid);
        
        // Use BFS to find shortest path
        ShortestPath<Cell> shortestPath = new ShortestPath<>();
        return shortestPath.shortestPathUnweighted(graph, start, target);
    }
    
    private Graph<Cell> buildGraphFromGrid(char[][] grid) {
        // Implementation would convert grid to graph
        // For now, return null as placeholder
        return null;
    }
    
    public static void main(String[] args) {
        System.out.println("Maze Solver - Part D Application");
        // Example usage
    }
}
