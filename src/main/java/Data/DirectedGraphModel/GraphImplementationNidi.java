package Data.DirectedGraphModel;

import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.ForNodeLink;
import guru.nidi.graphviz.attribute.Style;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.MutableNode;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static guru.nidi.graphviz.model.Factory.mutGraph;
import static guru.nidi.graphviz.model.Factory.mutNode;
import static guru.nidi.graphviz.model.Link.to;


public class GraphImplementationNidi implements Graph {


    Map<String, Node> nodes = new HashMap<>();


    @Override
    public void addNode(String label) {
        if (!this.nodes.containsKey(label)) {
            this.nodes.put(label, new Node(label));
        }
    }


    @Override
    public void addEdge(String fromLabel, String toLabel, LineColor lineColor, LineStyle lineStyle) {
        this.addNode(fromLabel);
        this.addNode(toLabel);
        Node fromNode = this.nodes.get(fromLabel);
        Node toNode   = this.nodes.get(toLabel);
        fromNode.addEdge(toNode, lineColor, lineStyle);
    }


    @Override
    public void exportPNG(int width, File file) {

        // Create Nodes
        HashMap<String, MutableNode> inodes = new HashMap<>();
        for (Node node : this.nodes.values()) {
            inodes.put(node.getLabel(), mutNode(node.getLabel()));
        }

        // Link Nodes
        for (String nodeFromLabel : this.nodes.keySet()) {
            Node nodeFrom = this.nodes.get(nodeFromLabel);
            for (int i = 0; i < nodeFrom.getEdgeCount(); i++) {
                Node nodeTo           = nodeFrom.getEdgeNode(i);
                String nodeToLabel    = nodeTo.getLabel();
                MutableNode inodeFrom = inodes.get(nodeFromLabel);
                MutableNode inodeTo   = inodes.get(nodeToLabel);

                inodeFrom.addLink(to(inodeTo)
                        .add(this.castStyle(nodeFrom.getEdgeStyle(i)))
                        .add(this.castColor(nodeFrom.getEdgeColor(i))));
            }
        }

        // Add Nodes to Graph
        MutableGraph graph = mutGraph("").setDirected(true);
        for (MutableNode inode : inodes.values()) {
            graph.add(inode);
        }

        // Export Graph
        try {
            Graphviz.fromGraph(graph)
                    .render(Format.PNG)
                    .toFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private Style<ForNodeLink> castStyle(LineStyle style) {
        if (style == LineStyle.DASHED) {
            return Style.DASHED;
        } else if (style == LineStyle.DOTTED) {
            return Style.DOTTED;
        } else {
            return Style.SOLID;
        }
    }


    private Color castColor(LineColor color) {
        if (color == LineColor.RED) {
            return Color.RED;
        } else if (color == LineColor.GREEN) {
            return Color.GREEN;
        } else if (color == LineColor.BLUE) {
            return Color.BLUE;
        } else {
            return Color.BLACK;
        }
    }

    public Map<String, Node> getNodes() {
        return nodes;
    }
}
