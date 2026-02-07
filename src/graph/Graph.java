package graph;

import java.util.List;

public interface Graph<T> {
    // Vertex operations
    void addVertex(T vertex);
    boolean removeVertex(T vertex);
    
    // Edge operations
    void addEdge(T source, T destination);
    void addEdge(T source, T destination, double weight);
    boolean removeEdge(T source, T destination);
    
    // Queries
    List<T> getNeighbors(T vertex);
    boolean hasVertex(T vertex);
    boolean hasEdge(T source, T destination);
    int getVertexCount();
    int getEdgeCount();
    boolean isDirected();
    boolean isWeighted();
    Double getEdgeWeight(T source, T destination);
    
    // Export
    int[][] toAdjacencyMatrix();
    double[][] toWeightedAdjacencyMatrix();
}
