package Praktikum07;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class RouteServer implements CommandExecutor{

    private Graph<DijkstraNode<Object>,Edge<Object>> graph;

    @Override
    public String execute(String command) throws Exception {
        System.out.println(command);
        graph = new AdjListGraph<>(DijkstraNode.class,Edge.class);

        try(Scanner scanner = new Scanner(command)) {
            while(scanner.hasNext()) {
                String[] arguments = scanner.nextLine().split(" ");
                graph.addEdge(arguments[0], arguments[1], Double.parseDouble(arguments[2]));
                graph.addEdge(arguments[1], arguments[0], Double.parseDouble(arguments[2]));
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        for(DijkstraNode<Object> node : graph.getNodes()) {
            System.out.println(node.getName());
        }

        initGraph(graph);

        String start = "Winterthur";
        String end = "Lugano";

        dijkstra(start, end);

        StringBuilder sb = new StringBuilder();
        sb.append(start);

        DijkstraNode<Object> destination = graph.findNode(end);
        List<Node<Object>> path = new ArrayList<>();

        while(destination.getPrev() != null) {
            path.add(0, destination);
            destination = destination.prev;
        }

        for(Node<Object> node : path) {
            sb.append(" -> " + node.getName());
        }

        return sb.toString();
    }

    private void initGraph(Graph<DijkstraNode<Object>,Edge<Object>> graph) {
        for(DijkstraNode<Object> node : graph.getNodes()) {
            node.setMark(false);
            node.setDist(Double.MAX_VALUE); //Double max value instead of inf
            node.setPrev(null);
        }
    }

    private void dijkstra(String from, String to ) {
        PriorityQueue<DijkstraNode<Object>> redNodes = new PriorityQueue<>();
        DijkstraNode<Object> startNode = graph.findNode(from);
        startNode.setDist(0);
        redNodes.add(startNode);
        DijkstraNode<Object> endNode = graph.findNode(to);
        while (!redNodes.isEmpty()) {
            DijkstraNode<Object> current = redNodes.remove();
            if (!current.equals(endNode)) {
                current.setMark(true);
                for (Object edge : current.getEdges()) {
                    DijkstraNode<Object> neighbour = (DijkstraNode<Object>) ((Edge<Object>) edge).getDest();
                    if (!neighbour.getMark()) {
                        double distance = current.getDist() + ((Edge<?>) edge).getWeight();
                        if (distance < neighbour.getDist()) {
                            neighbour.setDist(distance);
                            neighbour.setPrev(current);
                            redNodes.remove(neighbour);
                            redNodes.add(neighbour);
                        }
                    }
                }
            }
        }
    }
}
