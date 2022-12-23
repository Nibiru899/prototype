import controllers.BlankController;
import controllers.ThemeController;
import data.Blank;
import data.Theme;
import fileworkers.BlankSaverLoader;
import fileworkers.ThemesSaverLoader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Класс для интеграционного тестирования - првоерки корректной работы контроллера и его SaverLoader'а
 * Стоит отметить, что корректная работа контроллера целиком зависит от корректности его связи с SaverLaoder'ом.
 */
public class integretionTests {

    @BeforeEach
    void mkDirs(){
        File dir = new File("blanks");
        dir.mkdirs();
        File dir2 = new File("themes");
        dir2.mkdirs();
    }

    @AfterEach
    void delDirs(){
        File dir = new File("blanks");
        delInnr(dir);
        File dir2 = new File("themes");
        delInnr(dir2);
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
    void blankcontrollerBlankSaverLoaderIntegrationTest(){
        Blank testBlank = new Blank();
        testBlank.setName("testName");
        //сохранить и получить бланк
        BlankController.saveBlank(testBlank);
        //если взаимодействие корректно, оба должны вернуть один результат
        Assertions.assertEquals(BlankSaverLoader.getBlank(testBlank.getName()).getName(),BlankController.getBlank(testBlank.getName()).getName());
        //возвращенный результат также должен быть верным
        Assertions.assertEquals(testBlank.getName(),BlankController.getBlank(testBlank.getName()).getName());

        //проверить наличие
        Assertions.assertTrue(BlankController.isExist(testBlank.getName()));

        //проверить удаление
        BlankController.delBlank(testBlank.getName());
        //проверить через Saverloader. Если совпадает - взаимодействие верно
        Assertions.assertNull(BlankSaverLoader.getBlank(testBlank.getName()));
        //проверить остутвие
        Assertions.assertFalse(BlankController.isExist(testBlank.getName()));
    }

    @Test
    void themeControllerThemeSaverLoaderIntegretionTest() throws IOException, ClassNotFoundException {
        String themeName1 = "theme1";
        String themeName2 = "theme2";

        //создаем темы
        ThemeController.newTheme(themeName1);
        ThemeController.newTheme(themeName2);
        //если созданы корректно, их можно получить через SaverLoader
        Theme theme1 = ThemesSaverLoader.getTheme(themeName1);
        Theme theme2 = ThemesSaverLoader.getTheme(themeName2);
        Assertions.assertNotNull(theme1);
        Assertions.assertNotNull(theme2);
        //должны совпадать с полученным из контроллера
        Assertions.assertEquals(theme1.getName(),ThemeController.getTheme(theme1.getName()).getName());
        Assertions.assertEquals(theme2.getName(),ThemeController.getTheme(theme2.getName()).getName());
        //имена
        List<String> names = Arrays.asList(ThemeController.getNames());
        Assertions.assertTrue(names.contains(themeName1));
        Assertions.assertTrue(names.contains(themeName2));
        Assertions.assertEquals(2,names.size());
        Assertions.assertEquals(2,ThemesSaverLoader.getAllThemes().size());
        //удаление
        ThemeController.delTheme(themeName1);
        ThemeController.delTheme(themeName2);
        Assertions.assertEquals(0,ThemesSaverLoader.getAllThemes().size());
        //переименование (удаление + создание)
        ThemesSaverLoader.saveTheme(theme1);
        ThemeController.renameTheme(themeName1,"theme3");
        Assertions.assertNotNull(ThemesSaverLoader.getTheme("theme3"));

    }



}
