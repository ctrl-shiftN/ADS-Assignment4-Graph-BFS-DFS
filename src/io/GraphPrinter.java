package io;

import graph.Graph;
import java.util.*;

public class GraphPrinter {
    
    public static void printGraphInfo(Graph<?> graph) {
        System.out.println("=== Graph Information ===");
        System.out.println("Directed: " + graph.isDirected());
        System.out.println("Weighted: " + graph.isWeighted());
        System.out.println("Vertices: " + graph.getVertexCount());
        System.out.println("Edges: " + graph.getEdgeCount());
        System.out.println();
    }
    
    public static <T> void printBFSTraversal(List<T> traversal) {
        System.out.println("BFS Traversal Order:");
        for (int i = 0; i < traversal.size(); i++) {
            System.out.print(traversal.get(i));
            if (i < traversal.size() - 1) {
                System.out.print(" → ");
            }
        }
        System.out.println("\n");
    }
    
    public static <T> void printDFSTraversal(List<T> traversal) {
        System.out.println("DFS Traversal Order:");
        for (int i = 0; i < traversal.size(); i++) {
            System.out.print(traversal.get(i));
            if (i < traversal.size() - 1) {
                System.out.print(" → ");
            }
        }
        System.out.println("\n");
    }
    
    public static <T> void printShortestPath(List<T> path) {
        if (path.isEmpty()) {
            System.out.println("No path exists between source and target.");
            return;
        }
        
        System.out.println("Shortest Path (length " + (path.size() - 1) + "):");
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i));
            if (i < path.size() - 1) {
                System.out.print(" → ");
            }
        }
        System.out.println("\n");
    }
    
    public static void printAdjacencyMatrix(int[][] matrix) {
        System.out.println("Adjacency Matrix:");
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }
    
    public static void printWeightedAdjacencyMatrix(double[][] matrix) {
        System.out.println("Weighted Adjacency Matrix:");
        for (double[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }
}
