package com.epam.shapes.utility.fileUtility;

import com.epam.shapes.exception.ParsedFileException;
import com.epam.shapes.factory.CommonUtilitiesFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class FileReaderTest {
    FileReader fileReader = CommonUtilitiesFactory.getInstance().getFileReader();
    List<String> list;

    @Test
    public void testSuccessRead() throws ParsedFileException {
        list = new ArrayList<>();
        list.add("1.0 1.0 1.0 3.0");
        Assert.assertEquals(list, fileReader.read("FilesToParse/GoodCube.txt"));
    }

    @Test(expectedExceptions = ParsedFileException.class)
    public void testFailedRead() throws ParsedFileException {
        fileReader.read("lol.txt");
    }
}
