package com.ufpimaps.models;
import java.util.List;
/**
 * Created by LIMCI on 26/12/2016.
 */

public class Graph {

    private final List<Node> nodes;
    private final List<Edge> edges;

    public Graph(List<Node> nodes, List<Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
