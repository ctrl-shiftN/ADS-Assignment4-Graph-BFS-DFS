package bfs;

import graph.Graph;
import java.util.*;

public class BFS<T> {
    
    public static class BFSTraversalResult<T> {
        public List<T> traversalOrder;
        public Map<T, Integer> distances;
        public Map<T, T> parents;
        
        public BFSTraversalResult() {
            this.traversalOrder = new ArrayList<>();
            this.distances = new HashMap<>();
            this.parents = new HashMap<>();
        }
    }
    
    public BFSTraversalResult<T> bfs(Graph<T> graph, T start) {
        BFSTraversalResult<T> result = new BFSTraversalResult<>();
        
        if (!graph.hasVertex(start)) {
            return result;
        }
        
        Queue<T> queue = new LinkedList<>();
        Set<T> visited = new HashSet<>();
        
        // Initialize
        queue.add(start);
        visited.add(start);
        result.distances.put(start, 0);
        result.parents.put(start, null);
        
        while (!queue.isEmpty()) {
            T current = queue.poll();
            result.traversalOrder.add(current);
            
            // Get neighbors in sorted order for deterministic output
            List<T> neighbors = graph.getNeighbors(current);
            Collections.sort((List) neighbors);
            
            for (T neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                    result.distances.put(neighbor, result.distances.get(current) + 1);
                    result.parents.put(neighbor, current);
                }
            }
        }
        
        return result;
    }
    
    public void printTraversal(BFSTraversalResult<T> result) {
        System.out.println("BFS Traversal Order: " + result.traversalOrder);
        System.out.println("Distances: " + result.distances);
    }
}
