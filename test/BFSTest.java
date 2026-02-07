import graph.AdjacencyListGraph;
import graph.Graph;
import bfs.BFS;
import bfs.ShortestPath;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class BFSTest {
    
    @Test
    public void testBFSOnSimpleGraph() {
        Graph<Integer> g = new AdjacencyListGraph<>(false, false);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 3);
        
        BFS<Integer> bfs = new BFS<>();
        BFS.BFSTraversalResult<Integer> result = bfs.bfs(g, 0);
        
        assertEquals(4, result.traversalOrder.size());
        assertEquals(0, result.distances.get(0));
        assertEquals(1, result.distances.get(1));
        assertEquals(1, result.distances.get(2));
        assertEquals(2, result.distances.get(3));
    }
    
    @Test
    public void testBFSDisconnected() {
        Graph<String> g = new AdjacencyListGraph<>(false, false);
        g.addEdge("A", "B");
        g.addEdge("C", "D"); // Separate component
        
        BFS<String> bfs = new BFS<>();
        BFS.BFSTraversalResult<String> result = bfs.bfs(g, "A");
        
        // Should only reach A and B
        assertEquals(2, result.traversalOrder.size());
        assertTrue(result.traversalOrder.contains("A"));
        assertTrue(result.traversalOrder.contains("B"));
        assertFalse(result.traversalOrder.contains("C"));
    }
    
    @Test
    public void testShortestPath() {
        Graph<Integer> g = new AdjacencyListGraph<>(false, false);
        // 0-1-2-3 line
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        // Shortcut: 0-3
        g.addEdge(0, 3);
        
        ShortestPath<Integer> sp = new ShortestPath<>();
        List<Integer> path = sp.shortestPathUnweighted(g, 0, 3);
        
        // Should take direct edge 0-3 (length 1) not 0-1-2-3 (length 3)
        assertEquals(2, path.size()); // 0 and 3
        assertEquals(0, path.get(0));
        assertEquals(3, path.get(1));
    }
    
    @Test
    public void testUnreachablePath() {
        Graph<String> g = new AdjacencyListGraph<>(false, false);
        g.addEdge("A", "B");
        g.addEdge("C", "D"); // Separate component
        
        ShortestPath<String> sp = new ShortestPath<>();
        List<String> path = sp.shortestPathUnweighted(g, "A", "C");
        
        // Should return empty list for unreachable
        assertTrue(path.isEmpty());
    }
    
    @Test
    public void testBFSSingleVertex() {
        Graph<Integer> g = new AdjacencyListGraph<>(false, false);
        g.addVertex(5);
        
        BFS<Integer> bfs = new BFS<>();
        BFS.BFSTraversalResult<Integer> result = bfs.bfs(g, 5);
        
        assertEquals(1, result.traversalOrder.size());
        assertEquals(5, result.traversalOrder.get(0));
        assertEquals(0, result.distances.get(5));
    }
    
    @Test
    public void testBFSCycle() {
        Graph<Integer> g = new AdjacencyListGraph<>(false, false);
        // Triangle cycle
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        
        BFS<Integer> bfs = new BFS<>();
        BFS.BFSTraversalResult<Integer> result = bfs.bfs(g, 0);
        
        assertEquals(3, result.traversalOrder.size());
        // All vertices should be visited
        assertTrue(result.traversalOrder.contains(0));
        assertTrue(result.traversalOrder.contains(1));
        assertTrue(result.traversalOrder.contains(2));
    }
}
