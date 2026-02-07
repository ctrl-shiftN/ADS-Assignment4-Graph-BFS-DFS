package bfs;

import graph.Graph;
import java.util.*;

public class ShortestPath<T> {
    
    public List<T> shortestPathUnweighted(Graph<T> graph, T source, T target) {
        BFS<T> bfs = new BFS<>();
        BFS.BFSTraversalResult<T> result = bfs.bfs(graph, source);
        
        // Check if target is reachable
        if (!result.parents.containsKey(target)) {
            return new ArrayList<>(); // Empty path for unreachable
        }
        
        // Reconstruct path from target to source
        List<T> path = new ArrayList<>();
        T current = target;
        
        while (current != null) {
            path.add(current);
            current = result.parents.get(current);
        }
        
        // Reverse to get source -> target
        Collections.reverse(path);
        return path;
    }
    
    public void printShortestPath(List<T> path) {
        if (path.isEmpty()) {
            System.out.println("No path exists");
            return;
        }
        
        System.out.println("Shortest path length: " + (path.size() - 1));
        System.out.print("Path: ");
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i));
            if (i < path.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }
}
