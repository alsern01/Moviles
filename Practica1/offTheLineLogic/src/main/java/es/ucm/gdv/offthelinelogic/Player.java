package es.ucm.gdv.offthelinelogic;

import java.util.ArrayList;

import es.ucm.gdv.engine.Graphics;

public class Player extends Entity {
    public Player(float x, float y, float w, float h, float speed, ArrayList<Path> paths) {
        super(x, y, w, h, speed);
    }

    public void render(Graphics g) {
        g.setColor(255, 0, 136, 255);
        super.render(g);
    }

    public void update(double deltaTime) {
        super.update(deltaTime);

        _logicX += _speed * _dirX * deltaTime;
        _logicY += _speed * _dirY * deltaTime;

    }

    private int _dirY = 0, _dirX = 0;
}
