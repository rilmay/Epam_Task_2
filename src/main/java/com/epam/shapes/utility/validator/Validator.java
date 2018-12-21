package com.epam.shapes.utility.validator;

import com.epam.shapes.entity.ShapeInterface.Cube;
import com.epam.shapes.entity.ShapeInterface.Point;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Validator {
    private static final Logger logger = LogManager.getLogger(Validator.class);
    private static final Validator INSTANCE = new Validator();

    public static Validator getValidator() {
        return INSTANCE;
    }

    private Validator() {

    }

    public boolean isValid(Double var) {
        return var != null && !var.isNaN() && !var.isInfinite();
    }

    public boolean isValid(Point point) {
        return isValid(point.getX()) &&
                isValid(point.getY()) &&
                isValid(point.getY());
    }

    public boolean isValid(Cube cube) {
        return isValid(cube.getCentrePoint()) &&
                cube.getHeight() > 0.0 &&
                cube.getHeight().equals(cube.getWidth()) &&
                cube.getHeight().equals(cube.getLength());
    }
}