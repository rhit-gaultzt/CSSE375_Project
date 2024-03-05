package Domain.Rules;

import Data.JavaByteCodeAdapter.*;
import Data.Options;
import Domain.Issue;
import Domain.Rule;
import Domain.Severity;
import java.util.*;
import java.util.stream.Collectors;


// Vocabulary
// component - class to be decorated
// reference - abstract class for the decorator class
// decorator - implementation of the decorator


public class DecoratorPatternRule implements Rule {


    private Severity predictionSeverity;
    private Severity missingMethodSeverity;
    private Severity missingSuperCallSeverity;


    @Override
    public Options getDefaultOptions() {
        return new Options(new ArrayList<String>(){{
                add("severity");
                add("missingMethodSeverity");
                add("missingSuperCallSeverity");
            }}, new ArrayList<String>(){{
                add("ERROR");
                add("WARNING");
                add("WARNING");
            }});
    }


    @Override
    public List<Issue> apply(Map<String, ClassNode> classNodes, Options options) {
        HashMap<String, String> referenceToComponent = new HashMap<>();
        ArrayList<Issue> issues = new ArrayList<>();
        this.init(options);

        // Identify Decorator References
        for (String referenceName :classNodes.keySet()) {
            ClassNode referenceNode = classNodes.get(referenceName);
            String    referenceFile = referenceNode.getSourceFile();

            // Verify Decorator Reference
            if (!referenceNode.isAbstract()) continue;
            ArrayList<String> interfaces  = this.getInterfaces(referenceNode);
            ArrayList<String> classFields = this.getFields(referenceNode);
            ArrayList<String> initFields  = this.getInitFields(referenceNode);
            if (initFields.size() != 1) continue;
            String componentName    = initFields.get(0);
            ClassNode componentNode = classNodes.get(componentName);
            if (!classFields.contains(componentName)) continue;
            if (!interfaces.contains(componentName)) continue;

            // Verify that References Overrides all methods in Component
            ArrayList<MethodNode> methodsOfComponent = (ArrayList<MethodNode>) componentNode.getMethods();
            ArrayList<MethodNode> methodsOfReference = (ArrayList<MethodNode>) referenceNode.getMethods();
            ArrayList<String> methodNamesOfComponent = (ArrayList<String>) methodsOfComponent.stream().map(MethodNode::getName).collect(Collectors.toList());
            ArrayList<String> methodNamesOfReference = (ArrayList<String>) methodsOfReference.stream().map(MethodNode::getName).collect(Collectors.toList());
            for (int i = 0; i < methodNamesOfComponent.size(); i++) {
                if (!methodNamesOfReference.contains(methodNamesOfComponent.get(i))) {
                    String message = String.format("Decorator Reference \"%s\" "
                            + "did not implement the method \"%s\", which is "
                            + "present in the component being decorated, \"%s\""
                            + ".", referenceName, methodNamesOfComponent.get(i),
                            componentName);
                    issues.add(new Issue(
                            this.getClass().getSimpleName(),
                            -1,
                            referenceFile,
                            referenceName,
                            message,
                            this.missingMethodSeverity));
                }
            }

            // Verify all implementations call super
            issues.addAll(this.verifyMethodsCallSuper(referenceNode, null, componentName, false));
            referenceToComponent.put(referenceName, componentName);

            // Add Prediction
            String message = String.format("This class appears to be a "
                                + "abstract decorator for the component "
                                + "\"%s\".", componentName);
            issues.add(new Issue(
                    this.getClass().getSimpleName(),
                    -1,
                    referenceFile,
                    referenceName,
                    message,
                    this.predictionSeverity));
        }


        // Validate Decorators
        for (String decoratorName : classNodes.keySet()) {
            ClassNode decoratorNode = classNodes.get(decoratorName);
            String    decoratorFile = decoratorNode.getSourceFile();

            // Verify Decorator Extends Reference
            ClassNode referenceNode = decoratorNode.getSuperClass();
            if (referenceNode == null) continue;
            String referenceName = referenceNode.getClassName();
            if (!referenceToComponent.containsKey(referenceName)) continue;
            String componentName = referenceToComponent.get(referenceName);

            // Verify Constructor has Component as Param
            ArrayList<String> initFields  = this.getInitFields(decoratorNode);
            if (initFields.size() != 1) continue;
            if (!Objects.equals(componentName, initFields.get(0))) continue;

            // Verify all implementations call super
            issues.addAll(this.verifyMethodsCallSuper(decoratorNode, referenceName, componentName, true));

            // Add Prediction
            String message = String.format("This class appears to be a "
                    + "decorator for the component "
                    + "\"%s\".", componentName);
            issues.add(new Issue(
                    this.getClass().getSimpleName(),
                    -1,
                    decoratorFile,
                    decoratorName,
                    message,
                    this.predictionSeverity));
        }

        return issues;
    }


