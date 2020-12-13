package es.ucm.gdv.offthelinelogic;

public class Segment {
    public Segment(float x1, float y1, float x2, float y2) {
        p1 = new Point(x1, y1);
        p2 = new Point(x2, y2);

        _xDis = p2.getX() - p1.getX();
        _yDis = p2.getY() - p1.getY();

        _sqrDistance = _xDis * _xDis + _yDis * _yDis;
    }

    public Segment(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
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

    public Segment getInvertedSegment() {
        return new Segment(p2, p1);
    }

    protected Point p1;
    protected Point p2;
    protected float _xDis, _yDis;
    protected float _sqrDistance;
}

