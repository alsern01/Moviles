package es.ucm.gdv.offthelinelogic;

import es.ucm.gdv.engine.Graphics;

class Enemy {
    public Enemy(float x, float y, float length, float angle, float speed, float time1, float time2, float offX, float offY) {
        _pos = new Point(x, y);
        _angle = angle;
        _length = length;
        _speed = speed;
        setOffset(offX, offY);
        _time1 = time1;
        _time2 = time2;
        // Calcula la distancia que debe desplazarse y la velocidad del movimiento en funcion del tiempo
        _totalDistance = (float) Math.sqrt((_offset1.x * _offset1.x) + (_offset1.y * _offset1.y));
        _movementSpeed = _totalDistance / _time1;
        _move = true;


        _angle = angle;

        float sin = (float) Math.sin(Math.toRadians(_angle));
        float cos = (float) Math.cos(Math.toRadians(_angle));

        float x1 = _pos.x - _length / 2;
        float x2 = _pos.x + _length / 2;
        if (angle != 0)
            _segment = new Segment(new Point((x1 - _pos.x) * cos + _pos.x, (x1 - _pos.x) * sin + _pos.y), new Point((x2 - _pos.x) * cos + _pos.x, (x2 - _pos.x) * sin + _pos.y));
        else {
            _segment = new Segment(new Point(x - length / 2, y), new Point(x + length / 2, y));
        }
    }

    public void update(double deltaTime) {
        _segment.p1.x -= _pos.x;
        _segment.p1.y -= _pos.y;
        _segment.p2.x -= _pos.x;
        _segment.p2.y -= _pos.y;

        if (_time1 != 0) {
            if (!_wait) {
                Point dir;
                if (_move)
                    dir = _offset1;
                else dir = _offset2;

                dir.normalize();

                _pos.x += dir.x * _movementSpeed * (float) deltaTime;
                _pos.y += dir.y * _movementSpeed * (float) deltaTime;

                _distance += _movementSpeed * (float) deltaTime;
                //Cuando recorre la distancia que ha de recorrer de un punto a otro, se dispone a esperar
                if (_distance >= _totalDistance) {
                    _distance = 0f;
                    _move = !_move;
                    _wait = true;
                }
            } else {
                _waitTime += (float) deltaTime;
                if (_waitTime >= _time2) {
                    _wait = false;
                    _waitTime = 0f;
                }
            }
        }

        _angle = _speed * (float) deltaTime;

        float sin = (float) Math.sin(Math.toRadians(_angle));
        float cos = (float) Math.cos(Math.toRadians(_angle));

        float x1 = _segment.p1.x * cos - _segment.p1.y * sin;
        float y1 = _segment.p1.x * sin + _segment.p1.y * cos;

        float x2 = _segment.p2.x * cos - _segment.p2.y * sin;
        float y2 = _segment.p2.x * sin + _segment.p2.y * cos;

        _segment.p1.x = x1 + _pos.x;
        _segment.p1.y = y1 + _pos.y;

        _segment.p2.x = x2 + _pos.x;
        _segment.p2.y = y2 + _pos.y;
    }

    public void render(Graphics g) {
        g.setColor(255, 255, 0, 0);
        g.save();
        g.drawLine(_segment.p1.x, _segment.p1.y, _segment.p2.x, _segment.p2.y);
        g.restore();
    }

    public float getX() {
        return _pos.x;
    }

    public float getY() {
        return _pos.y;
    }

    public float getLength() {
        return _length;
    }

    // Angulo de rotacion
    public void setAngle(float angle) {
        _angle = angle;

        float sin = (float) Math.sin(Math.toRadians(_angle));
        float cos = (float) Math.cos(Math.toRadians(_angle));

        float x1 = _pos.x - _length / 2;
        float x2 = _pos.x + _length / 2;
        if (angle != 0)
            _segment = new Segment(new Point((x1 - _pos.x) * cos + _pos.x, (x1 - _pos.x) * sin + _pos.y), new Point((x2 - _pos.x) * cos + _pos.x, (x2 - _pos.x) * sin + _pos.y));
    }

    // Velocidad del enemigo
    public void setSpeed(float speed) {
        _speed = speed;
    }

    // Offset por el que se mueve el enemigo
    public void setOffset(float x, float y) {
        _offset1 = new Point(x, y);
        _offset2 = new Point(-x, -y);
    }

    public Segment getSegment() {
        return _segment;
    }

    private float _angle, _length, _speed, _time1, _time2;
    private float _movementSpeed, _totalDistance, _distance;
    private boolean _move = true, _wait = false;
    private float _waitTime = 0f;
    private Point _pos;
    private Point _offset1, _offset2;
    private Segment _segment;
}
