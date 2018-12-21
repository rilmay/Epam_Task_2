package com.epam.shapes.repository.dao.cubeDaoInterface;

import com.epam.shapes.exception.RepositoryException;
import com.epam.shapes.repository.savingWrapper.wrapperInterface.CubeWrapper;

import java.util.List;

public interface CubeDao {

    void add(CubeWrapper cubeWrapper) throws RepositoryException;

    void remove(CubeWrapper cubeWrapper) throws RepositoryException;

    List<Object> getAll();

    List<Object> searchById(String id);

    List<Object> searchByName(String name);

    List<Object> searchBySurfaceArea(double min, double max);

    List<Object> searchByVolume(double min, double max);

    List<Object> searchByDistanceFromOrigin(double distance);
}
