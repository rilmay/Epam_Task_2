package com.epam.shapes.utility.fileUtility;

import com.epam.shapes.exception.ParsedFileException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileReader {
    private static final Logger logger = LogManager.getLogger(FileReader.class);

    public FileReader() {
    }

    public List<String> read(String fileName) throws ParsedFileException {
        try {
            List<String> temp = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
            logger.info("Successful file reading, file: " + fileName);
            return temp;
        } catch (IOException e) {
            logger.error("Failed file reading, file: " + fileName);
            throw new ParsedFileException("Failed file reading");
        }
    }

}
