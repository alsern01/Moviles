package es.ucm.gdv.offthelinelogic;

import es.ucm.gdv.engine.Graphics;

public class Entity {
    public Entity(float x, float y, float w, float h, float speed) {
        _x = x;
        _y = y;
        _w = w;
        _h = h;
        _speed = speed;
        _rot = 0;
        _logicX = x + w / 2;
        _logicY = y + h / 2;
    }

    public void update(double deltaTime) {
        _rot += 180.0f * (float) deltaTime;
        _x = _logicX - _w / 2;
        _y = _logicY - _h / 2;
    }

    public void render(Graphics g) {
        g.save();

        g.translate((int) (_x + _w / 2), (int) (_y + _h / 2));
        g.rotate(_rot);
        g.translate((int) (-_x - _w / 2), (int) (-_y - _h / 2));
        g.drawLine((int) _x, (int) _y, (int) (_x + _w), (int) _y);
        g.drawLine((int) (_x + _w), (int) _y, (int) (_x + _w), (int) (_y + _h));
        g.drawLine((int) (_x + _w), (int) (_y + _h), (int) _x, (int) (_y + _h));
        g.drawLine((int) _x, (int) (_y + _h), (int) _x, (int) _y);

        g.restore();
    }

    public float getX() {
        return _x;
    }

    public float getY() {
        return _y;
    }

    public float getSpeed() {
        return _speed;
    }

    protected float _x, _y, _w, _h, _rot, _speed;
    protected float _logicX, _logicY;
}
