package com.epam.shapes.utility.figureUtility;

import com.epam.shapes.entity.ShapeInterface.Point;
import com.epam.shapes.exception.IllegalShapeArgumentException;
import com.epam.shapes.utility.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShapesUtility {
    private static final ShapesUtility INSTANCE = new ShapesUtility();
    private static final Logger logger = LogManager.getLogger(ShapesUtility.class);
    private Validator validator = Validator.getValidator();

    private ShapesUtility() {

    }

    public static ShapesUtility getInstance() {
        return INSTANCE;
    }

    public double[] toArray(Point point) {
        double[] coordinates;
        if (!validator.isValid(point)) {
            coordinates = new double[]{0.0, 0.0, 0.0};
            logger.error("Incorrect point arguments, point: " + point);
            //throw new IllegalShapeArgumentException("Incorrect point");
        } else {
            coordinates = new double[3];
            coordinates[0] = point.getX();
            coordinates[1] = point.getY();
            coordinates[2] = point.getZ();
        }
        return coordinates;
    }

    public double rounding(double d) throws IllegalShapeArgumentException {
        if (!validator.isValid(d)) {
            logger.error("Incorrect double argument, double: " + d);
            throw new IllegalShapeArgumentException("Incorrect double");
        }
        return Math.ceil(d * 10) / 10;
    }
}
