package Main;

import java.util.*;


/**
 * Created by hojjat on 10/30/15.
 */
public class NFA_NODE {
    int name;
    boolean isStartNode;
    boolean isEndNode;
    Set<Integer> aGoesTo = new HashSet<>();
    Set<Integer> bGoesTo = new HashSet<>();

    public NFA_NODE(String nodeData) {
        String[] data = nodeData.split("\t");
        setIsStartNodeOrEndNode(data[0].charAt(0));
        setName(data[0]);
        if (data.length > 1 && data[1].length() > 0)
            setWaysWithA(data[1]);
        if (data.length > 2 && data[2].length() > 0)
            setWaysWithB(data[2]);
    }

    private void setIsStartNodeOrEndNode(char sign) {
        if (sign == '*') {
            isEndNode = true;
        } else if (sign == '>') {
            isStartNode = true;
        } else if (sign == '#') {
            isEndNode = true;
            isStartNode = true;
        }
    }

    private void setName(String rawName) {
        this.name = Integer.parseInt(rawName.replaceAll("[^\\d]", ""));
    }

    public boolean isStartNode() {
        return isStartNode;
    }

    public boolean isEndNode() {
        return isEndNode;
    }

    public Set<Integer> getaGoesTo() {
        return aGoesTo;
    }

    public Set<Integer> getbGoesTo() {
        return bGoesTo;
    }

    public void setWaysWithA(String rawData) {
        String[] nodes = rawData.split(",");
        for (String s: nodes)
            aGoesTo.add(Integer.parseInt(s.replaceAll("[^\\d]", "")));
    }

    public void setWaysWithB(String rawData) {
        String[] nodes = rawData.split(",");
        for (String s: nodes)
            bGoesTo.add(Integer.parseInt(s.replaceAll("[^\\d]", "")));
    }

    @Override
    public boolean equals(Object obj) {
        return obj.equals(name);
    }

    @Override
    public String toString() {
        return "q" + name;
    }
}