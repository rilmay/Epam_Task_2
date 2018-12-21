package com.epam.shapes.repository.dao.sorting.sorter;

import com.epam.shapes.entity.ShapeImpl.CubeImpl;
import com.epam.shapes.entity.ShapeInterface.Cube;
import com.epam.shapes.repository.dao.cubeDaoImpl.CubeDaoImpl;
import com.epam.shapes.repository.dao.cubeDaoInterface.CubeDao;
import com.epam.shapes.repository.savingWrapper.wrapperImpl.CubeWrapperImpl;
import com.epam.shapes.repository.savingWrapper.wrapperInterface.CubeWrapper;
import com.epam.shapes.repository.warehouse.warehouseImpl.CubeWarehouse;
import com.epam.shapes.repository.warehouse.warehouseInterface.Warehouse;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class CubeSorterTest {
    CubeSorter cubeSorter = CubeSorter.getInstance();
    Warehouse warehouse;
    CubeDao cubeDao;
    CubeWrapper cubeWrapper1;
    CubeWrapper cubeWrapper2;
    CubeWrapper cubeWrapper3;
    CubeWrapper cubeWrapper4;

    @BeforeTest
    public void init() {
        warehouse = new CubeWarehouse();
        cubeDao = new CubeDaoImpl(warehouse);

        Cube cube1 = new CubeImpl(1.5, 1.0, 2.0, 2.0, 2.0, 2.0);
        Cube cube2 = new CubeImpl(1.5, 1.0, -1.0, 3.0, 3.0, 3.0);
        Cube cube3 = new CubeImpl(1.5, 1.0, 0.0, 4.0, 4.0, 4.0);
        Cube cube4 = new CubeImpl(1.5, 1.0, 7.0, 5.0, 5.0, 5.0);

        cubeWrapper1 = new CubeWrapperImpl(cube1, "A");
        cubeWrapper2 = new CubeWrapperImpl(cube2, "B");
        cubeWrapper3 = new CubeWrapperImpl(cube3, "C");
        cubeWrapper4 = new CubeWrapperImpl(cube4, "D");

        cubeDao.add(cubeWrapper1);
        cubeDao.add(cubeWrapper2);
        cubeDao.add(cubeWrapper3);
        cubeDao.add(cubeWrapper4);
    }

    @Test
    public void testSortByName() {
        boolean flag = true;
        char temp = 0;
        for (CubeWrapper i : cubeSorter.sortBy(warehouse, CubeWrapperEnum.NAME)) {
            char first = i.getName().charAt(0);
            if (first < temp) {
                flag = false;
            }
            temp = first;
        }
        Assert.assertTrue(flag);
    }

}
