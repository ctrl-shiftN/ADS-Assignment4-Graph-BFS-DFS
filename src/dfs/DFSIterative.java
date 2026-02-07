package dfs;

import graph.Graph;
import java.util.*;

public class DFSIterative<T> {
    
    public List<T> dfs(Graph<T> graph, T start) {
        List<T> traversalOrder = new ArrayList<>();
        Set<T> visited = new HashSet<>();
        Stack<T> stack = new Stack<>();
        Map<T, T> parent = new HashMap<>();
        
        stack.push(start);
        parent.put(start, null);
        
        while (!stack.isEmpty()) {
            T current = stack.pop();
            
            if (!visited.contains(current)) {
                visited.add(current);
                traversalOrder.add(current);
                
                // Get neighbors in sorted order (reverse for stack)
                List<T> neighbors = graph.getNeighbors(current);
                Collections.sort((List) neighbors);
                Collections.reverse(neighbors);
                
                for (T neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                        parent.put(neighbor, current);
                    }
                }
            }
        }
        
        return traversalOrder;
    }
}
