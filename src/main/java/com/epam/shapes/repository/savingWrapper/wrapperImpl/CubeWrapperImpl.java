package com.epam.shapes.repository.savingWrapper.wrapperImpl;

import com.epam.shapes.entity.ShapeInterface.Cube;
import com.epam.shapes.entity.ShapeInterface.Point;
import com.epam.shapes.exception.IllegalShapeArgumentException;
import com.epam.shapes.exception.RepositoryException;
import com.epam.shapes.factory.CommonUtilitiesFactory;
import com.epam.shapes.repository.repositoryUtility.IdGenerator;
import com.epam.shapes.repository.savingWrapper.wrapperInterface.CubeWrapper;
import com.epam.shapes.repository.warehouse.warehouseInterface.Warehouse;
import com.epam.shapes.utility.figureUtility.Calculator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class CubeWrapperImpl implements CubeWrapper {
    private Logger logger = LogManager.getLogger(CubeWrapperImpl.class);
    private List<Warehouse> listeners = new LinkedList<>();
    private String name;
    private String id;
    private Cube cube;
    private Calculator calculator = CommonUtilitiesFactory.getInstance().getCalculator();
    private double surfaceArea;
    private double volume;


    private void recalculating() throws IllegalShapeArgumentException {
        try {
            surfaceArea = calculator.getCubeSurfaceArea(cube);
            volume = calculator.getCubeVolume(cube);
        } catch (IllegalArgumentException e) {
            logger.error("Failure to update shape, cause: " + e.getMessage());
            throw new IllegalShapeArgumentException(e.getMessage());
        }
    }

    @Override
    public double getSurfaceArea() {
        return surfaceArea;
    }

    @Override
    public double getVolume() {
        return volume;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) throws IllegalShapeArgumentException {
        this.name = name;
        update();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void update() throws IllegalShapeArgumentException, RepositoryException {
        recalculating();
        Iterator it = listeners.iterator();
        while (it.hasNext()) {
            ((Warehouse) it.next()).onUpdate(this);
        }
    }

    @Override
    public void addListener(Warehouse warehouse) {
        listeners.add(warehouse);
    }

    @Override
    public void removeListener(Warehouse warehouse) {
        listeners.remove(warehouse);
    }

    @Override
    public Point getCentrePoint() {
        return cube.getCentrePoint();
    }

    @Override
    public Double getWidth() {
        return cube.getWidth();
    }

    @Override
    public Double getHeight() {
        return cube.getHeight();
    }

    @Override
    public Double getLength() {
        return cube.getLength();
    }

    @Override
    public void setCentrePoint(Point centrePoint) throws IllegalShapeArgumentException {
        cube.setCentrePoint(centrePoint);
        update();
    }

    @Override
    public void setWidth(Double width) throws IllegalShapeArgumentException {
        cube.setWidth(width);
        cube.setLength(width);
        cube.setHeight(width);
        update();
    }

    @Override
    public void setHeight(Double height) throws IllegalShapeArgumentException {
        cube.setWidth(height);
        cube.setLength(height);
        cube.setHeight(height);
        update();
    }

    @Override
    public void setLength(Double length) throws IllegalShapeArgumentException {
        cube.setWidth(length);
        cube.setLength(length);
        cube.setHeight(length);
        update();
    }

    public CubeWrapperImpl(Cube cube, String name) {
        this.cube = cube;
        this.name = name;
        this.id = IdGenerator.generate(cube, name);
        this.surfaceArea = calculator.getCubeSurfaceArea(cube);
        this.volume = calculator.getCubeVolume(cube);
    }

    @Override
    public String toString() {
        return "ID: " + id + " NAME: " + name + " " + cube;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CubeWrapperImpl that = (CubeWrapperImpl) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(id, that.id) &&
                Objects.equals(cube, that.cube);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, cube);
    }
}
