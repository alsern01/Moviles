package es.ucm.gdv.offthelinelogic;

/**
 * Clase que carga un nivel a partir del parseo del json
 */

import java.util.ArrayList;

import es.ucm.gdv.engine.Engine;

public class LoadLevel {
    public LoadLevel(Engine e, int nLevel) {
        _jsonReader = new MyJsonReader(e);

        _paths = new ArrayList<>();
        _enemies = new ArrayList<>();
        _items = new ArrayList<>();

        _jsonReader.parseLevel(nLevel, this);

        buildPaths();
    }

    public void buildPaths() {
        int i = 0;
        for (Path p : _paths) {
            p.buildSegments();
            System.out.println(p.getSegments().size());
            i++;
        }
    }

    public void addPath() {
        _paths.add(new Path());
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

    public MyJsonReader getParser() {
        return _jsonReader;
    }

    private MyJsonReader _jsonReader;
    private ArrayList<Path> _paths;
    private ArrayList<Enemy> _enemies;
    private ArrayList<Item> _items;

    private float _time;
    private String _name;
}


