package es.ucm.gdv.offthelinelogic;

import java.util.ArrayList;

import es.ucm.gdv.engine.Graphics;

/**
 * Clase que contiene un camino del nivel y su método render para pintarse
 */

class Path {
    public Path() {
        _verts = new ArrayList<>();
        _dirs = new ArrayList<>();
        _segments = new ArrayList<>();
    }

    public void render(Graphics g) {
        g.setColor(255, 255, 255, 255);
        for (int i = 0; i < getVerts().size(); i++) {
            if (i != getVerts().size() - 1)
                g.drawLine(getVerts().get(i).getX(), getVerts().get(i).getY(),
                        getVerts().get(i + 1).getX(), getVerts().get(i + 1).getY());
            else
                g.drawLine(getVerts().get(i).getX(), getVerts().get(i).getY(),
                        getVerts().get(0).getX(), getVerts().get(0).getY());
        }
    }

    public void pushVertice(float x, float y) {
        _verts.add(new Point(x, y));
    }

    public void pushDirection(int x, int y) {
        _dirs.add(new Direction(x, y));
    }

    public void buildSegments() {
        for (int i = 0; i < _verts.size(); i++) {
            if (i + 1 == _verts.size()) {
                _segments.add(new Segment(_verts.get(i).getX(), _verts.get(i).getY(), _verts.get(0).getX(), _verts.get(0).getY()));
            } else {
                _segments.add(new Segment(_verts.get(i).getX(), _verts.get(i).getY(), _verts.get(i + 1).getX(), _verts.get(i + 1).getY()));
            }
        }

        System.out.println(_segments.size());
    }

    public ArrayList<Point> getVerts() {
        return _verts;
    }

    public ArrayList<Direction> getDirs() {
        return _dirs;
    }

    public ArrayList<Segment> getSegments() {
        return _segments;
    }

    private ArrayList<Point> _verts;
    private ArrayList<Direction> _dirs;
    private ArrayList<Segment> _segments;
}

class Direction {
    Direction(int x, int y) {
        _x = x;
        _y = y;
    }

    public int getX() {
        return _x;
    }

    public int getY() {
        return _y;
    }

    public void setX(int x) {
        _x = x;
    }

    public void setY(int y) {
        _y = y;
    }

    private int _x, _y;
}
