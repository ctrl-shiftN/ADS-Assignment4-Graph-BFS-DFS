import graph.AdjacencyListGraph;
import graph.Graph;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {
    
    @Test
    public void testEmptyGraph() {
        Graph<String> g = new AdjacencyListGraph<>(false, false);
        assertEquals(0, g.getVertexCount());
        assertEquals(0, g.getEdgeCount());
        assertFalse(g.hasVertex("A"));
    }
    
    @Test
    public void testSingleVertex() {
        Graph<Integer> g = new AdjacencyListGraph<>(true, true);
        g.addVertex(1);
        assertEquals(1, g.getVertexCount());
        assertTrue(g.hasVertex(1));
        assertEquals(0, g.getEdgeCount());
    }
    
    @Test
    public void testAddRemoveEdgeUndirected() {
        Graph<String> g = new AdjacencyListGraph<>(false, false);
        g.addEdge("A", "B");
        
        assertEquals(2, g.getVertexCount());
        assertEquals(1, g.getEdgeCount());
        assertTrue(g.hasEdge("A", "B"));
        assertTrue(g.hasEdge("B", "A")); // Undirected
        
        g.removeEdge("A", "B");
        assertEquals(0, g.getEdgeCount());
        assertFalse(g.hasEdge("A", "B"));
    }
    
    @Test
    public void testAddRemoveEdgeDirected() {
        Graph<String> g = new AdjacencyListGraph<>(true, false);
        g.addEdge("A", "B");
        
        assertTrue(g.hasEdge("A", "B"));
        assertFalse(g.hasEdge("B", "A")); // Directed, so reverse doesn't exist
        
        assertEquals(1, g.getEdgeCount());
    }
    
    @Test
    public void testNeighborsOrder() {
        Graph<Integer> g = new AdjacencyListGraph<>(false, false);
        g.addEdge(0, 3);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        
        // Neighbors should be sorted
        assertEquals("[1, 2, 3]", g.getNeighbors(0).toString());
    }
    
    @Test
    public void testWeightedEdge() {
        Graph<String> g = new AdjacencyListGraph<>(false, true);
        g.addEdge("A", "B", 5.0);
        
        assertEquals(5.0, g.getEdgeWeight("A", "B"));
        assertTrue(g.isWeighted());
    }
    
    @Test
    public void testAdjacencyMatrix() {
        Graph<Integer> g = new AdjacencyListGraph<>(false, false);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        
        int[][] matrix = g.toAdjacencyMatrix();
        assertEquals(3, matrix.length); // 3 vertices
        
        // Check symmetry for undirected graph
        assertEquals(matrix[0][1], matrix[1][0]);
        assertEquals(matrix[0][2], matrix[2][0]);
        assertEquals(matrix[1][2], matrix[2][1]);
    }
    
    @Test
    public void testDisconnectedGraph() {
        Graph<String> g = new AdjacencyListGraph<>(false, false);
        g.addEdge("A", "B"); // Component 1
        g.addEdge("C", "D"); // Component 2
        
        assertEquals(4, g.getVertexCount());
        assertEquals(2, g.getEdgeCount());
        assertFalse(g.hasEdge("A", "C")); // Different components
    }
}
