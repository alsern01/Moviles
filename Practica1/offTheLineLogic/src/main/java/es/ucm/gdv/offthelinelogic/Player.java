package es.ucm.gdv.offthelinelogic;

import java.util.ArrayList;

import es.ucm.gdv.engine.Graphics;

public class Player extends Entity {
    public Player(float x, float y, float w, float h, float speed, ArrayList<Path> paths) {
        super(x, y, w, h, speed);

        _currentSegment = paths.get(0).getSegments().get(_segIndex);
        _dir = new Direction(0, 0);

        _prevX = _logicX;
        _prevY = _logicY;

        changeDir();
    }

    public void render(Graphics g) {
        g.setColor(255, 0, 136, 255);
        super.render(g);
    }

    public void update(double deltaTime) {
        super.update(deltaTime);

        _logicX += _speed * _dir.getX() * deltaTime;
        _logicY += _speed * _dir.getY() * deltaTime;

        _sqrTraveled = (_logicX - _prevX) * (_logicX - _prevX) + (_logicY - _prevY) * (_logicY - _prevY);

        checkPath();
    }

    private void checkPath() {
        if (_sqrTraveled >= _currentSegment.getSqrDistance()) {
            //_currentSegment = nexSegment();
            changeDir();
        }
    }

    private void changeDir() {
        _dir.setX((int) _currentSegment.getNormalizedXDistance());
        _dir.setY((int) _currentSegment.getNormalizedYDistance());

        _prevX = _logicX;
        _prevY = _logicY;
    }

    //private Segment nexSegment() {
    //Segment seg;


    //return seg;
    //}

    // Direccion en la que se mueve el jugador
    private Direction _dir;
    // El segmento del camino en el que esta el jugador
    private int _segIndex = 0;
    private Segment _currentSegment;
    // Distancia recorrida por el jugador del segmento actual, al cuadrado
    private float _sqrTraveled = 0;
    // Pos
    private float _prevX, _prevY;
}
