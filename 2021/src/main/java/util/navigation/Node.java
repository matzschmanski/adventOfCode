package util.navigation;

import java.util.ArrayList;
import java.util.List;

public class Node {
    String name;
    List<Node> connectionsTo;

    int someValue;

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

    public int getSomeValue() {
        return someValue;
    }

    public void setSomeValue(int someValue) {
        this.someValue = someValue;
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                '}';
    }
}
