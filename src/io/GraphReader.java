package io;

import graph.AdjacencyListGraph;
import graph.Graph;
import java.io.*;
import java.util.*;

public class GraphReader {
    
    public static Graph<String> readEdgeList(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        
        // First line: n m directed(0/1) weighted(0/1)
        String firstLine = reader.readLine().trim();
        String[] parts = firstLine.split("\\s+");
        
        int n = Integer.parseInt(parts[0]); // Number of vertices
        int m = Integer.parseInt(parts[1]); // Number of edges
        boolean directed = parts[2].equals("1");
        boolean weighted = parts.length > 3 && parts[3].equals("1");
        
        Graph<String> graph = new AdjacencyListGraph<>(directed, weighted);
        
        // Add vertices (0 to n-1 or string names)
        for (int i = 0; i < n; i++) {
            graph.addVertex(String.valueOf(i));
        }
        
        // Read edges
        for (int i = 0; i < m; i++) {
            String line = reader.readLine().trim();
            String[] edgeParts = line.split("\\s+");
            
            if (weighted && edgeParts.length >= 3) {
                String u = edgeParts[0];
                String v = edgeParts[1];
                double weight = Double.parseDouble(edgeParts[2]);
                graph.addEdge(u, v, weight);
            } else if (!weighted && edgeParts.length >= 2) {
                String u = edgeParts[0];
                String v = edgeParts[1];
                graph.addEdge(u, v);
            }
        }
        
        reader.close();
        return graph;
    }
    
    public static Graph<String> readAdjacencyList(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        
        // First line: n directed(0/1)
        String firstLine = reader.readLine().trim();
        String[] parts = firstLine.split("\\s+");
        
        int n = Integer.parseInt(parts[0]);
        boolean directed = parts[1].equals("1");
        // Assume unweighted for adjacency list format
        boolean weighted = false;
        
        Graph<String> graph = new AdjacencyListGraph<>(directed, weighted);
        
        for (int i = 0; i < n; i++) {
            String line = reader.readLine().trim();
            String[] vertexParts = line.split("[:\\s]+");
            
            if (vertexParts.length > 0) {
                String vertex = vertexParts[0].replace(":", "");
                graph.addVertex(vertex);
                
                for (int j = 1; j < vertexParts.length; j++) {
                    if (!vertexParts[j].isEmpty()) {
                        graph.addEdge(vertex, vertexParts[j]);
                    }
                }
            }
        }
        
        reader.close();
        return graph;
    }
    
    public static Graph<Integer> readEdgeListAsInteger(String filename) throws IOException {
        // Similar to readEdgeList but returns Integer vertices
        Graph<String> stringGraph = readEdgeList(filename);
        Graph<Integer> intGraph = new AdjacencyListGraph<>(
            stringGraph.isDirected(), stringGraph.isWeighted());
        
        // Convert vertices from String to Integer
        // This is simplified - in real implementation, you'd need to transfer all edges
        return intGraph;
    }
}
