import graph.*;
import bfs.*;
import dfs.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Graph, BFS and DFS Assignment ===");
        
        // Example: Create an undirected, unweighted graph
        Graph<Integer> graph = new AdjacencyListGraph<>(false, false);
        
        // Add vertices and edges
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(2, 5);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        
        System.out.println("Graph created with " + graph.getVertexCount() + 
                         " vertices and " + graph.getEdgeCount() + " edges");
        
        // BFS Example
        System.out.println("\n=== BFS ===");
        BFS<Integer> bfs = new BFS<>();
        BFS.BFSTraversalResult<Integer> bfsResult = bfs.bfs(graph, 0);
        bfs.printTraversal(bfsResult);
        
        // Shortest Path Example
        System.out.println("\n=== Shortest Path ===");
        ShortestPath<Integer> sp = new ShortestPath<>();
        List<Integer> path = sp.shortestPathUnweighted(graph, 0, 5);
        sp.printShortestPath(path);
        
        // DFS Example
        System.out.println("\n=== DFS Recursive ===");
        DFSRecursive<Integer> dfsRecursive = new DFSRecursive<>();
        List<Integer> dfsOrder = dfsRecursive.dfs(graph, 0);
        System.out.println("DFS Order: " + dfsOrder);
        
        System.out.println("\n=== DFS Iterative ===");
        DFSIterative<Integer> dfsIterative = new DFSIterative<>();
        List<Integer> dfsIterOrder = dfsIterative.dfs(graph, 0);
        System.out.println("DFS Iterative Order: " + dfsIterOrder);
        
        // Adjacency Matrix
        System.out.println("\n=== Adjacency Matrix ===");
        int[][] matrix = graph.toAdjacencyMatrix();
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}
