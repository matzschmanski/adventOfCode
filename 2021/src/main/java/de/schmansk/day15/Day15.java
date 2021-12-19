package de.schmansk.day15;

import de.schmansk.Coordinate;
import de.schmansk.FileTools;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.LinkedList;
import java.util.Map.Entry;

public class Day15 {
    // Driver code

    private Integer[][] enlargeMatrix(Integer[][] oldMatrix){
        Integer[][] newMatrix = new Integer[oldMatrix.length*5][oldMatrix[0].length*5];
        for (int i = 0; i < oldMatrix.length ; i++) {
            for (int j = 0; j < oldMatrix[i].length ; j++) {
                newMatrix[i][j] = oldMatrix[i][j];
            }
        }
        //copy to bottom
        int additive =1;
        int oldI = 0;
        for (int i = oldMatrix.length; i <newMatrix.length ; i++) {
            for (int j = 0; j <oldMatrix[oldI].length ; j++) {
                newMatrix[i][j] = getNewValue(oldMatrix[oldI][j],additive);

            }
            oldI++;
            if(oldI==oldMatrix.length){
                oldI=0;
                additive++;
            }
        }
        //copy to the right
        newMatrix = populateToTheRight(newMatrix, oldMatrix[0].length,oldMatrix.length);
        return newMatrix;
    }
    private Integer[][] populateToTheRight(Integer[][] oldMatrix, int oldMatrixMaxX, int oldMatrixMaxY){
        Integer[][] newMatrix = new Integer[oldMatrix.length][oldMatrix[0].length];
        //copy to the right
        int oldI=0;
        int oldJ=0;
        int additive =0;
        int iAdditive =0;
        for (int i = 0; i <newMatrix.length ; i++) {
            for (int j = 0; j <oldMatrix[oldI].length ; j++) {
                    newMatrix[i][j] = getNewValue(oldMatrix[oldI][oldJ],additive+iAdditive);
                    oldJ++;
                    if(oldJ==oldMatrixMaxX){
                        oldJ=0;
                        additive++;
                    }
            }
            oldI++;
            additive=0;
            if(oldI==oldMatrixMaxY){
                oldI=0;
                iAdditive++;
            }

        }
        return newMatrix;
    }

    public Integer getNewValue(Integer oldValue, Integer additive){
        Integer newValue = oldValue + additive;
        if(newValue>=10){
            newValue = newValue-9;
        }
        return newValue;
    }
    public int starOne(Path pathToInput) {


        Integer[][] matrix = FileTools.readIntoMatrix(pathToInput, "");
        matrix = enlargeMatrix(matrix);

        // Adjacency list representation of the
        // connected edges
        Map<String,Node> nodes = new HashMap<>();

        // Initialize list for every node
        for (int i = 0; i <matrix.length ; i++) {
            for (int j = 0; j <matrix[i].length ; j++) {
                Node baseNode = new Node(i+":"+j);
                nodes.put(i+":"+j,baseNode);
            }
            }
        for (int i = 0; i <matrix.length ; i++) {
            for (int j = 0; j <matrix[i].length ; j++) {
                Coordinate coord = new Coordinate(i,j);
                Node baseNode = nodes.get(i+":"+j);
                List<Coordinate> adjacentFour = Coordinate.getAdjacentFour(coord, matrix);
                for (Coordinate coordToAdd : adjacentFour){

                    baseNode.addDestination(nodes.get(coordToAdd.getI()+":"+coordToAdd.getJ()), matrix[coordToAdd.getI()][coordToAdd.getJ()]);
                }

            }
        }

        Graph graph = new Graph();
        nodes.values().forEach(graph::addNode);
        graph = calculateShortestPathFromSource(graph, nodes.get("0:0"));
        int matrixSize = matrix.length-1;
        String value = matrixSize+":"+matrixSize;
        for (Node node : graph.getNodes()){
            if(node.getName().equals(value))
            {
                System.out.println(node.distance);
            }
        }
        return 0;
    }

    public static Graph calculateShortestPathFromSource(Graph graph, Node source) {

        source.setDistance(0);

        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();
        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Entry<Node, Integer> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {
                Node adjacentNode = adjacencyPair.getKey();
                Integer edgeWeigh = adjacencyPair.getValue();

                if (!settledNodes.contains(adjacentNode)) {
                    CalculateMinimumDistance(adjacentNode, edgeWeigh, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph;
    }

    private static void CalculateMinimumDistance(Node evaluationNode, Integer edgeWeigh, Node sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }

    private static Node getLowestDistanceNode(Set<Node> unsettledNodes) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Node node : unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    public class Graph {

        private Set<Node> nodes = new HashSet<>();

        public void addNode(Node nodeA) {
            nodes.add(nodeA);
        }

        public Set<Node> getNodes() {
            return nodes;
        }

        public void setNodes(Set<Node> nodes) {
            this.nodes = nodes;
        }
    }

    public class Node {

        private String name;

        private LinkedList<Node> shortestPath = new LinkedList<>();

        private Integer distance = Integer.MAX_VALUE;

        private Map<Node, Integer> adjacentNodes = new HashMap<>();

        public Node(String name) {
            this.name = name;
        }

        public void addDestination(Node destination, int distance) {
            adjacentNodes.put(destination, distance);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Map<Node, Integer> getAdjacentNodes() {
            return adjacentNodes;
        }

        public void setAdjacentNodes(Map<Node, Integer> adjacentNodes) {
            this.adjacentNodes = adjacentNodes;
        }

        public Integer getDistance() {
            return distance;
        }

        public void setDistance(Integer distance) {
            this.distance = distance;
        }

        public List<Node> getShortestPath() {
            return shortestPath;
        }

        public void setShortestPath(LinkedList<Node> shortestPath) {
            this.shortestPath = shortestPath;
        }

    }

}




