package ru.itis;

/**
 * Created by Admon on 08.02.2018.
 */
public class Circle {
    private int radius;
    private int points;

    public Circle(int r, int p) {
        radius = r;
        points = p;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
