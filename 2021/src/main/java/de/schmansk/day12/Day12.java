package de.schmansk.day12;

import de.schmansk.FileTools;

import java.nio.file.Path;
import java.util.*;

public class Day12 {

    public int starOne(Path pathToInput) {
        String[] routesAsString = FileTools.readFileLineByLine(pathToInput);


        Map<String, Node> nodes = new HashMap<>();
        for (String line : routesAsString) {
            String[] split = line.split("-");
            String node1Name = split[0].trim();
            String node2Name = split[1].trim();
            Node node1 = nodes.getOrDefault(node1Name, new Node(node1Name));
            Node node2 = nodes.getOrDefault(node2Name, new Node(node2Name));
            node1.addConnection(node2);
            node2.addConnection(node1);
            nodes.put(node1Name, node1);
            nodes.put(node2Name, node2);
        }
        //tree rdy
        Graph graph = new Graph(nodes.get("start"), nodes.get("end"), new ArrayList<>(nodes.values()));
        graph.findRoutes();

        return 0;
    }

    private boolean isStringLowerCase(String str) {

        //convert String to char array
        char[] charArray = str.toCharArray();

        for (int i = 0; i < charArray.length; i++) {

            //if any character is not in lower case, return false
            if (!Character.isLowerCase(charArray[i]))
                return false;
        }

        return true;

    }

    public class Graph {
        Node start;
        Node end;
        List<Node> allNodes;

        public Graph(Node start, Node end, List<Node> allNodes) {
            this.allNodes = allNodes;
            this.end = end;
            this.start = start;
        }

        public void findRoutes() {
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

        }

        public int countInRoute(Node node,List<String> currentRoute){
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
                    if(location.equals(secondLocation) && isStringLowerCase(location)){
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean canConnect(Node from, Node to,List<String> currentRoute){
            if (to.getName().equals("start") || to.getName().equals("end")){
                if(currentRoute.contains(to.getName())){
                    return false;
                }
            } else if (
                    currentRoute.contains(to.getName()) &&
                            isStringLowerCase(to.getName()) &&
//                            countInRoute(to,currentRoute)>1 &&
                            isAnyLowerCaseElementTwiceInRoute(currentRoute)) {
                return false;
                //das ist any nicht nur einer darf zwei mal

            }
            return true;
        }

        public List<String> followConnections(Node currentNode, List<String> currentRoute, List<String> foundRoutes) {
            currentRoute.add(currentNode.getName());
//            visitCount.put(currentNode.getName(), visitCount.getOrDefault(currentNode.getName(), 0) + 1);
            if ("end".equals(currentNode.getName())) {
                foundRoutes.add(currentRoute.toString());
                System.out.println(currentRoute);
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

        public void addNode(Node nodeToAdd) {
            this.allNodes.add(nodeToAdd);
        }

    }

    public class Node {
        String name;
        List<Node> connectionsTo;

        public Node(String name) {
            this.connectionsTo = new ArrayList<>();
            this.name = name;
        }

        public void addConnection(Node node) {
            this.connectionsTo.add(node);

        }

        public String getName() {
            return this.name;
        }

        public List<Node> getConnectionsTo() {
            return this.connectionsTo;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
