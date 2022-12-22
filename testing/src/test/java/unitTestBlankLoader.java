import data.Blank;
import data.Theme;
import fileworkers.BlankSaverLoader;
import fileworkers.ThemesSaverLoader;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;

public class unitTestBlankLoader {

    @BeforeEach
    void mkDirs(){
        File dir = new File("blanks");
        dir.mkdirs();
    }

    @AfterEach
    void delDirs(){
        File dir = new File("blanks");
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
        Blank first = new Blank();
        first.setName("1firstName");
        Blank second = new Blank();
        second.setName("2secondName");
        Assertions.assertEquals(0, BlankSaverLoader.getAllBlanks().size());
        Assertions.assertNull(BlankSaverLoader.getBlank(first.getName()));
        //загрузка тем
        BlankSaverLoader.saveBlank(first);
        BlankSaverLoader.saveBlank(second);
        Assertions.assertEquals(2,BlankSaverLoader.getAllBlanks().size());
        //получение тем
        Assertions.assertEquals(first.getName(),BlankSaverLoader.getBlank(first.getName()).getName());
        Assertions.assertEquals(second.getName(),BlankSaverLoader.getBlank(second.getName()).getName());
        //удаление тем
        BlankSaverLoader.delBlank(first.getName());
        BlankSaverLoader.delBlank(second.getName());
        Assertions.assertNull(BlankSaverLoader.getBlank(first.getName()));
        Assertions.assertNull(BlankSaverLoader.getBlank(second.getName()));
    }

    @Test
    void saveLoadWithFilenames() throws IOException, ClassNotFoundException {
        Blank first = new Blank();
        first.setName("1firstName");

        BlankSaverLoader.saveBlank(first,"filename");
        Assertions.assertEquals(first.getName(),BlankSaverLoader.loadBlank("filename").getName());
    }

}
