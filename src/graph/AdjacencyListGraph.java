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
    public boolean removeVertex(T vertex) {
        if (!hasVertex(vertex)) {
            return false;
        }
        
        // Remove all edges to this vertex
        int edgesRemoved = 0;
        for (T other : adjacencyMap.keySet()) {
            if (!other.equals(vertex)) {
                List<Edge<T>> edges = adjacencyMap.get(other);
                edges.removeIf(edge -> edge.getDestination().equals(vertex));
                edgesRemoved++;
            }
        }
        
        // Remove the vertex itself
        edgeCount -= (adjacencyMap.get(vertex).size() + edgesRemoved);
        adjacencyMap.remove(vertex);
        return true;
    }
    
    @Override
    public void addEdge(T source, T destination) {
        addEdge(source, destination, 1.0);
    }
    
    @Override
    public void addEdge(T source, T destination, double weight) {
        if (!weighted && weight != 1.0) {
            throw new IllegalArgumentException("Non-weighted graph requires weight = 1.0");
        }
        
        addVertex(source);
        addVertex(destination);
        
        // Add forward edge
        adjacencyMap.get(source).add(new Edge<>(destination, weight));
        
        // If undirected and not self-loop, add reverse edge
        if (!directed && !source.equals(destination)) {
            adjacencyMap.get(destination).add(new Edge<>(source, weight));
        }
        
        edgeCount++;
    }
    
    @Override
    public boolean removeEdge(T source, T destination) {
        if (!hasVertex(source) || !hasVertex(destination)) {
            return false;
        }
        
        List<Edge<T>> sourceEdges = adjacencyMap.get(source);
        boolean removed = sourceEdges.removeIf(edge -> edge.getDestination().equals(destination));
        
        if (removed) {
            edgeCount--;
        }
        
        // If undirected, remove reverse edge too
        if (!directed && !source.equals(destination)) {
            List<Edge<T>> destEdges = adjacencyMap.get(destination);
            destEdges.removeIf(edge -> edge.getDestination().equals(source));
        }
        
        return removed;
    }
    
    @Override
    public List<T> getNeighbors(T vertex) {
        if (!hasVertex(vertex)) {
            return new ArrayList<>();
        }
        
        List<T> neighbors = new ArrayList<>();
        for (Edge<T> edge : adjacencyMap.get(vertex)) {
            neighbors.add(edge.getDestination());
        }
        
        // Sort for deterministic output (natural ordering if Comparable)
        if (!neighbors.isEmpty() && neighbors.get(0) instanceof Comparable) {
            Collections.sort((List) neighbors);
        }
        
        return neighbors;
    }
    
    @Override
    public boolean hasVertex(T vertex) {
        return adjacencyMap.containsKey(vertex);
    }
    
    @Override
    public boolean hasEdge(T source, T destination) {
        if (!hasVertex(source) || !hasVertex(destination)) {
            return false;
        }
        
        for (Edge<T> edge : adjacencyMap.get(source)) {
            if (edge.getDestination().equals(destination)) {
                return true;
            }
        }
        
        return false;
    }
    
    @Override
    public int getVertexCount() {
        return adjacencyMap.size();
    }
    
    @Override
    public int getEdgeCount() {
        return edgeCount;
    }
    
    @Override
    public boolean isDirected() {
        return directed;
    }
    
    @Override
    public boolean isWeighted() {
        return weighted;
    }
    
    @Override
    public Double getEdgeWeight(T source, T destination) {
        if (!hasEdge(source, destination)) {
            return null;
        }
        
        for (Edge<T> edge : adjacencyMap.get(source)) {
            if (edge.getDestination().equals(destination)) {
                return edge.getWeight();
            }
        }
        
        return null;
    }
    
    @Override
    public int[][] toAdjacencyMatrix() {
        List<T> vertices = new ArrayList<>(adjacencyMap.keySet());
        Collections.sort((List) vertices); // Ensure consistent ordering
        
        int n = vertices.size();
        int[][] matrix = new int[n][n];
        
        Map<T, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indexMap.put(vertices.get(i), i);
        }
        
        for (int i = 0; i < n; i++) {
            T vertex = vertices.get(i);
            for (Edge<T> edge : adjacencyMap.get(vertex)) {
                int j = indexMap.get(edge.getDestination());
                matrix[i][j] = 1;
            }
        }
        
        return matrix;
    }
    
    @Override
    public double[][] toWeightedAdjacencyMatrix() {
        List<T> vertices = new ArrayList<>(adjacencyMap.keySet());
        Collections.sort((List) vertices);
        
        int n = vertices.size();
        double[][] matrix = new double[n][n];
        
        Map<T, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indexMap.put(vertices.get(i), i);
        }
        
        // Initialize with infinity (no edge)
        for (int i = 0; i < n; i++) {
            Arrays.fill(matrix[i], Double.POSITIVE_INFINITY);
        }
        
        // Fill with weights
        for (int i = 0; i < n; i++) {
            T vertex = vertices.get(i);
            for (Edge<T> edge : adjacencyMap.get(vertex)) {
                int j = indexMap.get(edge.getDestination());
                matrix[i][j] = edge.getWeight();
            }
        }
        
        return matrix;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        List<T> vertices = new ArrayList<>(adjacencyMap.keySet());
        Collections.sort((List) vertices);
        
        for (T vertex : vertices) {
            sb.append(vertex).append(": ");
            List<Edge<T>> edges = adjacencyMap.get(vertex);
            edges.sort(Comparator.comparing(Edge::getDestination));
            
            for (Edge<T> edge : edges) {
                sb.append(edge.toString()).append(" ");
            }
            sb.append("\n");
        }
        
        return sb.toString();
    }
}
