import data.Theme;
import fileworkers.ThemesSaverLoader;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;

public class unitTestThemeLoader {
    @BeforeEach
    void mkDirs(){
        File dir = new File("themes");
        dir.mkdirs();
    }

    @AfterEach
    void delDirs(){
        File dir = new File("themes");
        delInnr(dir);
    }
    void delInnr(File file){
        if (file.isDirectory()){
            for (File inner : file.listFiles()){
                delInnr(inner);
            }
        } else {
            file.delete();
        }
    }
    @Test
    void saveLoadDelete() throws IOException, ClassNotFoundException {
        Theme first = new Theme();
        first.setName("1firstName");
        Theme second = new Theme();
        second.setName("2secondName");
        Assertions.assertEquals(0, ThemesSaverLoader.getAllThemes().size());
        Assertions.assertNull(ThemesSaverLoader.getTheme(first.getName()));
        //загрузка тем
        ThemesSaverLoader.saveTheme(first);
        ThemesSaverLoader.saveTheme(second);
        Assertions.assertEquals(2,ThemesSaverLoader.getAllThemes().size());
        //получение тем
        Assertions.assertEquals(first.getName(),ThemesSaverLoader.getTheme(first.getName()).getName());
        Assertions.assertEquals(second.getName(),ThemesSaverLoader.getTheme(second.getName()).getName());
        //удаление тем
        ThemesSaverLoader.delTheme(first.getName());
        ThemesSaverLoader.delTheme(second.getName());
        Assertions.assertNull(ThemesSaverLoader.getTheme(first.getName()));
        Assertions.assertNull(ThemesSaverLoader.getTheme(second.getName()));
    }

    @Test
    void saveLoadWithFilenames() throws IOException, ClassNotFoundException {
        Theme first = new Theme();
        first.setName("1firstName");

        ThemesSaverLoader.saveTheme(first,"filename");
        Assertions.assertEquals(first.getName(),ThemesSaverLoader.loadTheme("filename").getName());
    }
}
