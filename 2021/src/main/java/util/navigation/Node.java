package util.navigation;

import java.util.ArrayList;
import java.util.List;

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
