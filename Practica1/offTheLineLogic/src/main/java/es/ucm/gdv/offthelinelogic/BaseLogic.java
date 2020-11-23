package es.ucm.gdv.offthelinelogic;

import es.ucm.gdv.engine.Engine;
import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.Input;

public class BaseLogic implements es.ucm.gdv.engine.Logic {
    @Override
    public boolean init(Engine app) {
        _app = app;
        _graphics = _app.getGraphics();
        _input = _app.getInput();
        return true;
    }

    @Override
    public void update(double deltaTime) {
        int maxX = _graphics.getWidth() - 300; // 300 : longitud estimada en píxeles del rótulo

        _x += _incX * deltaTime;
        while (_x < 0 || _x > maxX) {
            // Vamos a pintar fuera de la pantalla. Rectificamos.
            if (_x < 0) {
                // Nos salimos por la izquierda. Rebotamos.
                _x = -_x;
                _incX *= -1;
            } else if (_x > maxX) {
                // Nos salimos por la derecha. Rebotamos
                _x = 2 * maxX - _x;
                _incX *= -1;
            }
        } // while
    }

    @Override
    public void render() {
        // limpia la pantalla pintando de negro
        _graphics.clear(255, 0, 0, 0);

        // escribe un texto
        _graphics.setColor(255, 255, 255, 255);
        _graphics.drawText("RENDERIZADO ACTIVO", (int) _x, 300);
    }

    private Engine _app;
    // Gestores para ahorrar llamadas a la aplicacion
    private Graphics _graphics;
    private Input _input;

    /**
     * Posición x actual del texto (lado izquierdo). Es importante
     * que sea un número real, para acumular cambios por debajo del píxel si
     * la velocidad de actualización es mayor que la del desplazamiento.
     */
    protected double _x = 0;

    /**
     * Velocidad de desplazamiento en píxeles por segundo.
     */
    protected int _incX = 50;
}
