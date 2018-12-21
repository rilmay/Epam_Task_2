package com.epam.shapes.repository.warehouse.warehouseImpl;

import com.epam.shapes.entity.ShapeImpl.CubeImpl;
import com.epam.shapes.entity.ShapeInterface.Cube;
import com.epam.shapes.exception.RepositoryException;
import com.epam.shapes.repository.savingWrapper.wrapperImpl.CubeWrapperImpl;
import com.epam.shapes.repository.savingWrapper.wrapperInterface.CubeWrapper;
import com.epam.shapes.repository.warehouse.warehouseInterface.Warehouse;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CubeWarehouseTest {
    Warehouse warehouse;
    CubeWrapper cubeWrapper1;
    CubeWrapper cubeWrapper2;
    CubeWrapper cubeWrapper3;
    CubeWrapper cubeWrapper4;

    @BeforeTest
    public void init() {
        warehouse = new CubeWarehouse();

        Cube cube1 = new CubeImpl(1.5, 1.0, 2.0, 2.0, 2.0, 2.0);
        Cube cube2 = new CubeImpl(1.5, 1.0, -1.0, 3.0, 3.0, 3.0);
        Cube cube3 = new CubeImpl(1.5, 1.0, 0.0, 4.0, 4.0, 4.0);
        Cube cube4 = new CubeImpl(1.5, 1.0, 7.0, 5.0, 5.0, 5.0);

        cubeWrapper1 = new CubeWrapperImpl(cube1, "A");
        cubeWrapper2 = new CubeWrapperImpl(cube2, "B");
        cubeWrapper3 = new CubeWrapperImpl(cube3, "C");
        cubeWrapper4 = new CubeWrapperImpl(cube4, "D");

        warehouse.addShape(cubeWrapper1);
        warehouse.addShape(cubeWrapper2);
        warehouse.addShape(cubeWrapper3);
    }

    @Test
    public void testAdd() {
        warehouse.addShape(cubeWrapper4);
        Assert.assertTrue(warehouse.getCubes().containsKey(cubeWrapper4.getId()));
    }

    @Test
    public void testRemove() {
        int size = warehouse.getCubes().size();
        warehouse.removeShape(cubeWrapper2);
        int size2 = warehouse.getCubes().size();
        Assert.assertEquals(size - 1, size2);
    }

    @Test(expectedExceptions = RepositoryException.class)
    public void testRemoveFail() {
        Cube cube = new CubeImpl(1.5, 1.0, 2.0, 2.0, 2.0, 2.0);
        CubeWrapper cubeWrapper = new CubeWrapperImpl(cube, "xex");
        warehouse.removeShape(cubeWrapper);
    }
}
