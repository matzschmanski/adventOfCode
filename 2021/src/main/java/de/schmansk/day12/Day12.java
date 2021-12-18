package de.schmansk.day12;

import de.schmansk.FileTools;
import util.navigation.Graph;
import util.navigation.Node;

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

}
