package com.epam.shapes.utility.fileUtility;

import com.epam.shapes.entity.ShapeImpl.CubeImpl;
import com.epam.shapes.exception.ParsedFileException;
import com.epam.shapes.utility.validator.FileValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class FileParser {
    private static final Logger logger = LogManager.getLogger(FileParser.class);
    private FileValidator fileValidator = FileValidator.getFileValidator();

    public List<Object> parse(List<String> lines) throws ParsedFileException {
        if (lines == null) {
            logger.error("Parsing argument is nullpointer ");
            throw new ParsedFileException("Parsing argument is nullpointer");
        }
        logger.info("File parsing is processed");
        List<Object> objects = new ArrayList<>();
        Iterator iterator = lines.iterator();
        while (iterator.hasNext()) {
            Object currentObject = parse((String) iterator.next());
            if (currentObject != null) {
                objects.add(currentObject);
            }
        }
        if (objects.size() == 0) {
            logger.error("Parsing file does not contain valid cube arguments");
            throw new ParsedFileException("Failed file parsing");
        }
        logger.info("Successful file parsing");
        return objects;
    }

    public Object parse(String input) {
        if (!fileValidator.isValid(input)) {
            logger.warn("Parsing string does not contain valid cube arguments ");
            return null;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(input);
        int size = stringTokenizer.countTokens();
        double temp;
        return new CubeImpl(Double.valueOf(stringTokenizer.nextToken()),
                Double.valueOf(stringTokenizer.nextToken()),
                Double.valueOf(stringTokenizer.nextToken()),
                temp = Double.valueOf(stringTokenizer.nextToken()),
                ((size > 4) ? Double.valueOf(stringTokenizer.nextToken()) : temp),
                ((size > 5) ? Double.valueOf(stringTokenizer.nextToken()) : temp));
    }
}
