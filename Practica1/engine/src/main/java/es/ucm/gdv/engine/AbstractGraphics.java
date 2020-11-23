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

    /**
     * Calcula el ancho y el alto actual de la ventana
     */
    private void actualWindowSize() {
        _winWidth = getWidth();
        _winHeight = getHeight();
    }

    /**
     * Calcula el ancho y alto del canvas a partir del tamaño actual de la ventana,
     * busca maximizar ese área
     */
    private void calculateCanvas() {
        // Comprobamos el aspect ratio actual de la ventana
        double actualRatio = (double) _winWidth / (double) _winHeight;
        // Si es mayor o igual que el ratio logico
        if (actualRatio > _ratio) {
            // quiere decir que o el ancho aumenta o la altura disminuye, en cualquier caso
            // solo hace falta reescalar el alto
            _canvasHeight = (int) ((double) _winWidth * _logicHeight / _logicWidth);
            _canvasWidth = _logicWidth;
        } else if (actualRatio == _ratio) {
            _canvasWidth = _logicWidth;
            _canvasHeight = _logicHeight;
        } else {
            //justo al contrario, solo hay que reescalar el ancho
            _canvasWidth = (int) ((double) _winHeight * _logicWidth / _logicHeight);
            _canvasHeight = _logicHeight;
        }
    }

    /**
     * Convierte coordenandas logicas (sin reescalado)
     * en coordenadas fisicas (reescaladas)
     *
     * @param logicX coordenada logica X
     * @param logicY coordenada logica Y
     */
    public void logicToPhysic(float logicX, float logicY) {
        // Primero obtenemos el tamaño de la ventana
        actualWindowSize();
        // Luego se calcula el tamaño del canvas respecto a la ventana
        calculateCanvas();

        // Se necesita un offset para que las coordenadas qeuden centradas
        float offsetX = ((_winWidth - _canvasWidth) / 2.0f);
        float offsetY = ((_winHeight - _canvasHeight) / 2.0f);

        // Por último se transforman las coordenadas
        float physicX = (logicX * (float) _canvasWidth) / (float) _logicWidth;
        float physicY = (logicY * (float) _canvasHeight) / (float) _logicHeight;

        // Se asigna el resultado y se añade el offset
        logicX = physicX + offsetX;
        logicY = physicY + offsetY;
    }

    /**
     * Convierte coordenadas fisicas (reescaladas)
     * en coordenandas logicas (sin reescalado)
     *
     * @param physicX coordenada fisica X
     * @param physicY coordenada fisica Y
     */
    public void physicToLogic(float physicX, float physicY) {

        float logicX = (physicX * (float) _logicWidth) / (float) _canvasWidth;
        float logicY = (physicY * (float) _logicHeight) / (float) _canvasHeight;

        physicX = logicX;
        physicY = logicY;
    }

    /**
     * Ancho y alto del canvas a partir del tamaño de la ventana
     */
    private int _canvasWidth, _canvasHeight;
    /**
     * Ancho y alto de la ventana de la aplicacion
     */
    private int _winWidth, _winHeight;
    /**
     * Ancho y alto reales del area de juego
     */
    private int _logicWidth = 640, _logicHeight = 480;
    /**
     * Ratio para reescalar el area de juego
     */
    private double _ratio = (double) _logicWidth / (double) _logicHeight;
}
