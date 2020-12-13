package es.ucm.gdv.offthelinelogic;

public class LevelSegment extends Segment {
    public LevelSegment(float x1, float y1, float x2, float y2) {
        super(x1, y1, x2, y2);
        _dir = new Point(0, 0);
    }

    public LevelSegment(Point p1, Point p2) {
        super(p1, p2);
        _dir = new Point(0, 0);
    }

    public void setDir(Point p) {
        _dir = p;
    }

    public Point getDir() {
        return _dir;
    }

    public LevelSegment getInvertedSegment() {
        LevelSegment inverted = new LevelSegment(p2, p1);
        inverted.setDir(_dir);
        return inverted;
    }

    private Point _dir;
}
