package es.ucm.gdv.engine;

/**
 * Clase para calcular el reescalado lógico y físico de la ventana
 * <p>
 * Las coordenadas logicas contienen el tamaño y posicion real de los objetos
 * <p>
 * Las coordenadas fisicas contienen un tamaño y posicion reescalados en funcion
 * del ancho y alto de la ventana
 * <p>
 * Todas las medidas se calculan en pixeles
 */

public abstract class AbstractGraphics implements Graphics {

    @Override
    public void calculateCanvasSize() {
        int winWidth = getWidth();
        int winHeight = getHeight();

        float wScale = (float) winWidth / (float) _logicWidth;
        float hScale = (float) winHeight / (float) _logicHeight;

        if (wScale < hScale) {
            _scale = wScale;
            _canvasWidth = winWidth;
            _canvasHeight = (winWidth * _logicHeight) / _logicWidth;

            _canvasY = (winHeight - _canvasHeight) / 2;
            _canvasX = 0;
        } else {
            _scale = hScale;
            _canvasHeight = winHeight;
            _canvasWidth = (winHeight * _logicWidth) / _logicHeight;

            _canvasX = (winWidth - _canvasWidth) / 2;
            _canvasY = 0;
        }
    }

    @Override
    public int getCanvasWidth() {
        return _canvasWidth;
    }

    @Override
    public int getCanvasHeight() {
        return _canvasHeight;
    }

    @Override
    public int getCanvasX() {
        return _canvasX;
    }

    @Override
    public int getCanvasY() {
        return _canvasY;
    }

    @Override
    public int getLogicWidth() {
        return _logicWidth;
    }

    @Override
    public int getLogicHeight() {
        return _logicHeight;
    }

    @Override
    public float getCanvasScale() {
        return _scale;
    }

    /**
     * Escala de la pantalla
     */
    private float _scale;

    /**
     * Ancho y alto del canvas a partir del tamaño de la ventana
     */
    private int _canvasWidth, _canvasHeight;

    /**
     * Posicion X e Y del canvas a partir del tamaño de la ventana
     */
    protected int _canvasX, _canvasY;

    /**
     * Ancho y alto reales del area de juego
     */
    private int _logicWidth = 640, _logicHeight = 480;
}
