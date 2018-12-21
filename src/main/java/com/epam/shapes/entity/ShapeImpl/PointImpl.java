package com.epam.shapes.entity.ShapeImpl;

import com.epam.shapes.entity.ShapeInterface.Point;

import java.util.Objects;

public class PointImpl implements Point {
    private Double x;
    private Double y;
    private Double z;

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getZ() {
        return z;
    }

    public void setZ(Double z) {
        this.z = z;
    }

    public PointImpl(Double x, Double y, Double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public PointImpl() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointImpl point = (PointImpl) o;
        return Objects.equals(x, point.x) &&
                Objects.equals(y, point.y) &&
                Objects.equals(z, point.z);
    }

    @Override
    public int hashCode() {

        return Objects.hash(x, y, z);
    }


    @Override
    public String toString() {
        return "X: " + x + " Y: " + y + " Z: " + z;
    }
}
