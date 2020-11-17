package Praktikum08;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LabyrinthServer implements CommandExecutor{

    final double SCALE = 10;

    private Graph<LabyrinthNode<Object>, Edge<Object>> graph;
    private static final String startNode = "0-6";
    private static final String goal = "3-0";


    private void drawPath(ServerGraphics g, String from, String to, boolean mouse) {
        double xh0 = from.charAt(0) - '0';
        double yh0 = from.charAt(2) - '0';
        double xh1 = to.charAt(0) - '0';
        double yh1 = to.charAt(2) - '0';
        double x0 = Math.min(xh0,xh1)/SCALE;
        double y0 = Math.min(yh0,yh1)/SCALE;
        double x1 = Math.max(xh0,xh1)/SCALE;
        double y1 = Math.max(yh0,yh1)/SCALE;
        double w = 1/SCALE;
        if (mouse) g.drawLine(x0+w/2,y0+w/2,x1+w/2,y1+w/2);
        else {
            if (y0 == y1)
                g.fillRect(x0,y0,x1-x0+w,w);
            else
                g.fillRect(x0,y0,w,y1-y0+w);
        }
    }

    @Override
    public String execute(String command) throws Exception {
        graph = new AdjListGraph<>(LabyrinthNode.class, Edge.class);
        ServerGraphics serverGraphics = new ServerGraphics();
        serverGraphics.setColor(Color.white);
        try (Scanner scanner = new Scanner(command)) {
            while (scanner.hasNext()) {
                String[] arguments = scanner.nextLine().split(" ");
                drawPath(serverGraphics, arguments[0], arguments[1], false);
                graph.addEdge(arguments[0], arguments[1], 1);
                graph.addEdge(arguments[1], arguments[0], 1);

            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        initGraph(graph);
        search(startNode);

        LabyrinthNode<Object> destination = graph.findNode(goal);
        List<Node<Object>> path = new ArrayList<>();

        while(destination.getPrev() != null) {
            path.add(0, destination);
            destination = destination.getPrev();
        }
        serverGraphics.setColor(Color.red);
        path.add(0, graph.findNode(startNode));

        for(int i = 1; i < path.size(); i++) {
            drawPath(serverGraphics, path.get(i-1).getName(), path.get(i).getName(), true);
        }


        return serverGraphics.getTrace();

    }

    private boolean search(String node) {
        LabyrinthNode currentNode = graph.findNode(node);
        currentNode.setMarked(true);
        if(currentNode.getName().equals(goal)) return true;
        else {
            for(Object edge:  currentNode.getEdges()) {
                LabyrinthNode<Object> neighbour = (LabyrinthNode<Object>) ((Edge<Object>) edge).getDest();
                if(!neighbour.isMarked()) {
                    if(search(neighbour.getName())) {
                        neighbour.setPrev(currentNode);
                        return true;
                    }
                }
            }
        }
        currentNode.setMarked(false);
        return false;
    }


    private void initGraph(Graph<LabyrinthNode<Object>, Edge<Object>> graph) {
        for(LabyrinthNode node: graph.getNodes()) {
            node.setMarked(false);
            node.setPrev(null);
        }
    }

}
