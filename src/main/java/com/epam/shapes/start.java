package com.epam.shapes;

import com.epam.shapes.entity.ShapeImpl.CubeImpl;
import com.epam.shapes.entity.ShapeInterface.Cube;
import com.epam.shapes.repository.dao.cubeDaoImpl.CubeDaoImpl;
import com.epam.shapes.repository.dao.cubeDaoInterface.CubeDao;
import com.epam.shapes.repository.dao.sorting.sorter.CubeSorter;
import com.epam.shapes.repository.dao.sorting.sorter.CubeWrapperEnum;
import com.epam.shapes.repository.savingWrapper.wrapperImpl.CubeWrapperImpl;
import com.epam.shapes.repository.savingWrapper.wrapperInterface.CubeWrapper;
import com.epam.shapes.repository.warehouse.warehouseImpl.CubeWarehouse;
import com.epam.shapes.repository.warehouse.warehouseInterface.Warehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class start {
    private static Logger logger = LogManager.getLogger(start.class);

    public static void main(String args[]) throws Exception {
        //Calculator calculator = new Calculator();
        Cube cube = new CubeImpl(1.5, 1.0, 2.0, 2.0, 2.0, 2.0);
        Cube cube2 = new CubeImpl(1.5, 1.0, -1.0, 3.0, 3.0, 3.0);
        Cube cube3 = new CubeImpl(1.5, 1.0, 0.0, 4.0, 4.0, 4.0);
        Cube cube4 = new CubeImpl(1.5, 1.0, 7.0, 5.0, 5.0, 5.0);

        CubeWrapper cubeWrapper1 = new CubeWrapperImpl(cube, "A");
        CubeWrapper cubeWrapper2 = new CubeWrapperImpl(cube2, "B");
        CubeWrapper cubeWrapper3 = new CubeWrapperImpl(cube3, "C");
        CubeWrapper cubeWrapper4 = new CubeWrapperImpl(cube4, "D");

        Warehouse warehouse = new CubeWarehouse();
        warehouse.addShape(cubeWrapper1);
        warehouse.addShape(cubeWrapper2);
        warehouse.addShape(cubeWrapper3);
        warehouse.addShape(cubeWrapper4);
        CubeDao cubeDao = new CubeDaoImpl(warehouse);

        show(cubeDao);

        System.out.println("\n\nChanges");
        cubeWrapper1.setName("NeWName");
        cubeWrapper1.setWidth(0.00001);
        warehouse.removeShape(cubeWrapper2);
        show(cubeDao);
        CubeSorter cubeSorter = CubeSorter.getInstance();

        System.out.println("\n sorting\n");

        for(Object i: cubeSorter.sortBy(warehouse,CubeWrapperEnum.ID)){
            System.out.println(i);
        }


    }

    static void show(CubeDao cubeDao) {
        System.out.println("\n!!!                ALL           !!!\r\n");

        for (Object i : cubeDao.getAll()) {
            System.out.print(i.toString() + " ");
        }

        System.out.println("\n name\r\n");


        for (Object i : cubeDao.searchByName("A")) {
            System.out.print(i.toString() + " ");
        }

        System.out.println("\n" + "distance\r\n");

        for (Object i : cubeDao.searchByDistanceFromOrigin(3.0)) {
            System.out.print(i.toString() + " ");
        }

        System.out.println("\n surf\r\n");

        for (Object i : cubeDao.searchBySurfaceArea(1.0, 50.0)) {
            System.out.print(i.toString() + " ");
        }

        System.out.println("\n vol\r\n");

        for (Object i : cubeDao.searchByVolume(2.0, 100.0)) {
            System.out.print(i.toString() + " ");
        }
    }
}
