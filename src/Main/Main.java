package Main;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by hojjat on 10/30/15.
 */
public class Main {
    static String NFAFilePath = "/home/hojjat/Dars/Nazarie/Project1/Sample4/NDFA.txt";
    public static void main(String... args){
        try {
            (new Conventer(new NFA(new File(NFAFilePath)))).convert();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}