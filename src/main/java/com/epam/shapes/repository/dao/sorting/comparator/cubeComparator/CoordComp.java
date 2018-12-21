package com.epam.shapes.repository.dao.sorting.comparator.cubeComparator;

import com.epam.shapes.repository.savingWrapper.wrapperInterface.CubeWrapper;
import com.epam.shapes.utility.figureUtility.ShapesUtility;

import java.util.Comparator;

public class CoordComp implements Comparator<CubeWrapper> {
    private int which;
    private ShapesUtility shapesUtility = ShapesUtility.getInstance();

    public CoordComp(int which) {
        this.which = which;
    }

    @Override
    public int compare(CubeWrapper o1, CubeWrapper o2) {
        Double coordinate1 = shapesUtility.toArray(o1.getCentrePoint())[which];
        Double coordinate2 = shapesUtility.toArray(o2.getCentrePoint())[which];
        return coordinate1.compareTo(coordinate2);
    }
}
