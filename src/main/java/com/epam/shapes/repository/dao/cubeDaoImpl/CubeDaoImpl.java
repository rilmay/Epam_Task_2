package com.epam.shapes.repository.dao.cubeDaoImpl;

import com.epam.shapes.entity.ShapeInterface.Point;
import com.epam.shapes.exception.RepositoryException;
import com.epam.shapes.factory.CommonUtilitiesFactory;
import com.epam.shapes.repository.dao.cubeDaoInterface.CubeDao;
import com.epam.shapes.repository.savingWrapper.wrapperInterface.CubeWrapper;
import com.epam.shapes.repository.warehouse.warehouseInterface.Warehouse;
import com.epam.shapes.utility.figureUtility.Calculator;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CubeDaoImpl implements CubeDao {
    private Calculator calculator = CommonUtilitiesFactory.getInstance().getCalculator();
    private Warehouse warehouse;
    private Map<String, CubeWrapper> cubes;
    private Validator validator = Validator.getValidator();
    private Logger logger = LogManager.getLogger(CubeDaoImpl.class);
    

    @Override
    public void add(CubeWrapper cubeWrapper) throws RepositoryException {
        warehouse.addShape(cubeWrapper);
    }

    @Override
    public void remove(CubeWrapper cubeWrapper) throws RepositoryException {
        warehouse.removeShape(cubeWrapper);
    }

    @Override
    public List<Object> getAll() {
        return new LinkedList<>(cubes.values());
    }

    public CubeDaoImpl(Warehouse warehouse) {
        this.warehouse = warehouse;
        this.cubes = warehouse.getCubes();
    }

    @Override
    public List<Object> searchById(String id) {
        
        if (id == null) {
            logger.error("Invalid arguments");
            throw new IllegalShapeArgumentException("Invalid arguments");
        }
        
        List<Object> result = new LinkedList<>();
        result.add(cubes.get(id));
        return result;
    }

    @Override
    public List<Object> searchByName(String name) {
        
        
        if (name == null) {
            logger.error("Invalid arguments");
            throw new IllegalShapeArgumentException("Invalid arguments");
        }
        
        List<Object> result = new LinkedList<>();
        for (CubeWrapper cube : cubes.values()) {
            if (name.equals(cube.getName())) {
                result.add(cube);
            }
        }
        return result;
    }

    @Override
    public List<Object> searchBySurfaceArea(double min, double max) {

        if (!validator.isValid(min) && !validator.isValid(max)) {
            logger.error("Invalid arguments");
            throw new IllegalShapeArgumentException("Invalid arguments");
        }
        
        List<Object> result = new LinkedList<>();
        for (CubeWrapper cube : cubes.values()) {
            if (cube.getSurfaceArea() >= min && cube.getSurfaceArea() <= max) {
                result.add(cube);
            }
        }
        return result;
    }

    @Override
    public List<Object> searchByVolume(double min, double max) {
        
        if (!validator.isValid(min) && !validator.isValid(max)) {
            logger.error("Invalid arguments");
            throw new IllegalShapeArgumentException("Invalid arguments");
        }
        
        List<Object> result = new LinkedList<>();
        for (CubeWrapper cube : cubes.values()) {
            if (cube.getVolume() >= min && cube.getVolume() <= max) {
                result.add(cube);
            }
        }
        return result;
    }

    @Override
    public List<Object> searchByDistanceFromOrigin(double distance) {
        
        if (!validator.isValid(distance)) {
            logger.error("Invalid arguments");
            throw new IllegalShapeArgumentException("Invalid arguments");
        }
        
        List<Object> result = new LinkedList<>();
        for (CubeWrapper cube : cubes.values()) {
            Point centre = cube.getCentrePoint();
            boolean flag = true;
            for (double hypot : calculator.getHypotenuzesFromOrign(centre)) {
                if (hypot > distance) {
                    flag = false;
                }
            }
            if (flag) {
                result.add(cube);
            }
        }
        return result;
    }
}
