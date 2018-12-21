package com.epam.shapes.repository.savingWrapper.wrapperInterface;

import com.epam.shapes.exception.RepositoryException;
import com.epam.shapes.repository.warehouse.warehouseInterface.Warehouse;
import com.epam.shapes.entity.ShapeInterface.Cube;

public interface CubeWrapper extends Cube {
    void update() throws RepositoryException;
    void addListener(Warehouse warehouse);
    void removeListener(Warehouse warehouse);
    String getName();
    void setName(String name);
    String getId();
    double getSurfaceArea();
    double getVolume();
}
