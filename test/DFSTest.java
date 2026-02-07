import graph.AdjacencyListGraph;
import graph.Graph;
import dfs.DFSRecursive;
import dfs.DFSIterative;
import dfs.CycleDetector;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class DFSTest {
    
    @Test
    public void testDFSRecursive() {
        Graph<Integer> g = new AdjacencyListGraph<>(false, false);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 3);
        
        DFSRecursive<Integer> dfs = new DFSRecursive<>();
        List<Integer> traversal = dfs.dfs(g, 0);
        
        assertEquals(4, traversal.size());
        assertEquals(0, traversal.get(0)); // Start from 0
        // All vertices should be visited
        assertTrue(traversal.contains(0));
        assertTrue(traversal.contains(1));
        assertTrue(traversal.contains(2));
        assertTrue(traversal.contains(3));
    }
    
    @Test
    public void testDFSIterative() {
        Graph<String> g = new AdjacencyListGraph<>(true, false);
        g.addEdge("A", "B");
        g.addEdge("A", "C");
        g.addEdge("B", "D");
        g.addEdge("C", "D");
        
        DFSIterative<String> dfs = new DFSIterative<>();
        List<String> traversal = dfs.dfs(g, "A");
        
        assertEquals(4, traversal.size());
        assertEquals("A", traversal.get(0));
        assertTrue(traversal.containsAll(List.of("A", "B", "C", "D")));
    }
    
    @Test
    public void testCompareRecursiveIterative() {
        Graph<Integer> g = new AdjacencyListGraph<>(false, false);
        // Simple line: 0-1-2-3
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        
        DFSRecursive<Integer> dfsRec = new DFSRecursive<>();
        DFSIterative<Integer> dfsIter = new DFSIterative<>();
        
        List<Integer> recResult = dfsRec.dfs(g, 0);
        List<Integer> iterResult = dfsIter.dfs(g, 0);
        
        // Both should visit all vertices
        assertEquals(4, recResult.size());
        assertEquals(4, iterResult.size());
        // Both should contain all vertices
        assertTrue(recResult.containsAll(List.of(0, 1, 2, 3)));
        assertTrue(iterResult.containsAll(List.of(0, 1, 2, 3)));
    }
    
    @Test
    public void testCycleDetectionUndirected() {
        Graph<Integer> g = new AdjacencyListGraph<>(false, false);
        // No cycle: tree
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        
        CycleDetector<Integer> detector = new CycleDetector<>();
        assertFalse(detector.hasCycleUndirected(g));
        
        // Add cycle
        g.addEdge(3, 0);
        assertTrue(detector.hasCycleUndirected(g));
    }
    
    @Test
    public void testCycleDetectionDirected() {
        Graph<String> g = new AdjacencyListGraph<>(true, false);
        // A -> B -> C -> A (cycle)
        g.addEdge("A", "B");
        g.addEdge("B", "C");
        g.addEdge("C", "A");
        
        CycleDetector<String> detector = new CycleDetector<>();
        assertTrue(detector.hasCycleDirected(g));
        
        // Remove cycle
        g.removeEdge("C", "A");
        // Now A -> B -> C (no cycle)
        assertFalse(detector.hasCycleDirected(g));
    }
    
    @Test
    public void testDFSOnEmptyGraph() {
        Graph<Integer> g = new AdjacencyListGraph<>(false, false);
        g.addVertex(0);
        
        DFSRecursive<Integer> dfs = new DFSRecursive<>();
        List<Integer> traversal = dfs.dfs(g, 0);
        
        assertEquals(1, traversal.size());
        assertEquals(0, traversal.get(0));
    }
    
    @Test
    public void testDFSMultipleComponents() {
        Graph<String> g = new AdjacencyListGraph<>(false, false);
        g.addEdge("A", "B"); // Component 1
        g.addEdge("C", "D"); // Component 2
        
        DFSRecursive<String> dfs = new DFSRecursive<>();
        List<String> traversal = dfs.dfs(g, "A");
        
        // Should only visit component 1
        assertEquals(2, traversal.size());
        assertTrue(traversal.contains("A"));
        assertTrue(traversal.contains("B"));
        assertFalse(traversal.contains("C"));
    }
}
