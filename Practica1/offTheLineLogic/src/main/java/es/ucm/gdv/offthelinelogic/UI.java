package es.ucm.gdv.offthelinelogic;

import es.ucm.gdv.engine.Graphics;

public class UI {
    public UI(float x, float y, int size, int lifes) {
        _lifes = lifes;
        p = new Point(x, y);
        _size = size;
    }

    public void render(Graphics g) {
        g.setColor(255, 0, 136, 255);
        for (int i = 0; i < _lifes; i++) {
            g.drawLine(p.x + (i * 2 * _size) - _size / 2, p.y - _size / 2, p.x + (i * 2 * _size) + _size / 2, p.y - _size / 2);
            g.drawLine(p.x + (i * 2 * _size) + _size / 2, p.y - _size / 2, p.x + (i * 2 * _size) + _size / 2, p.y + _size / 2);
            g.drawLine(p.x + (i * 2 * _size) + _size / 2, p.y + _size / 2, p.x + (i * 2 * _size) - _size / 2, p.y + _size / 2);
            g.drawLine(p.x + (i * 2 * _size) - _size / 2, p.y + _size / 2, p.x + (i * 2 * _size) - _size / 2, p.y - _size / 2);
        }
    }

    public float getX() {
        return p.x;
    }

    public float getY() {
        return p.y;
    }

    public void set_x(float x) {
        p.x = x;
    }

    public void set_y(float y) {
        p.y = y;
    }

    public void setLifes(int l) {
        _lifes = l;
    }

    private int _lifes;
    private Point p;
    private float _size;
}
