package com.epam.shapes.repository.warehouse.warehouseImpl;

import com.epam.shapes.exception.RepositoryException;
import com.epam.shapes.repository.savingWrapper.wrapperInterface.CubeWrapper;
import com.epam.shapes.repository.warehouse.warehouseInterface.Warehouse;
import com.epam.shapes.utility.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class CubeWarehouse implements Warehouse {
    private Logger logger = LogManager.getLogger(CubeWarehouse.class);
    private Validator validator = Validator.getValidator();
    private Map<String, CubeWrapper> cubes = new HashMap<>();

    @Override
    public void onUpdate(CubeWrapper cubeWrapper) throws RepositoryException {
        if (validator.isValid(cubeWrapper) && cubes.containsKey(cubeWrapper.getId())) {
            cubes.put(cubeWrapper.getId(), cubeWrapper);
            logger.info("Successful shape update");
        } else {
            logger.error("Unable to save shape");
            throw new RepositoryException("Unable to save shape");
        }
    }

    @Override
    public void addShape(CubeWrapper cubeWrapper) throws RepositoryException {
        if (validator.isValid(cubeWrapper) && !cubes.containsKey(cubeWrapper.getId())) {
            cubes.put(cubeWrapper.getId(), cubeWrapper);
            cubeWrapper.addListener(this);
            logger.info("Successful shape add");
        } else {
            logger.error("Unable to save shape");
            throw new RepositoryException("Unable to save shape");
        }
    }

    @Override
    public void removeShape(CubeWrapper cubeWrapper) throws RepositoryException {
        if (validator.isValid(cubeWrapper) && cubes.containsKey(cubeWrapper.getId())) {
            cubes.remove(cubeWrapper.getId());
            cubeWrapper.removeListener(this);
            logger.info("Successful shape remove");
        } else {
            logger.error("Unable to remove shape");
            throw new RepositoryException("Unable to remove shape");
        }
    }

    public Map<String, CubeWrapper> getCubes() {
        return cubes;
    }
}
