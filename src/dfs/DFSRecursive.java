package dfs;

import graph.Graph;
import java.util.*;

public class DFSRecursive<T> {
    private Set<T> visited;
    private List<T> traversalOrder;
    private Map<T, T> parent;
    
    public DFSRecursive() {
        this.visited = new HashSet<>();
        this.traversalOrder = new ArrayList<>();
        this.parent = new HashMap<>();
    }
    
    public List<T> dfs(Graph<T> graph, T start) {
        visited.clear();
        traversalOrder.clear();
        parent.clear();
        
        dfsVisit(graph, start, null);
        return new ArrayList<>(traversalOrder);
    }
    
    private void dfsVisit(Graph<T> graph, T vertex, T parentVertex) {
        visited.add(vertex);
        traversalOrder.add(vertex);
        parent.put(vertex, parentVertex);
        
        // Get neighbors in sorted order
        List<T> neighbors = graph.getNeighbors(vertex);
        Collections.sort((List) neighbors);
        
        for (T neighbor : neighbors) {
            if (!visited.contains(neighbor)) {
                dfsVisit(graph, neighbor, vertex);
            }
        }
    }
    
    public Map<T, T> getParentMap() {
        return new HashMap<>(parent);
    }
}
