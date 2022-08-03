package controllers;

import data.Blank;
import fileworkers.BlankSaverLoader;
import java.util.List;

public class BlankController {


    public static Blank getBlank(String name) {
        return BlankSaverLoader.getBlank(name);
    }

    public static void delBlank(String name) {
        BlankSaverLoader.delBlank(name);
    }

    public static List<Blank> getAllBlanks(){
        return BlankSaverLoader.getAllBlanks();
    }

    public static boolean isExist(String name){
        List<Blank> blanks = getAllBlanks();
        for (Blank blank : blanks){
            if (blank.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public static void saveBlank(Blank blank) {
        BlankSaverLoader.saveBlank(blank);
    }
}
