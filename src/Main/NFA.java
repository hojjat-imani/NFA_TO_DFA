package Main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Created by hojjat on 10/30/15.
 */
public class NFA {
    ArrayList<NFA_NODE> nodes = new ArrayList<>();

    public NFA(File file) throws IOException {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            nodes.add(new NFA_NODE(scanner.nextLine()));
        }
    }

    public NFA_NODE getStartNode() {
        for (NFA_NODE node : nodes)
            if (node.isStartNode())
                return node;
        return null;
    }

    public NFA_NODE getNodeWithName(Integer i) {
        for (NFA_NODE node : nodes)
            if (node.equals(i))
                return node;
        return null;
    }
}