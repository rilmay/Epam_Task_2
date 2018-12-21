package com.epam.shapes.utility.validator;

import com.epam.shapes.entity.ShapeImpl.CubeImpl;
import com.epam.shapes.entity.ShapeImpl.PointImpl;
import com.epam.shapes.entity.ShapeInterface.Cube;
import com.epam.shapes.entity.ShapeInterface.Point;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ValidatorTest {
    Validator validator;
    Cube cube;
    Cube wrongCube;
    Point point;
    Point wrongPoint;

    @BeforeTest
    public void init() {
        validator = Validator.getValidator();
        cube = new CubeImpl(0.0, 0.0, 0.0, 2.0, 2.0, 2.0);
        wrongCube = new CubeImpl(0.0, null, 0.0, 2.0, -2.0, 2.0);
        point = new PointImpl(2.0, 3.1, 3.2);
        wrongPoint = new PointImpl(3.0, null, 1.0);
    }

    @Test
    public void successfulCubeValidation() {
        Assert.assertTrue(validator.isValid(cube));
    }

    @Test
    public void failedCubeValidation() {
        Assert.assertFalse(validator.isValid(wrongCube));
    }

    @Test
    public void successfulPointValidation() {
        Assert.assertTrue(validator.isValid(point));
    }

    @Test
    public void failedPointValidation() {
        Assert.assertFalse(validator.isValid(wrongPoint));
    }

}
