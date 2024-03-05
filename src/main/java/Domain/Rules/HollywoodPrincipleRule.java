package Domain.Rules;
import Data.DirectedGraphModel.Graph;
import Data.DirectedGraphModel.LineColor;
import Data.DirectedGraphModel.LineStyle;
import Data.JavaByteCodeAdapter.*;
import Data.Options;
import Domain.Issue;
import Domain.Rule;
import Domain.Severity;

import java.io.File;
import java.util.*;


public class HollywoodPrincipleRule implements Rule {

    private boolean[][] connections;
    private ArrayList<String> classes;
    private Severity severity;
    private Graph graph;

    private enum EdgeType {
        DIRECT_EDGE,
        INDIRECT_EDGE,
        CYCLE_EDGE
    }

    public HollywoodPrincipleRule() {
        this.graph = null;
    }

    public HollywoodPrincipleRule(Graph graph) {
        this.graph = graph;
    }


    @Override
    public Options getDefaultOptions() {
        return new Options(new ArrayList<String>(){{
                add("severity");
            }}, new ArrayList<String>(){{
                add("ERROR");
            }});
    }

    @Override
    public List<Issue> apply(Map<String, ClassNode> classNodes, Options options) {
        List<Issue> issues = new ArrayList<>();
        this.init(classNodes, options);
        
        // Scan for Connections
        for (String callFromName : this.classes) {
            ClassNode callFrom = classNodes.get(callFromName);
            if (!callFrom.isValid()) continue;
            for (MethodNode methodNode : callFrom.getMethods()) {
                InsnList insnList = methodNode.getInstructions();
                for (int i = 0; i < insnList.size(); i++) {
                    AbstractInsnNode insn = insnList.get(i);
                    if (insn.isMethodInsnNode()) {
                        MethodInsnNode call   = insn.getMethodInsnNode();
                        ClassNode      callTo = call.getMethodOwner();
                        if (callTo.isValid()) {
                            this.addConnection(
                                    callFrom.getClassName(),
                                    callTo.getClassName()
                            );
                        }
                    }
                }
            }
        }

        // Copy Direct Connection Graph
        boolean[][] directConnection = new boolean[this.connections.length][this.connections.length];
        for (int row = 0; row < this.connections.length; row++) {
            for (int col = 0; col < this.connections[row].length; col++) {
                directConnection[row][col] = this.connections[row][col];
            }
        }

        // Calculate Reachability
        this.reachability();
        Map<String, ArrayList<String>> cycles = this.getCycles();

        for (String classname : cycles.keySet()) {
            String filename = classNodes.get(classname).getSourceFile();
            String rule     = this.getClass().getSimpleName();
            String msg      = String.format("Violation of Hollywood Principle, "
                                + "this class is "
                                + "calling to: %s", cycles.get(classname));
            issues.add(new Issue(rule, -1, filename,
                    classname, msg, this.severity));
        }


        this.drawGraph(directConnection, this.connections, cycles, this.classes);

        return issues;
    }


    private void init(Map<String, ClassNode> classNodes, Options options) {
        String ignore    = options.hasKey("ignore") ? options.get("ignore") : "";
        this.classes     = new ArrayList<>();
        this.classes.addAll(classNodes.keySet());
        this.classes.removeAll(Arrays.asList(Arrays.stream(ignore.split(",")).map(String::trim).toArray()));

        this.connections = new boolean[this.classes.size()][this.classes.size()];
        this.severity    = options.get("severity", Severity.class, this.getClass().getSimpleName());
    }


    private void addConnection(String fromClass, String toClass) {
        int fromClassIndex = this.findClassIndex(fromClass);
        int toClassIndex   = this.findClassIndex(toClass);
        if (fromClassIndex == -1 || toClassIndex == -1) return;
        this.connections[fromClassIndex][toClassIndex] = true;
    }


    private int findClassIndex(String classname) {
        return this.classes.indexOf(classname);
    }


    // https://www.geeksforgeeks.org/floyd-warshall-algorithm-dp-16/
    private void reachability() {
        int size = this.connections.length;
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (this.connections[i][k] && this.connections[k][j]) {
                        this.connections[i][j] = true;
                    }
                }
            }
        }
    }


    private Map<String, ArrayList<String>> getCycles() {
        int size = this.connections.length;
        Map<String, ArrayList<String>> cycles = new HashMap<>();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (this.connections[i][j] && this.connections[j][i]) {
                    if (i == j) continue;
                    String classFrom = this.classes.get(i);
                    String classTo   = this.classes.get(j);
                    if (!cycles.containsKey(classFrom)) {
                        cycles.put(classFrom, new ArrayList<>());
                    }
                    if (!cycles.get(classFrom).contains(classTo)) {
                        cycles.get(classFrom).add(classTo);
                    }
                }
            }
        }
        return cycles;
    }


    private void drawGraph(boolean[][] direct, boolean[][] indirect,
                           Map<String, ArrayList<String>> cycles,
                           ArrayList<String> nodeNames) {
        if (this.graph == null) return;
        System.out.println("Log's from Graph Dependency");

        // Generate Edge Types
        EdgeType[][] edges = new EdgeType[direct.length][direct.length];
        for (int row = 0; row < direct.length; row++) {
            for (int col = 0; col < direct[row].length; col++) {
                boolean isEdge       = direct[row][col];
                boolean isDirectEdge = indirect[row][col];

                if (isEdge && row != col) {
                    String rowName = nodeNames.get(row);
                    String colName = nodeNames.get(col);
                    if (cycles.containsKey(rowName) && cycles.get(rowName).contains(colName)) {
                        edges[row][col] = EdgeType.CYCLE_EDGE;
                    } else if (isDirectEdge) {
                        edges[row][col] = EdgeType.DIRECT_EDGE;
                    } else {
                        edges[row][col] = EdgeType.INDIRECT_EDGE;
                    }
                } else {
                    edges[row][col] = null;
                }
            }
        }

        // Add Nodes
        for (String node : nodeNames) {
            graph.addNode(node);
        }

        // Add Edges
        for (int row = 0; row < edges.length; row++) {
            for (int col = 0; col < edges[row].length; col++) {
                String edgeFromLabel = nodeNames.get(row);
                String edgeToLabel   = nodeNames.get(col);
                EdgeType edgeType    = edges[row][col];
                if (edgeType == EdgeType.DIRECT_EDGE) {
                    graph.addEdge(edgeFromLabel, edgeToLabel, LineColor.BLACK, LineStyle.SOLID);
                } else if (edgeType == EdgeType.INDIRECT_EDGE) {
                    graph.addEdge(edgeFromLabel, edgeToLabel, LineColor.BLACK, LineStyle.DASHED);
                } else if (edgeType == EdgeType.CYCLE_EDGE) {
                    graph.addEdge(edgeFromLabel, edgeToLabel, LineColor.RED, LineStyle.SOLID);
                }
            }
        }

        // Render Edge
        graph.exportPNG(5000, new File("dependencies.png"));
    }


}
