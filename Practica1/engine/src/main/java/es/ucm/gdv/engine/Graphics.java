package es.ucm.gdv.engine;

public interface Graphics {
    /**
     * Crea una nueva fuente
     *
     * @param filename nombre del archivo .ttf
     * @param size     tamaño de la fuente
     * @param isBold   booleano para saber si es negrita
     */
    Font newFont(String filename, int size, boolean isBold);

    /**
     * Metodo de control para saber si se ha inicializado el modulo correctamente
     */
    boolean init();

    /**
     * Pinta toda la pantalla con el color indicado
     */
    void clear(int a, int r, int g, int b);

    /**
     * Metodo de control de la transformacion sobre el canvas
     *
     * @param x posicion X sobre el canvas
     * @param y posicion Y sobre el canvas
     **/
    void translate(int x, int y);

    /**
     * Metodo de control de la escala sobre el canvas
     *
     * @param x escala en eje X
     * @param y escala en eje Y
     **/
    void scale(float x, float y);

    /**
     * Metodo de control de la rotacion sobre el canvas
     *
     * @param angle angulo de rotacion
     **/
    void rotate(float angle);

    /**
     * Metodo para guardar el estado de los graficos
     */
    void save();

    /**
     * Metodo para restaurar el estado en el que se encontraban los graficos
     */
    void restore();

    /**
     * Color con el que se va a pintar en la pantalla
     *
     * Rango 0-255
     * @param a componente alfa del color
     * @param r parametro red del color
     * @param g parametro blue del color
     * @param b parametro green del color
     */
    void setColor(int a, int r, int g, int b);

    /**
     * Dibuja una linea
     *
     * @param x1 posicion X del primer extremo del segmento
     * @param y1 posicion Y del primer extremo del segmento
     * @param x2 posicion Y del segundo extremo del segmento
     * @param y2 posicion Y del segundo extremo del segmento
     */
    void drawLine(int x1, int y1, int x2, int y2);

    /**
     * Dibuja un rectangulo relleno
     *
     * @param x1 posicion X del primer extremo del rectangulo
     * @param y1 posicion Y del primer extremo del rectangulo
     * @param x2 posicion Y del segundo extremo del rectangulo
     * @param y2 posicion Y del segundo extremo del rectangulo
     */
    void fillRect(int x1, int y1, int x2, int y2);

    /**
     * Dibuja un texto en pantalla
     *
     * @param text Contenido del texto
     * @param x    posicion X del texto
     * @param y    posicion Y del texto
     */
    void drawText(String text, int x, int y);

    /**
     * Devuelve el ancho de la ventana
     */
    int getWidth();

    /**
     * Devuelve el alto de la ventana
     */
    int getHeight();

    /**
     * Devuelve el ancho del canvas del juego
     */
    //int getCanvasWidth();

    /**
     * Devuelve el alto del canvas del juego
     */
    //int getCanvasHeight();
}
