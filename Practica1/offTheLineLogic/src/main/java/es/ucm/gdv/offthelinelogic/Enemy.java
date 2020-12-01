package es.ucm.gdv.offthelinelogic;

import es.ucm.gdv.engine.Graphics;

class Enemy {
    public Enemy(float x, float y, float length, float angle, float speed, float time1, float time2, float offX, float offY) {
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

    public void update(double deltaTime) {
        _angle += _speed * deltaTime;
    }

    public void render(Graphics g) {
        g.setColor(255, 255, 0, 0);
        g.save();
        g.translate((int) _x, (int) _y);
        g.rotate(_angle);
        g.drawLine((int) -_length / 2, 0, (int) _length / 2, 0);
        g.restore();
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
