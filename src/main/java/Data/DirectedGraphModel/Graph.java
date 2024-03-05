package Data.DirectedGraphModel;

import java.io.File;

public interface Graph {
    void addNode(String label);
    void addEdge(String fromLabel, String toLabel, LineColor lineColor, LineStyle lineStyle);
    void exportPNG(int width, File file);
}
