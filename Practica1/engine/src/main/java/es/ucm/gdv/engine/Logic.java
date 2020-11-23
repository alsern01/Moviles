package es.ucm.gdv.engine;

/**
 * Interfaz que encapsula la funcionalidad de los estados lógicos de la aplicación,
 * se inicia, se actualiza y se pinta
 */
public interface Logic {
    /**
     * Inicializa lo necesario para el funcionamiento del estado
     *
     * @param app necesita acceso a la aplicación
     * @return booleano de control por si ha habido fallos durante la inicialización
     */
    public boolean init(Engine app);

    /**
     * Función que actualiza to-do lo relativo a la lógica de la aplicación
     *
     * @param deltaTime tiempo en segundos desde el último frame
     */
    public void update(double deltaTime);

    /**
     * Función que actualiza los elementos gráficos de la aplicación
     */
    public void render();
}
