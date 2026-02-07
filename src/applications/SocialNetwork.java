package applications;

import bfs.BFS;
import bfs.ShortestPath;
import graph.AdjacencyListGraph;
import graph.Graph;
import java.util.*;

public class SocialNetwork {
    
    private Graph<String> friendshipGraph;
    
    public SocialNetwork() {
        // Undirected, unweighted graph for friendships
        friendshipGraph = new AdjacencyListGraph<>(false, false);
    }
    
    public void addFriendship(String person1, String person2) {
        friendshipGraph.addEdge(person1, person2);
    }
    
    // (i) All people within distance ≤ 2
    public Set<String> getPeopleWithinDistance2(String person) {
        Set<String> result = new HashSet<>();
        
        if (!friendshipGraph.hasVertex(person)) {
            return result;
        }
        
        BFS<String> bfs = new BFS<>();
        BFS.BFSTraversalResult<String> bfsResult = bfs.bfs(friendshipGraph, person);
        
        for (Map.Entry<String, Integer> entry : bfsResult.distances.entrySet()) {
            if (entry.getValue() <= 2) {
                result.add(entry.getKey());
            }
        }
        
        return result;
    }
    
    // (ii) Shortest path to target using BFS
    public List<String> getShortestPath(String source, String target) {
        ShortestPath<String> shortestPath = new ShortestPath<>();
        return shortestPath.shortestPathUnweighted(friendshipGraph, source, target);
    }
    
    public void printSocialCircle(String person) {
        System.out.println("Social circle for " + person + " (distance ≤ 2):");
        Set<String> circle = getPeopleWithinDistance2(person);
        List<String> sortedCircle = new ArrayList<>(circle);
        Collections.sort(sortedCircle);
        
        for (String friend : sortedCircle) {
            if (!friend.equals(person)) {
                System.out.println("  - " + friend);
            }
        }
        System.out.println("Total: " + (circle.size() - 1) + " people\n");
    }
    
    public static void main(String[] args) {
        SocialNetwork network = new SocialNetwork();
        
        // Example friendships
        network.addFriendship("Alice", "Bob");
        network.addFriendship("Alice", "Charlie");
        network.addFriendship("Bob", "David");
        network.addFriendship("Charlie", "Eve");
        network.addFriendship("David", "Frank");
        network.addFriendship("Eve", "Frank");
        
        network.printSocialCircle("Alice");
        
        List<String> path = network.getShortestPath("Alice", "Frank");
        System.out.println("Shortest path from Alice to Frank:");
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i));
            if (i < path.size() - 1) {
                System.out.print(" → ");
            }
        }
        System.out.println("\nPath length: " + (path.size() - 1));
    }
}
