package com.epam.shapes.repository.dao.cubeDaoImpl;

import com.epam.shapes.entity.ShapeImpl.CubeImpl;
import com.epam.shapes.entity.ShapeInterface.Cube;
import com.epam.shapes.exception.RepositoryException;
import com.epam.shapes.factory.CommonUtilitiesFactory;
import com.epam.shapes.repository.dao.cubeDaoInterface.CubeDao;
import com.epam.shapes.repository.savingWrapper.wrapperImpl.CubeWrapperImpl;
import com.epam.shapes.repository.savingWrapper.wrapperInterface.CubeWrapper;
import com.epam.shapes.repository.warehouse.warehouseImpl.CubeWarehouse;
import com.epam.shapes.repository.warehouse.warehouseInterface.Warehouse;
import com.epam.shapes.utility.figureUtility.Calculator;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class CubeDaoImplTest {
    Warehouse warehouse;
    CubeDao cubeDao;
    CubeWrapper cubeWrapper1;
    CubeWrapper cubeWrapper2;
    CubeWrapper cubeWrapper3;
    CubeWrapper cubeWrapper4;
    Calculator calculator = CommonUtilitiesFactory.getInstance().getCalculator();
    Cube cube2;

    @BeforeTest
    public void init() {
        warehouse = new CubeWarehouse();
        cubeDao = new CubeDaoImpl(warehouse);

        Cube cube1 = new CubeImpl(1.5, 1.0, 2.0, 2.0, 2.0, 2.0);
        cube2 = new CubeImpl(1.5, 1.0, -1.0, 3.0, 3.0, 3.0);
        Cube cube3 = new CubeImpl(1.5, 1.0, 0.0, 4.0, 4.0, 4.0);
        Cube cube4 = new CubeImpl(1.5, 1.0, 7.0, 5.0, 5.0, 5.0);

        cubeWrapper1 = new CubeWrapperImpl(cube1, "A");
        cubeWrapper2 = new CubeWrapperImpl(cube2, "B");
        cubeWrapper3 = new CubeWrapperImpl(cube3, "C");
        cubeWrapper4 = new CubeWrapperImpl(cube4, "D");

        cubeDao.add(cubeWrapper1);
        cubeDao.add(cubeWrapper2);
        cubeDao.add(cubeWrapper3);
    }

    @Test
    public void testAdd() {
        cubeDao.add(cubeWrapper4);
        Assert.assertTrue(cubeDao.getAll().contains(cubeWrapper4));
    }

    @Test
    public void testRemove() {
        int size = cubeDao.getAll().size();
        cubeDao.remove(cubeWrapper2);
        int size2 = cubeDao.getAll().size();
        Assert.assertEquals(size - 1, size2);
    }

    @Test(expectedExceptions = RepositoryException.class)
    public void testRemoveFail() {
        Cube cube = new CubeImpl(1.5, 1.0, 2.0, 2.0, 2.0, 2.0);
        CubeWrapper cubeWrapper = new CubeWrapperImpl(cube, "xex");
        cubeDao.remove(cubeWrapper);
    }
    
    @Test(expectedExceptions = RepositoryException.class)
    public void testAddFail(){
        cubeDao.add(cubeWrapper1);
    }

    @Test
    public void testSearchByName() {
        String name = cubeWrapper1.getName();
        List<java.lang.Object> list = cubeDao.searchByName(name);
        Assert.assertEquals(cubeWrapper1, list.toArray()[0]);
    }
    
    
    @Test
    public void testSearchByID(){
        String id = cubeWrapper3.getId();
        List<java.lang.Object> list = cubeDao.searchById(id);
        Assert.assertEquals(cubeWrapper3,list.toArray()[0]);

    }

    @Test
    public void testSearchBySurfaceArea(){
        double surfaceArea = cubeWrapper3.getSurfaceArea();
        List<java.lang.Object> list = cubeDao.searchBySurfaceArea(surfaceArea,surfaceArea);
        Assert.assertEquals(cubeWrapper3,list.toArray()[0]);
    }

    @Test
    public void testSearchByVolume(){
        double volume = cubeWrapper3.getVolume();
        List<java.lang.Object> list = cubeDao.searchByVolume(volume,volume);
        Assert.assertEquals(cubeWrapper3,list.toArray()[0]);
    }



    @Test
    public void testObserver() {
        cubeWrapper2.setHeight(1.35);
        double vol = calculator.getCubeVolume(cube2);
        CubeWrapper result = (CubeWrapper) cubeDao.searchById(cubeWrapper2.getId()).toArray()[0];
        Assert.assertEquals(vol, result.getVolume());

    }
}
