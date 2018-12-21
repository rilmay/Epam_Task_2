package com.epam.shapes.utility.validator;


import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FileValidatorTest {
    FileValidator fileValidator;
    String string;
    String wrongstring;

    @BeforeTest
    public void init() {
        fileValidator = FileValidator.getFileValidator();
        string = "1.0 +1.0 -6.4      22.0";
        wrongstring = "1.3 - 1h 1.0 2.0";
    }

    @Test
    public void testTrueIsvalid() {
        Assert.assertTrue(fileValidator.isValid(string));
    }

    @Test
    public void testFalseIsValid() {
        Assert.assertFalse(fileValidator.isValid(wrongstring));
    }
}
