package Data.DirectedGraphModel;

import java.util.ArrayList;


public class Node {

    private final String               label;
    private final ArrayList<Node>      edgeNodes  = new ArrayList<>();
    private final ArrayList<LineColor> edgeColors = new ArrayList<>();
    private final ArrayList<LineStyle> edgeStyles = new ArrayList<>();

    Node(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

    public void addEdge(Node node, LineColor lineColor, LineStyle lineStyle) {
        if (!this.edgeNodes.contains(node)) {
            this.edgeNodes.add(node);
            this.edgeColors.add(lineColor);
            this.edgeStyles.add(lineStyle);
        }
    }

    public int getEdgeCount() {
        return this.edgeNodes.size();
    }


    public Node getEdgeNode(int i) {
        return this.edgeNodes.get(i);
    }


    public LineColor getEdgeColor(int i) {
        return this.edgeColors.get(i);
    }


    public LineStyle getEdgeStyle(int i) {
        return this.edgeStyles.get(i);
    }


}
