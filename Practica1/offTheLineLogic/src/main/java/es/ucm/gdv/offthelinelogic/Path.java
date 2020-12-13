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

    public void pushDirection(float x, float y) {
        _dirs.add(new Point(x, y));
    }

    public void buildSegments() {
        if (_segments.size() <= 0) {
            for (int i = 0; i < _verts.size(); i++) {
                if (i != _verts.size() - 1) {
                    _segments.add(new LevelSegment(_verts.get(i).getX(), _verts.get(i).getY(), _verts.get(i + 1).getX(), _verts.get(i + 1).getY()));
                } else {
                    _segments.add(new LevelSegment(_verts.get(i).getX(), _verts.get(i).getY(), _verts.get(0).getX(), _verts.get(0).getY()));
                }
                if (_dirs.size() > 0) {
                    _segments.get(i).setDir(_dirs.get(i));
                }
            }
        }
    }

    public ArrayList<Point> getVerts() {
        return _verts;
    }

    public ArrayList<Point> getDirs() {
        return _dirs;
    }

    public ArrayList<LevelSegment> getSegments() {
        return _segments;
    }

    private ArrayList<Point> _verts;
    private ArrayList<Point> _dirs;
    private ArrayList<LevelSegment> _segments;
}