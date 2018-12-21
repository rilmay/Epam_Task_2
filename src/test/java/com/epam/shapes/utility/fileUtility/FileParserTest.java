package com.epam.shapes.utility.fileUtility;

import com.epam.shapes.entity.ShapeImpl.CubeImpl;
import com.epam.shapes.entity.ShapeInterface.Cube;
import com.epam.shapes.exception.ParsedFileException;
import com.epam.shapes.factory.CommonUtilitiesFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class FileParserTest {
    FileParser fileParser;

    @BeforeTest
    public void init() {
        fileParser = CommonUtilitiesFactory.getInstance().getFileParser();
    }

    @Test
    public void testStringParse() throws ParsedFileException {
        String parsedString = "1.0 1.0 1.0 2.0";
        Cube expectedCube = new CubeImpl(1.0, 1.0, 1.0, 2.0, 2.0, 2.0);
        Assert.assertEquals(expectedCube, fileParser.parse(parsedString));
    }

    @Test
    public void testFailedParse() throws ParsedFileException {
        String parsedString = "1.0 1gh.0 1.0 2.0 2.0";
        Assert.assertEquals(null, fileParser.parse(parsedString));
    }

    @Test
    public void testListParse() throws ParsedFileException {
        List<String> list = new ArrayList<>();
        list.add("1.0 1.0 1.0 2.0");
        list.add("1.0 0.9 1.0 0.01 0.01");
        List<Object> cubes = new ArrayList<>();
        cubes.add(new CubeImpl(1.0, 1.0, 1.0, 2.0, 2.0, 2.0));
        cubes.add(new CubeImpl(1.0, 0.9, 1.0, 0.01, 0.01, 0.01));
        Assert.assertEquals(cubes, fileParser.parse(list));
    }
}
