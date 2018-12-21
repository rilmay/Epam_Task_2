package com.epam.shapes.utility.figureUtility;

import com.epam.shapes.entity.ShapeInterface.Cube;
import com.epam.shapes.entity.ShapeInterface.Point;
import com.epam.shapes.exception.IllegalShapeArgumentException;
import com.epam.shapes.utility.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Calculator {
    private static final Logger logger = LogManager.getLogger(Calculator.class);
    private Validator validator = Validator.getValidator();
    private ShapesUtility shapesUtility = ShapesUtility.getInstance();
    private final String AXES = "xyz";

    public Calculator() {
    }

    public double getCubeSurfaceArea(Cube cube) throws IllegalShapeArgumentException {
        if (!validator.isValid(cube)) {
            logger.error("Incorrect cube arguments, cube: " + cube);
            throw new IllegalShapeArgumentException("Incorrect cube");
        }
        return shapesUtility.rounding(Math.pow(cube.getHeight(), 2.0) * 6.0);
    }

    public double getCubeVolume(Cube cube) throws IllegalShapeArgumentException {
        if (!validator.isValid(cube)) {
            logger.error("Incorrect cube arguments, cube:" + cube);
            throw new IllegalShapeArgumentException("Incorrect cube arguments, cube: " + cube);
        }
        return shapesUtility.rounding(Math.pow(cube.getHeight(), 3.0));
    }

    public boolean isCube(Cube cube) {
        return validator.isValid(cube);
    }

    public boolean isOnPlane(Cube cube) throws IllegalShapeArgumentException {
        if (!validator.isValid(cube)) {
            logger.error("Incorrect cube arguments, cube: " + cube);
            throw new IllegalShapeArgumentException("Incorrect cube");
        }

        Point CentrePoint = cube.getCentrePoint();
        for (double i : shapesUtility.toArray(CentrePoint)) {
            if (Math.abs(i) == (cube.getHeight() / 2)) {
                return true;
            }
        }
        return false;
    }

    public String dividedByPlane(Cube cube, String plane) throws IllegalShapeArgumentException {
        if (!validator.isValid(cube)) {
            logger.error("Incorrect cube arguments, cube: " + cube);
            throw new IllegalShapeArgumentException("Incorrect cube");
        }

        int whichPlane = -1;
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            if (plane.indexOf(AXES.charAt(i)) == -1) {
                whichPlane = i;
                counter++;
            }
        }
        if (plane.length() != 2 || counter != 1) {
            logger.error("Incorrect plane arguments, plane: " + plane);
            throw new IllegalShapeArgumentException("Incorrect plane parameters");
        }

        Point CentrePoint = cube.getCentrePoint();
        double part1 = shapesUtility.toArray(CentrePoint)[whichPlane] - (cube.getHeight() / 2);
        double part2 = part1 + cube.getHeight();

        if (part1 * part2 >= 0.0) {
            return null;
        }
        double output = Math.abs(part1 / part2);
        return "1.0:" + shapesUtility.rounding((output > 1.0) ? output : 1.0 / output);
    }

    public double[] getHypotenuzesFromOrign(Point point) {
        double[] input = shapesUtility.toArray(point);
        double[] result = new double[3];
        for (int i = 0; i < 3; i++) {
            result[i] = Math.hypot(input[i], input[(i == 2) ? 0 : i + 1]);
        }
        return result;
    }
}
