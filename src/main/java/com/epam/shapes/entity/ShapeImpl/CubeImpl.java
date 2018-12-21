package com.epam.shapes.entity.ShapeImpl;

import com.epam.shapes.entity.ShapeInterface.Cube;
import com.epam.shapes.entity.ShapeInterface.Point;

import java.util.Objects;

public class CubeImpl implements Cube {
    private Point centrePoint;
    private Double width;
    private Double height;
    private Double length;

    public Point getCentrePoint() {
        return centrePoint;
    }

    public Double getWidth() {
        return width;
    }

    public Double getHeight() {
        return height;
    }

    public Double getLength() {
        return length;
    }

    public void setCentrePoint(Point centrePoint) {
        this.centrePoint = centrePoint;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public CubeImpl() {
    }

    public CubeImpl(Double x, Double y, Double z, Double width, Double height, Double length) {
        this.centrePoint = new PointImpl(x, y, z);
        this.width = width;
        this.height = height;
        this.length = length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CubeImpl cube = (CubeImpl) o;
        return Objects.equals(centrePoint, cube.centrePoint) &&
                Objects.equals(width, cube.width) &&
                Objects.equals(height, cube.height) &&
                Objects.equals(length, cube.length);
    }

    @Override
    public int hashCode() {
        return Objects.hash(centrePoint, width, height, length);
    }

    @Override
    public String toString() {
        return centrePoint + " W: " + width + " H: " + height + " L: " + length;
    }
}
