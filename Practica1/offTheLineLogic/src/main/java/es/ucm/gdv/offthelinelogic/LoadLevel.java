package es.ucm.gdv.offthelinelogic;

/**
 * Clase que carga un nivel a partir del parseo del json y
 */

import java.util.ArrayList;

import es.ucm.gdv.engine.Engine;

public class LoadLevel {
    public LoadLevel(Engine e, int nLevel) {
        _jsonReader = new MyJsonReader(e);

        _jsonReader.parseLevel(nLevel, this);

        _paths = new ArrayList<>();
        _enemies = new ArrayList<>();
        _items = new ArrayList<>();
    }

    public ArrayList<Path> getPaths() {
        return _paths;
    }

    public ArrayList<Enemy> getEnemies() {
        return _enemies;
    }

    public ArrayList<Item> getItems() {
        return _items;
    }

    public void setTime(float time) {
        _time = time;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getName() {
        return _name;
    }

    private MyJsonReader _jsonReader;
    private ArrayList<Path> _paths;
    private ArrayList<Enemy> _enemies;
    private ArrayList<Item> _items;

    private float _time;
    private String _name;
}

class Path {
    public Path() {
        _verts = new ArrayList<>();
        _dirs = new ArrayList<>();
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

class Item {
    public Item(float x, float y, float radius, float speed, float angle) {
        _x = x;
        _y = y;
        _radius = radius;
        _speed = speed;
        _angle = angle;
    }

    public float getX() {
        return _x;
    }

    public float getY() {
        return _y;
    }

    public float getRadius() {
        return _radius;
    }

    public float getSpeed() {
        return _speed;
    }

    public float getAngle() {
        return _angle;
    }

    private float _x, _y, _radius, _speed, _angle;
}

class Enemy {
    public Enemy(float x, float y, float angle, float length, float speed, float time1, float time2, float offX, float offY) {
        _x = x;
        _y = y;
        _angle = angle;
        _length = length;
        _speed = speed;
        _time1 = time1;
        _time2 = time2;
        _offX = offX;
        _offY = offY;
    }

    public float getX() {
        return _x;
    }

    public float getY() {
        return _y;
    }

    public float getLength() {
        return _length;
    }

    public float getAngle() {
        return _angle;
    }

    public float getSpeed() {
        return _speed;
    }

    public float getTime1() {
        return _time1;
    }

    public float getTime2() {
        return _time2;
    }

    public float getOffsetX() {
        return _offX;
    }

    public float getOffsetY() {
        return _offY;
    }

    private float _x, _y, _angle, _length, _speed, _time1, _time2, _offX, _offY;
}
