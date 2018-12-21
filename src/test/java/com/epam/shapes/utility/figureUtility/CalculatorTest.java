package com.epam.shapes.utility.figureUtility;

import com.epam.shapes.entity.ShapeImpl.CubeImpl;
import com.epam.shapes.entity.ShapeInterface.Cube;
import com.epam.shapes.exception.IllegalShapeArgumentException;
import com.epam.shapes.factory.CommonUtilitiesFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CalculatorTest {
    Calculator calculator;
    Cube cube;
    Cube wrongCube;


    @BeforeTest
    public void init() {
        calculator = CommonUtilitiesFactory.getInstance().getCalculator();
        cube = new CubeImpl(1.5, 0.5, 0.0, 2.0, 2.0, 2.0);
        wrongCube = new CubeImpl(1.5, 0.5, 0.0, 2.0, 0.0, 2.1);
    }

    @Test
    public void testGetCubeSurfaceArea() {
        Assert.assertEquals(24.0, calculator.getCubeSurfaceArea(cube), 0.5);
    }

    @Test
    public void testGetCubeVolume() {
        Assert.assertEquals(8.0, calculator.getCubeVolume(cube), 0.5);
    }

    @Test
    public void testIsCube() {
        Assert.assertTrue(calculator.isCube(cube));
    }

    @Test
    public void testIsOnPlane() {
        Assert.assertFalse(calculator.isOnPlane(cube));
    }

    @Test
    public void testDividedByPlane() {
        Assert.assertEquals("1.0:3.0", calculator.dividedByPlane(cube, "xz"));
    }

    @Test
    public void testNotDividedByPlane() {
        Assert.assertEquals(null, calculator.dividedByPlane(cube, "yz"));
    }

    @Test(expectedExceptions = IllegalShapeArgumentException.class)
    public void testWrongCube() throws IllegalArgumentException {
        Assert.assertEquals(false, calculator.isOnPlane(wrongCube));
    }

}
