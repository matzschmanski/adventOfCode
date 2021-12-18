package util.navigation;


import util.StringTools;

import java.util.*;

public abstract class Graph {
    Node start;
    Node end;
    List<Node> allNodes;

    private boolean onlyShortestRoute;
    private Integer shortestRouteLength;
    public Graph(Node start, Node end, List<Node> allNodes) {
        this.allNodes = allNodes;
        this.end = end;
        this.start = start;
        this.onlyShortestRoute=false;
        this.shortestRouteLength=-1;
    }

    public boolean isOnlyShortestRoute() {
        return onlyShortestRoute;
    }

    public void setOnlyShortestRoute(boolean onlyShortestRoute) {
        this.onlyShortestRoute = onlyShortestRoute;
    }

    public abstract Integer determineRouteLength(List<String> currentRoute);

    public List<String> findRoutes() {
        List<String> currentRoute = new ArrayList<>();
        currentRoute.add(start.getName());
        List<Node> connectionsTo = new ArrayList<>(start.getConnectionsTo());
        List<String> foundRoutes = new ArrayList<>();
        Map<String, Integer> visitCount = new HashMap<>();
        visitCount.put(start.getName(), visitCount.getOrDefault(start.getName(), 0) + 1);
        for (Node node : connectionsTo) {
            List<String> newRoute = new ArrayList<>(currentRoute);
            foundRoutes = followConnections(node, newRoute, foundRoutes);
        }
        System.out.println("done");
        return foundRoutes;

    }

    public int countInRoute(Node node, List<String> currentRoute){
        int numCount = 0;

        for (String targetNodeName : currentRoute) {
            if (node.getName().equals(targetNodeName)) numCount++;
        }

        return numCount;
    }

    public boolean isAnyLowerCaseElementTwiceInRoute(List<String> currentRoute){
        for (int i = 0; i <currentRoute.size() ; i++) {
            String location = currentRoute.get(i);
            int count =1;
            for (int j = i+1; j < currentRoute.size(); j++) {
                String secondLocation = currentRoute.get(j);
                if(location.equals(secondLocation) && StringTools.isStringLowerCase(location)){
                    return true;
                }
            }
        }
        return false;
    }

    public abstract boolean canConnect(Node from, Node to, List<String> currentRoute);

    public List<String> followConnections(Node currentNode, List<String> currentRoute, List<String> foundRoutes) {
        currentRoute.add(currentNode.getName());
//            visitCount.put(currentNode.getName(), visitCount.getOrDefault(currentNode.getName(), 0) + 1);
        if (end.getName().equals(currentNode.getName())) {
            if (onlyShortestRoute) {
                Integer integer = determineRouteLength(currentRoute);
                if(integer<shortestRouteLength || shortestRouteLength==-1){
                    System.out.println("new champ" + integer);
                    foundRoutes = new ArrayList<>();
                    foundRoutes.add(currentRoute.toString());
                    shortestRouteLength = integer;
                }
            }else{
                foundRoutes.add(currentRoute.toString());
            }
        } else {
            List<Node> connectionsTo = new ArrayList<>(currentNode.getConnectionsTo());
            //filter connections that are lowercase and were visited already
            for (Iterator<Node> iter = connectionsTo.iterator(); iter.hasNext(); ) {
                Node node = iter.next();
                if (!canConnect(currentNode,node, currentRoute)) {
                    iter.remove();
                }
            }
            for (Node node : connectionsTo) {
                List<String> newRoute = new ArrayList<>(currentRoute);
                followConnections(node, newRoute, foundRoutes);
            }
        }
        return foundRoutes;


    }

    public Node getStart() {
        return start;
    }

    public Node getEnd() {
        return end;
    }

    public void addNode(Node nodeToAdd) {
        this.allNodes.add(nodeToAdd);
    }
}
