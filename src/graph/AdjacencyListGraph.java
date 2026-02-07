package graph;

import java.util.*;

public class AdjacencyListGraph<T> implements Graph<T> {
    private final Map<T, List<Edge<T>>> adjacencyMap;
    private final boolean directed;
    private final boolean weighted;
    private int edgeCount;
    
    public AdjacencyListGraph(boolean directed, boolean weighted) {
        this.adjacencyMap = new HashMap<>();
        this.directed = directed;
        this.weighted = weighted;
        this.edgeCount = 0;
    }
    
    @Override
    public void addVertex(T vertex) {
        if (!hasVertex(vertex)) {
            adjacencyMap.put(vertex, new ArrayList<>());
        }
    }
    
    @Override
    public boolean hasVertex(T vertex) {
        return adjacencyMap.containsKey(vertex);
    }
    
    // TO BE CONTINUED - We'll add more methods one by one
    @Override
    public void addEdge(T source, T destination) {
        addEdge(source, destination, 1.0);
    }
    
    @Override
    public void addEdge(T source, T destination, double weight) {
        // We'll implement this next
        System.out.println("TODO: Implement addEdge with weight");
    }
    
    // Add other empty methods for now
    @Override public boolean removeVertex(T vertex) { return false; }
    @Override public boolean removeEdge(T source, T destination) { return false; }
    @Override public List<T> getNeighbors(T vertex) { return new ArrayList<>(); }
    @Override public boolean hasEdge(T source, T destination) { return false; }
    @Override public int getVertexCount() { return adjacencyMap.size(); }
    @Override public int getEdgeCount() { return edgeCount; }
    @Override public boolean isDirected() { return directed; }
    @Override public boolean isWeighted() { return weighted; }
    @Override public Double getEdgeWeight(T source, T destination) { return null; }
    @Override public int[][] toAdjacencyMatrix() { return new int[0][0]; }
    @Override public double[][] toWeightedAdjacencyMatrix() { return new double[0][0]; }
}
