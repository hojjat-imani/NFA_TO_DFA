package Main;
import java.util.HashSet;
import java.util.Set;
/**
 * Created by hojjat on 10/30/15.
 */
public class Conventer {
    String tab = "    ";
    NFA NFA;
    Set<Set<Integer>> toBeProcessedNodes = new HashSet<>();
    Set<Set<Integer>> processedNodes = new HashSet<>();

    public Conventer(NFA NFA) {
        this.NFA = NFA;
        HashSet<Integer> start = new HashSet<>();
        start.add(NFA.getStartNode().name);
        toBeProcessedNodes.add(start);
    }

    public void convert() {
        Set node = getNextNodeForProcess();
        if (node == null)
            return;
        System.out.print(getSign(node) + getDFANodeName(node) + tab);
        Set<Integer> desWithA = getDesWithA(node);
        Set<Integer> desWithB = getDesWithB(node);
        System.out.print(getDFANodeName(desWithA) + tab);
        System.out.println(getDFANodeName(desWithB));
        toBeProcessedNodes.remove(node);
        processedNodes.add(node);
        if (desWithA.size()!= 0 && !processedNodes.contains(desWithA))
            toBeProcessedNodes.add(desWithA);
        if (desWithB .size() != 0 && !processedNodes.contains(desWithB))
            toBeProcessedNodes.add(desWithB);
        convert();
    }

    private String getDFANodeName(Set<Integer> nodes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer i : nodes)
            stringBuilder.append("q").append(i).append(",");
        stringBuilder.setLength(stringBuilder.length() > 0 ? stringBuilder.length() - 1 : 0);
        return stringBuilder.toString();
    }

    private Set<Integer> getNextNodeForProcess() {
        if (toBeProcessedNodes.size() != 0) {
            Object[] objects = toBeProcessedNodes.toArray();
            return (Set) objects[0];
        }
        return null;
    }

    public char getSign(Set<Integer> nodes) {
        boolean isStartNode = isStartNode(nodes);
        boolean isEndNode = isEndNode(nodes);
        if (isStartNode && isEndNode)
            return '#';
        if (isStartNode)
            return '>';
        if (isEndNode)
            return '*';
        return ' ';
    }

    private boolean isStartNode(Set<Integer> nodes) {
        if (nodes.size() == 1) {
            Object[] objects = nodes.toArray();
            return NFA.getStartNode().equals(objects[0]);
        }
        return false;
    }

    private boolean isEndNode(Set<Integer> nodes) {
        for (Integer i : nodes)
            if (NFA.getNodeWithName(i).isEndNode())
                return true;
        return false;
    }

    public Set<Integer> getDesWithA(Set<Integer> nodes) {
        HashSet<Integer> result = new HashSet<>();
        for (Integer i : nodes) {
            result.addAll(NFA.getNodeWithName(i).getaGoesTo());
        }
        return result;
    }

    public Set<Integer> getDesWithB(Set<Integer> nodes) {
        HashSet<Integer> result = new HashSet<>();
        for (Integer i : nodes) {
            result.addAll(NFA.getNodeWithName(i).getbGoesTo());
        }
        return result;
    }
}