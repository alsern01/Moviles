package es.ucm.gdv.offthelinelogic;

public class Point {
    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void normalize() {
        float magnitude = (float) Math.sqrt(x * x + y * y);
        x = x / magnitude;
        y = y / magnitude;
    }

    public float x, y;
}
