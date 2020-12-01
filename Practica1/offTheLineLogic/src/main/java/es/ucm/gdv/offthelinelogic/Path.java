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
        _verts.add(new Vertice(x, y));
    }

    public void pushDirection(int x, int y) {
        _dirs.add(new Direction(x, y));
    }

    public ArrayList<Vertice> getVerts() {
        return _verts;
    }

    public ArrayList<Direction> getDirs() {
        return _dirs;
    }

    private ArrayList<Vertice> _verts;
    private ArrayList<Direction> _dirs;
}

class Vertice {
    public Vertice(float x, float y) {
        _x = x;
        _y = y;
    }

    public float getX() {
        return _x;
    }

    public float getY() {
        return _y;
    }


    private float _x, _y;
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

    private int _x, _y;
}
