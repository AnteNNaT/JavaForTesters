package ru.geekbrains.java;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;


public class FlowersTest {

    public static Path FILE_PATH = Paths.get("src").resolve("test").resolve("resources");
    private static File TEST_FILE=new File(FILE_PATH+"/test.csv");

   @Test
    public void checkWriteAndLoad() {

        String[][] data1 = new String[][]{
                {"Rose", "100", "June"},
                {"Violet", "10", "May"},
                {"Phlox", "50", "July"}
        };
        Flowers flowers1 = new Flowers(data1);
        flowers1.overwriteFile(TEST_FILE);

        Flowers flowers2 = new Flowers();
        flowers2.loadFromFile(TEST_FILE);

        Assert.assertEquals("Name",flowers2.getHeader()[0]);
        Assert.assertEquals("Rose",flowers2.getFlowers()[0][0]);
        Assert.assertTrue(flowers1.equals(flowers2));


    }
}