package data.themes;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class ThemesSaverLoader {
    static String path = "themes/";

    public static ArrayList<Theme> getAllThemes() throws IOException, ClassNotFoundException {
        ArrayList<Theme> result = new ArrayList<>();
        File dir = new File(path);
        if (!dir.exists()){
            dir.mkdirs();
        }
        for (File file :dir.listFiles()){
            result.add(loadTheme(file.getName()));
        }
        return result;
    }

    public static void saveTheme(Theme theme,String filename) throws IOException {
        FileOutputStream fos = new FileOutputStream(path + filename);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(theme);
        oos.close();
    }

    public static void saveTheme(Theme theme) {
        try {
            saveTheme(theme,getHash(theme.getName())+".txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Theme loadTheme(String filename) throws IOException, ClassNotFoundException {
            FileInputStream fis = new FileInputStream(path + filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Theme theme = (Theme) ois.readObject();
            ois.close();
            return theme;
    }




    public static Theme getTheme(String name) {
        try {
            return loadTheme(getHash(name)+".txt");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void delTheme(String name){
        File file = new File(path + getHash(name)+".txt");
        file.delete();
    }

    public static Theme addTheme(String name){
        try {
            Theme theme = new Theme();
            theme.setName(name);
            saveTheme(theme,getHash(name)+".txt");
            return theme;
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void addTheme(Theme theme)  {
        try {
            saveTheme(theme,getHash(theme.getName())+".txt");
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public static String getHash(String toHash){
        return String.valueOf(toHash.hashCode());
    }





}
