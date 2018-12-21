package com.epam.shapes.repository.warehouse.warehouseInterface;

import com.epam.shapes.exception.RepositoryException;
import com.epam.shapes.repository.savingWrapper.wrapperInterface.CubeWrapper;

import java.util.Map;

public interface Warehouse {
    void onUpdate(CubeWrapper cubeWrapper) throws RepositoryException;

    void addShape(CubeWrapper cubeWrapper) throws RepositoryException;

    void removeShape(CubeWrapper cubeWrapper) throws RepositoryException;

    Map<String, CubeWrapper> getCubes();
}
