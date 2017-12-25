package ru.itis.task049;

/**
 * @author Aleksandov Andrey
 *         11-703
 *         for 049
 */
public class Vector2D {
    private double x;
    private double y;
    public Vector2D() {
        x = 0;
        y = 0;
    }

    public Vector2D(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Vector2D add(Vector2D vector){
        return new Vector2D(this.x + vector.getX(), this.y + vector.getY());
    }

    public void add2(Vector2D vector){
        this.x += vector.getX();
        this.y += vector.getY();
    }


    public Vector2D sub(Vector2D vector){
        return new Vector2D(this.x -vector.getX(), this.y - vector.getY());
    }

    public void sub2(Vector2D vector){
        this.x -= vector.getX();
        this.y -= vector.getY();
    }

    public Vector2D mult(double constant){
        return new Vector2D(this.x * constant, this.y * constant);
    }

    public void mult2(double constant){
        this.x *= constant;
        this.y *= constant;
    }

    public String toString(){
        return "[ " + this.x + "; " + this.y + " ]";
    }

    public double length(){
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }

    double scalarProduct(Vector2D vector){
        return this.x * vector.getX() + this.y * vector.getY();
    }

    public double cos(Vector2D vector){
        return scalarProduct(vector) /(length() * vector.length());
    }

    public boolean equals(Vector2D vector){
        return (this.x == vector.getX() && this.y == vector.getY());
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
