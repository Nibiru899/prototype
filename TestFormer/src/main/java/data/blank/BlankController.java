package data.blank;



import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class BlankController {


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
