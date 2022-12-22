package fileworkers;

import data.Blank;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class BlankSaverLoader {
    static String path = "blanks/";

    public static ArrayList<Blank> getAllBlanks(){
        ArrayList<Blank> result = new ArrayList<>();
        File dir = new File(path);
        if (!dir.exists()){
            dir.mkdirs();
        }
        for (File file :dir.listFiles()){
            Blank blank = loadBlank(file.getName());
            if (blank!=null){
                result.add(blank);
            }
        }
        return result;
    }

    public static void saveBlank(Blank blank, String filename) throws IOException {
        FileOutputStream fos = new FileOutputStream(path + filename);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(blank);
        oos.close();
    }

    public static void saveBlank(Blank blank) {
        try {
            saveBlank(blank,getHash(blank.getName())+".txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Blank loadBlank(String filename){
        try {
            FileInputStream fis = new FileInputStream(path + filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Blank blank = (Blank) ois.readObject();
            ois.close();
            return blank;
        }
        catch (Exception e){
            return null;
        }
    }

    public static Blank getBlank(String name) {
            return loadBlank(getHash(name)+".txt");
    }

    public static void delBlank(String name){
        File file = new File(path + getHash(name)+".txt");
        file.delete();
    }


    public static String getHash(String toHash){
        return String.valueOf(toHash.hashCode());
    }





}
