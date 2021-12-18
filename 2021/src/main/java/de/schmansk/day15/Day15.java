package de.schmansk.day15;

import de.schmansk.Coordinate;
import de.schmansk.FileTools;
import util.navigation.Graph;
import util.navigation.Node;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day15 {


    public int starOne(Path pathToInput) {
        Integer[][] routesAsString = FileTools.readIntoMatrix(pathToInput,"");



        Map<String, Node> nodes = new HashMap<>();
        for (int i = 0; i <routesAsString.length ; i++) {
            for (int j = 0; j < routesAsString[i].length; j++) {
                Node node = new Node(i+":"+j);
                node.setSomeValue(routesAsString[i][j]);
                nodes.put(node.getName(),node);
            }
        }
        for (int i = 0; i <routesAsString.length ; i++) {
            for (int j = 0; j <routesAsString[i].length ; j++) {
                Coordinate coord = new Coordinate(i,j);
                List<Coordinate> adjacentFour = Coordinate.getAdjacentFour(coord, routesAsString);
                Node currentNode = nodes.get(coord.getI()+":"+coord.getJ());
                for (Coordinate connection : adjacentFour) {
                    String nodeKey = connection.getI() + ":" + connection.getJ();
                    currentNode.addConnection(nodes.get(nodeKey));
                }

            }

        }
        Coordinate start = new Coordinate(0,0);
        Coordinate end = new Coordinate(routesAsString.length-1,routesAsString[routesAsString.length-1].length-1);
        Node startNode = nodes.get(start.getI()+":"+start.getJ());
        Node endNode = nodes.get(end.getI()+":"+end.getJ());
        Graph graph = new Graph(nodes.get(startNode.getName()), nodes.get(endNode.getName()), new ArrayList<>(nodes.values())) {


            @Override
            public Integer determineRouteLength(List<String> currentRoute) {
                int sum = currentRoute.stream().map(nodes::get).map(Node::getSomeValue).mapToInt(Integer::intValue).sum();
                return sum;
            }

            @Override
            public boolean canConnect(Node from, Node to, List<String> currentRoute) {

                Integer toYCoordinate = Integer.parseInt(to.getName().trim().split(":")[0]);
                Integer fromYCoordinate = Integer.parseInt(from.getName().trim().split(":")[0]);
                if(currentRoute.contains(to.getName()) || fromYCoordinate>toYCoordinate){
                    return false;
                }
                return true;
            }
        };
        graph.setOnlyShortestRoute(true);
        graph.findRoutes();
        return 0;
    }



}
