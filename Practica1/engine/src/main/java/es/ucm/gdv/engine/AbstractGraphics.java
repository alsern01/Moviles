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

        float logicRatio = (float) _logicWidth / (float) _logicHeight;
        float realRatio = (float) winWidth / (float) winHeight;

        if (logicRatio <= realRatio) {
            _canvasHeight = winHeight;
            _canvasWidth = (int) ((float) _canvasHeight * logicRatio);
            _canvasX = (winWidth - _canvasWidth) / 2;
            _canvasY = 0;
        } else {
            _canvasWidth = winWidth;
            _canvasHeight = (int) ((float) _canvasWidth / logicRatio);
            _canvasY = (winHeight - _canvasHeight) / 2;
            _canvasX = 0;
        }
    }

    /*/**
     * Convierte coordenandas logicas (sin reescalado)
     * en coordenadas fisicas (reescaladas)
     *
     * @param logicX coordenada logica X
     * @param logicY coordenada logica Y
     */
    /*public void logicToPhysic(float logicX, float logicY) {
        // Primero obtenemos el tamaño de la ventana
        actualWindowSize();
        // Luego se calcula el tamaño del canvas respecto a la ventana
        calculateCanvasSize();

        // Por último se transforman las coordenadas
        float physicX = (logicX * (float) _canvasWidth) / (float) _logicWidth;
        float physicY = (logicY * (float) _canvasHeight) / (float) _logicHeight;

        // Se asigna el resultado y se añade el offset en funcion de la posicion del canvas
        logicX = physicX + _canvasX;
        logicY = physicY + _canvasY;
    }*/
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

    /**
     * Ancho y alto del canvas a partir del tamaño de la ventana
     */
    private int _canvasWidth, _canvasHeight;

    /**
     * Posicion X e Y del canvas a partir del tamaño de la ventana
     */
    private int _canvasX, _canvasY;

    /**
     * Ancho y alto reales del area de juego
     */
    private int _logicWidth = 640, _logicHeight = 480;
}
