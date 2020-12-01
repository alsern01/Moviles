package es.ucm.gdv.offthelinelogic;

import es.ucm.gdv.engine.Graphics;

class Item extends Entity {
    public Item(float x, float y, float w, float h, float radius, float speed, float angle) {
        super(x + radius * (float) Math.cos(Math.toRadians(angle)), y + radius * (float) Math.sin(Math.toRadians(angle)), w, h, speed);
        _radius = radius;
        _angle = angle;
    }

    public void render(Graphics g) {
        g.setColor(255, 255, 255, 0);
        super.render(g);
        g.save();
        g.rotate(_angle);
        g.restore();


    }

    public void update(double deltaTime) {
        super.update(deltaTime);
        _angle += _angle * _speed * deltaTime;

        if (_prueba) {
            _scale += 0.05f * (float) deltaTime;

            _w += _scale;
            _h += _scale;

            if (_scale > 0.03f) {
                _prueba = false;
            }
        }
    }

    public float getRadius() {
        return _radius;
    }

    public float getAngle() {
        return _rot;
    }

    private float _radius, _angle;

    private boolean _prueba = false;
    float _scale = 0f;
}
