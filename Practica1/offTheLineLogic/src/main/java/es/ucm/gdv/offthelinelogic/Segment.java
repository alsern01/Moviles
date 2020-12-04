package es.ucm.gdv.offthelinelogic;

public class Segment {
    public Segment(float x1, float y1, float x2, float y2) {
        _x1 = x1;
        _y1 = y1;
        _x2 = x2;
        _y2 = y2;

        _xDis = _x2 - _x1;
        _yDis = _y2 - _y1;

        _sqrDistance = _xDis * _xDis + _yDis * _yDis;
    }

    public float getX1() {
        return _x1;
    }

    public float getY1() {
        return _y1;
    }

    public float getX2() {
        return _x2;
    }

    public float getY2() {
        return _y2;
    }

    public float getSqrDistance() {
        return _sqrDistance;
    }

    public float getXDistance() {
        return _xDis;
    }

    public float getYDistance() {
        return _yDis;
    }

    public float getNormalizedXDistance() {
        return _xDis / (float) Math.sqrt(_xDis * _xDis + _yDis * _yDis);
    }

    public float getNormalizedYDistance() {
        return _yDis / (float) Math.sqrt(_xDis * _xDis + _yDis * _yDis);
    }

    private float _x1, _y1;
    private float _x2, _y2;
    private float _xDis, _yDis;
    private float _sqrDistance;
}