    private void init(Options options) {
        this.predictionSeverity       = options.get("severity", Severity.class, this.getClass().getSimpleName());
        this.missingMethodSeverity    = options.get("missingMethodSeverity", Severity.class, this.getClass().getSimpleName());
        this.missingSuperCallSeverity = options.get("missingSuperCallSeverity", Severity.class, this.getClass().getSimpleName());
    }


    private ArrayList<String> getFields(ClassNode classNode) {
        return (ArrayList<String>) classNode.getFields()
                .stream()
                .map((o) -> o.getType().getClassName())
                .collect(Collectors.toList());
    }


    private ArrayList<String> getInterfaces(ClassNode classNode) {
        return (ArrayList<String>) classNode.getInterfaces()
                .stream()
                .map(ClassNode::getClassName)
                .collect(Collectors.toList());
    }


    private ArrayList<String> getInitFields(ClassNode classNode) {
        ArrayList<MethodNode> constructorMethod = (ArrayList<MethodNode>) classNode.getMethods()
                .stream()
                .filter(MethodNode::isConstructor)
                .collect(Collectors.toList());
        if (constructorMethod.size() < 1) return new ArrayList<>();
        return (ArrayList<String>) constructorMethod.get(0).getArgumentTypes()
                .stream()
                .map(ClassNode::getClassName)
                .collect(Collectors.toList());
    }


    private ArrayList<Issue> verifyMethodsCallSuper(ClassNode classNode, String referenceName, String componentName, boolean isDecorator) {
        ArrayList<Issue> issues = new ArrayList<>();
        String classFile        = classNode.getSourceFile();
        String className        = classNode.getClassName();

        for (MethodNode method : classNode.getMethods()) {
            String   methodName   = method.getName();
            InsnList instructions = method.getInstructions();
            boolean  hasCallSuper = false;
            if (!isDecorator && method.isConstructor()) continue;
            if (!method.isPublic()) continue;
            for (int i = 0; i < instructions.size(); i++) {
                AbstractInsnNode instAbstract = instructions.get(i);
                if (!instAbstract.isMethodInsnNode()) continue;
                MethodInsnNode instMethod = instAbstract.getMethodInsnNode();
                String callName  = instMethod.getMethodName();
                String callOwner = instMethod.getMethodOwner().getClassName();
                if (!Objects.equals(callName, methodName)) continue;
                if (!Objects.equals(callOwner, referenceName) && !Objects.equals(callOwner, componentName)) continue;
                hasCallSuper = true;
            }
            if (!hasCallSuper) {
                String message = String.format("Abstract decorator "
                                + "\"%s\", for component \"%s\", did not call "
                                + "the decorated component in method \"%s\".",
                                className, componentName, methodName);
                if (isDecorator) {
                    message = String.format("Decorator implementation "
                                    + "\"%s\" of \"%s\" "
                                    + "did not call super or the decorated "
                                    + "component in method \"%s\".", className,
                                    referenceName, methodName);
                }
                issues.add(new Issue(
                        this.getClass().getSimpleName(),
                        -1,
                        classFile,
                        className,
                        message,
                        this.missingSuperCallSeverity)); // FIXME
            }
        }

        return issues;
    }


}
