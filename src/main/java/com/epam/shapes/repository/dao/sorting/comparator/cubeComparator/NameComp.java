package com.epam.shapes.repository.dao.sorting.comparator.cubeComparator;

import com.epam.shapes.repository.savingWrapper.wrapperInterface.CubeWrapper;

import java.util.Comparator;

public class NameComp implements Comparator<CubeWrapper> {
    @Override
    public int compare(CubeWrapper o1, CubeWrapper o2) {
        String name1 = o1.getName();
        String name2 = o2.getName();
        return name1.compareTo(name2);
    }
}
