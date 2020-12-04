package es.ucm.gdv.offthelinelogic;

public class Point {
    public Point(float x, float y) {
        _x = x;
        _y = y;
    }

    public float getX() {
        return _x;
    }

    public float getY() {
        return _y;
    }

    public void setX(float x) {
        _x = x;
    }

    public void setY(float y) {
        _y = y;
    }

    public void normalize() {
        float magnitude = (float) Math.sqrt(_x * _x + _y * _y);
        _x = _x / magnitude;
        _y = _y / magnitude;
    }

    private float _x, _y;
}
