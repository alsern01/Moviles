package es.ucm.gdv.app.android;

import android.os.Bundle;
import android.view.SurfaceView;

import androidx.appcompat.app.AppCompatActivity;

import es.ucm.gdv.engine.androidengine.Engine;
import es.ucm.gdv.offthelinelogic.BaseLogic;

/**
 * Prueba de concepto de renderizado activo en Android.
 * <p>
 * Esta clase implementa la actividad principal de la aplicación.
 * En condiciones normales (una aplicación más compleja) la
 * implementación se distribuiría en más clases y se haría más
 * versátil.
 * <p>
 * Para que funcione, en el módulo se debe incluir un directorio
 * de Assets y guardar en él el fichero "Bangers-Regular.ttf" con
 * la fuente de letra, que será cargada en ejecución para pintar un
 * rótulo en pantalla.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Método llamado por Android como parte del ciclo de vida de
     * la actividad. Se llama en el momento de lanzarla.
     *
     * @param savedInstanceState Información de estado de la actividad
     *                           previamente serializada por ella misma
     *                           para reconstruirse en el mismo estado
     *                           tras un reinicio. Será null la primera
     *                           vez.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Preparamos el contenido de la actividad.
        _renderView = new SurfaceView(this);

        _engine = new Engine(_renderView, getAssets());//creación del motor
        _logic = new BaseLogic();//creación de la lógica

        // Iniciar ambos modulos y si algo falla cerrar el programa
        if (!_engine.init(_logic)) return;
        if (!_logic.init(_engine)) return;

        setContentView(_renderView);//asignar el content view al surface view del motor

    } // onCreate

    //--------------------------------------------------------------------

    /**
     * Método llamado por Android como parte del ciclo de vida de la
     * actividad. Notifica que la actividad va a pasar a primer plano,
     * estando en la cima de la pila de actividades y completamente
     * visible.
     * <p>
     * Es llamado durante la puesta en marcha de la actividad (algo después
     * de onCreate()) y también después de un periodo de pausa (notificado
     * a través de onPause()).
     */
    @Override
    protected void onResume() {

        // Avisamos a la vista (que es la encargada del active render)
        // de lo que está pasando.
        super.onResume();
        _engine.resume();

    } // onResume

    //--------------------------------------------------------------------

    /**
     * Método llamado por Android como parte del ciclo de vida de la
     * actividad. Notifica que la actividad ha dejado de ser la de
     * primer plano. Es un indicador de que el usuario está, de alguna
     * forma, abandonando la actividad.
     */
    @Override
    protected void onPause() {

        // Avisamos a la vista (que es la encargada del active render)
        // de lo que está pasando.
        super.onPause();
        _engine.pause();

    } // onPause

    /**
     * Vista principal de la actividad que gestiona, además, el active
     * rendering.
     */
    protected SurfaceView _renderView;
    private Engine _engine;
    private BaseLogic _logic;

} // class MainActivity
