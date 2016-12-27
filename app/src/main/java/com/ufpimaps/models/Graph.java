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

    public Node getNodeByName(String name){

        for(Node n: nodes){
            if(name.equals(n.getName()))
                return n;

        }
        return null;
    }

    public void addEdge(String laneId, String noOrigem, String noDestino,
                        double distance){
        Edge lane = new Edge(laneId, getNodeByName(noOrigem), getNodeByName(noDestino), distance );
        edges.add(lane);
        Edge lane2 = new Edge(laneId+999, getNodeByName(noDestino), getNodeByName(noOrigem), distance );
        edges.add(lane2);

    }
}
