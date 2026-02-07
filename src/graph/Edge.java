package graph;

public class Edge<T> {
    private T destination;
    private double weight;
    
    public Edge(T destination, double weight) {
        this.destination = destination;
        this.weight = weight;
    }
    
    public T getDestination() {
        return destination;
    }
    
    public double getWeight() {
        return weight;
    }
    
    @Override
    public String toString() {
        return "-> " + destination + " (" + weight + ")";
    }
}
