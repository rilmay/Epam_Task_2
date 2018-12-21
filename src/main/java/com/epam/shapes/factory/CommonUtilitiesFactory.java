package com.epam.shapes.factory;

import com.epam.shapes.utility.figureUtility.Calculator;
import com.epam.shapes.utility.fileUtility.FileParser;
import com.epam.shapes.utility.fileUtility.FileReader;

public class CommonUtilitiesFactory {

    private final static CommonUtilitiesFactory INSTANCE = new CommonUtilitiesFactory();
    private Calculator calculator = new Calculator();
    private FileParser fileParser = new FileParser();
    private FileReader fileReader = new FileReader();

    private CommonUtilitiesFactory() {
    }

    public static CommonUtilitiesFactory getInstance() {
        return INSTANCE;
    }

    public Calculator getCalculator() {
        return calculator;
    }

    public FileParser getFileParser() {
        return fileParser;
    }

    public FileReader getFileReader() {
        return fileReader;
    }
}
