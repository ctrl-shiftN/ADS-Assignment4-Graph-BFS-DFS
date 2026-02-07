  package dfs;

import graph.Graph;
import java.util.*;

public class CycleDetector<T> {
    
    // For undirected graphs
    public boolean hasCycleUndirected(Graph<T> graph) {
        if (graph.isDirected()) {
            throw new IllegalArgumentException("Use hasCycleDirected for directed graphs");
        }
        
        Set<T> visited = new HashSet<>();
        
        for (T vertex : getAllVertices(graph)) {
            if (!visited.contains(vertex)) {
                if (hasCycleUndirectedDFS(graph, vertex, null, visited)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private boolean hasCycleUndirectedDFS(Graph<T> graph, T vertex, T parent, Set<T> visited) {
        visited.add(vertex);
        
        for (T neighbor : graph.getNeighbors(vertex)) {
            if (!visited.contains(neighbor)) {
                if (hasCycleUndirectedDFS(graph, neighbor, vertex, visited)) {
                    return true;
                }
            } else if (!neighbor.equals(parent)) {
                // If neighbor is visited and not parent, cycle exists
                return true;
            }
        }
        
        return false;
    }
    
    // For directed graphs
    public boolean hasCycleDirected(Graph<T> graph) {
        if (!graph.isDirected()) {
            throw new IllegalArgumentException("Use hasCycleUndirected for undirected graphs");
        }
        
        Set<T> visited = new HashSet<>();
        Set<T> recursionStack = new HashSet<>();
        
        for (T vertex : getAllVertices(graph)) {
            if (!visited.contains(vertex)) {
                if (hasCycleDirectedDFS(graph, vertex, visited, recursionStack)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private boolean hasCycleDirectedDFS(Graph<T> graph, T vertex, Set<T> visited, Set<T> recursionStack) {
        visited.add(vertex);
        recursionStack.add(vertex);
        
        for (T neighbor : graph.getNeighbors(vertex)) {
            if (!visited.contains(neighbor)) {
                if (hasCycleDirectedDFS(graph, neighbor, visited, recursionStack)) {
                    return true;
                }
            } else if (recursionStack.contains(neighbor)) {
                // If neighbor is in recursion stack, cycle exists
                return true;
            }
        }
        
        recursionStack.remove(vertex);
        return false;
    }
    
    private Set<T> getAllVertices(Graph<T> graph) {
        // This is a helper method - in real implementation, you'd need
        // to add a method to Graph interface to get all vertices
        Set<T> vertices = new HashSet<>();
        
        // For now, we'll assume we can get vertices somehow
        // In your actual code, you might need to modify Graph interface
        return vertices;
    }
}
