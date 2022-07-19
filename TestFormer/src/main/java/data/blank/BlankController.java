package data.blank;



import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static data.themes.ThemeController.getTheme;

public class BlankController {


    public static String[] getNames() {
        try {
            return BlankSaverLoader.getAllBlanks().stream().map(e->e.getName()).collect(Collectors.toList()).toArray(new String[]{});
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isExist(String name){
        List<String> names = Arrays.asList(getNames());
        return names.contains(name);
    }

    public static Blank getBlank(String name) {
        return BlankSaverLoader.getBlank(name);
    }

    public static void delBlank(String name) {
        BlankSaverLoader.delBlank(name);
    }

    public static Blank newBlank(String name){
            Blank blank = new Blank();
            blank.setName(name);
            BlankSaverLoader.addBlank(blank);
            return blank;
    }

    public static void renameTheme(String first,String second){
        Blank blank = BlankSaverLoader.getBlank(first);
        BlankSaverLoader.delBlank(first);
        blank.setName(second);
        blank.getStart();
        BlankSaverLoader.addBlank(blank);
    }

    public static List<Blank> getAllBlanks(){
        try {
            return BlankSaverLoader.getAllBlanks();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void saveBlank(Blank blank) {
        BlankSaverLoader.saveBlank(blank);
    }
}
