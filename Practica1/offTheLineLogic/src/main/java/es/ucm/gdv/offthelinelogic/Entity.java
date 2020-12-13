package es.ucm.gdv.offthelinelogic;

import es.ucm.gdv.engine.Graphics;

public class Entity {
    public Entity(float x, float y, float size, float speed) {
        _pos = new Point(x, y);
        _size = (int) size;
        _speed = speed;
        _rot = 0;
    }

    public void update(double deltaTime) {
        _rot += 180.0f * (float) deltaTime;
    }

    public void render(Graphics g) {
        g.save();

        g.translate((int) _pos.x, (int) _pos.y);
        g.scale(1, 1);
        g.rotate(-_rot);

        g.drawLine(-_size, -_size, _size, -_size);
        g.drawLine(_size, -_size, _size, _size);
        g.drawLine(_size, _size, -_size, _size);
        g.drawLine(-_size, _size, -_size, -_size);

        g.restore();
    }

    public float getX() {
        return _pos.x;
    }

    public float getY() {
        return _pos.y;
    }

    protected Point _pos; // Posicion
    protected int _size; // Tamaño de medio segmento
    protected float _rot, _speed; // Angulo y velocidad
}
