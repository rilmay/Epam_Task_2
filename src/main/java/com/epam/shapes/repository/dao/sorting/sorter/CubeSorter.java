package com.epam.shapes.repository.dao.sorting.sorter;

import com.epam.shapes.repository.dao.sorting.comparator.cubeComparator.CoordComp;
import com.epam.shapes.repository.dao.sorting.comparator.cubeComparator.IdComp;
import com.epam.shapes.repository.dao.sorting.comparator.cubeComparator.NameComp;
import com.epam.shapes.repository.savingWrapper.wrapperInterface.CubeWrapper;
import com.epam.shapes.repository.warehouse.warehouseInterface.Warehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CubeSorter {
    private static final CubeSorter INSTANCE = new CubeSorter();
    private Logger logger = LogManager.getLogger(CubeSorter.class);

    public static CubeSorter getInstance() {
        return INSTANCE;
    }

    private CubeSorter() {
    }

    public List<CubeWrapper> sortBy(Warehouse warehouse, CubeWrapperEnum cubeWrapperEnum) {

        LinkedList<CubeWrapper> result = new LinkedList<CubeWrapper>(warehouse.getCubes().values());

        switch (cubeWrapperEnum) {
            case NAME:
                Collections.sort(result, new NameComp());
                logger.info("Successful sorting by name");
                return result;
            case ID:
                Collections.sort(result, new IdComp());
                logger.info("Successful sorting by id");
                return result;
            case COORDINATE_X:
                Collections.sort(result, new CoordComp(0));
                logger.info("Successful sorting by coordinate X");
                return result;
            case COORDINATE_Y:
                Collections.sort(result, new CoordComp(1));
                logger.info("Successful sorting by coordinate Y");
                return result;
            case COORDINATE_Z:
                Collections.sort(result, new CoordComp(2));
                logger.info("Successful sorting by coordinate Z");
                return result;
            default:
                logger.info("No sorting was applied");
                return result;
        }
    }
}
