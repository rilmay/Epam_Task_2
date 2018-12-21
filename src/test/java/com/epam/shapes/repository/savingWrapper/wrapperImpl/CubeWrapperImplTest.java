package com.epam.shapes.repository.savingWrapper.wrapperImpl;

import com.epam.shapes.entity.ShapeImpl.CubeImpl;
import com.epam.shapes.entity.ShapeInterface.Cube;
import com.epam.shapes.exception.IllegalShapeArgumentException;
import com.epam.shapes.factory.CommonUtilitiesFactory;
import com.epam.shapes.repository.savingWrapper.wrapperInterface.CubeWrapper;
import com.epam.shapes.utility.figureUtility.Calculator;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class CubeWrapperImplTest {
    Calculator calculator = CommonUtilitiesFactory.getInstance().getCalculator();
    CubeWrapper cubeWrapper1;
    CubeWrapper cubeWrapper2;
    Cube cube1;

    @BeforeTest
    public void init() {
        cube1 = new CubeImpl(1.5, 1.0, 2.0, 2.0, 2.0, 2.0);
        Cube cube2 = new CubeImpl(1.5, 1.0, -1.0, 3.0, 3.0, 3.0);

        cubeWrapper1 = new CubeWrapperImpl(cube1, "A");
        cubeWrapper2 = new CubeWrapperImpl(cube2, "B");
    }

    @Test
    public void testSetHeight() {
        double height = 3.1;
        cubeWrapper1.setHeight(height);
        Assert.assertEquals(height, cubeWrapper1.getHeight());
    }

    @Test(expectedExceptions = IllegalShapeArgumentException.class)
    public void testWrongInit() {
        Cube cubeWrong = new CubeImpl(1.5, 1.0, 0.0, 4.0, 1.0, 4.0);
        CubeWrapper wrong = new CubeWrapperImpl(cubeWrong, "Fail");
    }

    @Test
    public void testGetVolume() {
        Assert.assertEquals(calculator.getCubeVolume(cube1), cubeWrapper1.getVolume());
    }
}
