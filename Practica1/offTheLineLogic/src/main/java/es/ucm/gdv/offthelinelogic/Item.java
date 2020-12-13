package es.ucm.gdv.offthelinelogic;

import es.ucm.gdv.engine.Graphics;

class Item extends Entity {
    public Item(float x, float y, float size, float radius, float speed, float angle) {
        super(x + radius * (float) Math.cos(Math.toRadians(angle)), y + radius * (float) Math.sin(Math.toRadians(angle)), size, speed);
        _radius = radius;
        _angle = angle;
    }

    public void render(Graphics g) {
        g.setColor(255, 255, 255, 0);
        super.render(g);
    }

    public void update(double deltaTime) {
        super.update(deltaTime);

        _angle += _speed * deltaTime;

        if (_radius != 0) {
            _pos.x = _radius * (float) Math.cos(Math.toRadians(_angle));
            _pos.y = _radius * (float) Math.sin(Math.toRadians(_angle));
        }

        if (_destroying) {
            _size += 50f * (float) deltaTime;
            _deadTime += deltaTime;
            if (_deadTime >= 1f) {
                _destroyed = true;
            }
        }
    }

    public boolean isDestroyed() {
        return _destroyed;
    }

    public void destroy() {
        _destroying = true;
    }

    private float _radius, _angle;

    private boolean _destroying = false, _destroyed = false;
    float _scale = 0f, _deadTime = 0f;
}
