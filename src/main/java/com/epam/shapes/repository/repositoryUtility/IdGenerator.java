package com.epam.shapes.repository.repositoryUtility;

import java.util.Date;

public class IdGenerator {
    public static String generate(Object... objects) {
        Integer generatedValue = Math.abs((int) (new Date().hashCode() * Math.random()));
        return new String(generatedValue.toString());
    }
}
