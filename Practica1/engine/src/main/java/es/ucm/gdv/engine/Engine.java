package es.ucm.gdv.engine;

/**
 * Interfaz que gestiona el bucle principal de la aplicación y los
 * módulos que integran el motor
 */
public interface Engine {
    /**
     * Inicializa los atributos necesarios para correr la aplicación
     *
     * @param logic logica del bucle principal
     * @return booleano de control por si ha habido errores en la inicialización
     */
    boolean init(Logic logic);

    /**
     * Bucle principal de la aplicacion
     */
    void run();

    /**
     * Añade un motor logico
     */
    void setLogic(Logic logic);

    /**
     * Devuelve el gestor de gráficos
     */
    Graphics getGraphics();

    /**
     * Devuelve el gestor de input
     */
    Input getInput();

    /**
     * Abre un archivo con el nombre indicado
     */
    InputStream openInputStream(String filename);
}