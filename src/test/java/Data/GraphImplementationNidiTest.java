package Data;

import Data.DirectedGraphModel.GraphImplementationNidi;
import Data.DirectedGraphModel.LineColor;
import Data.DirectedGraphModel.LineStyle;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class GraphImplementationNidiTest {

    @Test
    public void addNodeBasicTest() {
        // Record
        GraphImplementationNidi graph = new GraphImplementationNidi();
        String label = "label";

        // Replay
        graph.addNode(label);

        // Verify
        Assertions.assertEquals(1, graph.getNodes().size());
        Assertions.assertEquals(true, graph.getNodes().keySet().contains(label));
        Assertions.assertEquals(0, graph.getNodes().get(label).getEdgeCount());
    }

    @Test
    public void addMultipleNodesTest() {
        // Record
        GraphImplementationNidi graph = new GraphImplementationNidi();
        String label1 = "label1";
        String label2 = "label2";

        // Replay
        graph.addNode(label1);
        graph.addNode(label2);

        // Verify
        Assertions.assertEquals(2, graph.getNodes().size());
        Assertions.assertEquals(true, graph.getNodes().keySet().contains(label1));
        Assertions.assertEquals(true, graph.getNodes().keySet().contains(label2));
        Assertions.assertEquals(0, graph.getNodes().get(label1).getEdgeCount());
        Assertions.assertEquals(0, graph.getNodes().get(label2).getEdgeCount());
    }

    @Test
    public void addEdgeBasicTest() {
        // Record
        GraphImplementationNidi graph = new GraphImplementationNidi();
        String label1 = "label1";
        String label2 = "label2";
        LineColor color = LineColor.RED;
        LineStyle style = LineStyle.SOLID;

        // Replay
        graph.addNode(label1);
        graph.addNode(label2);
        graph.addEdge(label1, label2, color, style);

        // Verify
        Assertions.assertEquals(1, graph.getNodes().get(label1).getEdgeCount());
        Assertions.assertEquals(label2, graph.getNodes().get(label1).getEdgeNode(0).getLabel());
        Assertions.assertEquals(color, graph.getNodes().get(label1).getEdgeColor(0));
        Assertions.assertEquals(style, graph.getNodes().get(label1).getEdgeStyle(0));
    }

    @Test
    public void addMultipleEdgesTest() {
        // Record
        GraphImplementationNidi graph = new GraphImplementationNidi();
        String label1 = "label1";
        String label2 = "label2";
        LineColor color1 = LineColor.RED;
        LineStyle style1 = LineStyle.SOLID;
        LineColor color2 = LineColor.BLACK;
        LineStyle style2 = LineStyle.DASHED;

        // Replay
        graph.addNode(label1);
        graph.addNode(label2);
        graph.addEdge(label1, label2, color1, style1);
        graph.addEdge(label2, label1, color2, style2);

        // Verify
        Assertions.assertEquals(1, graph.getNodes().get(label1).getEdgeCount());
        Assertions.assertEquals(label2, graph.getNodes().get(label1).getEdgeNode(0).getLabel());
        Assertions.assertEquals(color1, graph.getNodes().get(label1).getEdgeColor(0));
        Assertions.assertEquals(style1, graph.getNodes().get(label1).getEdgeStyle(0));

        Assertions.assertEquals(1, graph.getNodes().get(label2).getEdgeCount());
        Assertions.assertEquals(label1, graph.getNodes().get(label2).getEdgeNode(0).getLabel());
        Assertions.assertEquals(color2, graph.getNodes().get(label2).getEdgeColor(0));
        Assertions.assertEquals(style2, graph.getNodes().get(label2).getEdgeStyle(0));
    }
}
