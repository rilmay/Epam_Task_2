package com.epam.shapes.entity.ShapeInterface;

public interface Cube {

    Point getCentrePoint();

    Double getWidth();

    Double getHeight();

    Double getLength();

    void setCentrePoint(Point centrePoint);

    void setWidth(Double width);

    void setHeight(Double height);

    void setLength(Double length);
}
