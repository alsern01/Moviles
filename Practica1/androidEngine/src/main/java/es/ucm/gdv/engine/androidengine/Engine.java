package es.ucm.gdv.engine.androidengine;

import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.view.SurfaceView;

import java.io.InputStream;

import es.ucm.gdv.engine.Logic;

public class Engine implements es.ucm.gdv.engine.Engine, Runnable {

    public Engine(SurfaceView surfaceView, AssetManager assetManager) {
        _surfaceView = surfaceView;
        _assetManager = assetManager;
    }

    @Override
    public boolean init(Logic logic) {
        _graphics = new Graphics(_assetManager, _surfaceView);

        _input = new Input(_graphics);
        _surfaceView.setOnTouchListener(_input);

        return true;
    }

    @Override
    public Graphics getGraphics() {
        return _graphics;
    }

    @Override
    public Input getInput() {
        return _input;
    }

    @Override
    public InputStream openInputStream(String filename) {
        return _inputStream;
    }

    @Override
    public void run() {
        if (_renderThread != Thread.currentThread()) {
            // ¿¿Quién es el tuercebotas que está llamando al
            // run() directamente?? Programación defensiva
            // otra vez, con excepción, por merluzo.
            throw new RuntimeException("run() should not be called directly");
        }

        // Antes de saltar a la simulación, confirmamos que tenemos
        // un tamaño mayor que 0. Si la hebra se pone en marcha
        // muy rápido, la vista podría todavía no estar inicializada.
        while (_running && _surfaceView.getWidth() == 0)
            // Espera activa. Sería más elegante al menos dormir un poco.
            ;

        long lastFrameTime = System.nanoTime();

        long informePrevio = lastFrameTime; // Informes de FPS
        int frames = 0;

        // Bucle principal.
        while (_running) {

            long currentTime = System.nanoTime();
            long nanoElapsedTime = currentTime - lastFrameTime;
            lastFrameTime = currentTime;
            double elapsedTime = (double) nanoElapsedTime / 1.0E9;

            // Update de la logica con el deltaTime calculado
            _logic.update(elapsedTime);

            // Informe de FPS
            /*if (currentTime - informePrevio > 1000000000l) {
                long fps = frames * 1000000000l / (currentTime - informePrevio);
                System.out.println("" + fps + " fps");
                frames = 0;
                informePrevio = currentTime;
            }
            ++frames;
            */

            // Pintamos el frame
            while (!_surfaceView.getHolder().getSurface().isValid())
                ;
            Canvas canvas = _surfaceView.getHolder().lockCanvas();
            _graphics.setCanvas(canvas);
            // Render de la logica
            _logic.render();

            _surfaceView.getHolder().unlockCanvasAndPost(canvas);
                /*
                // Posibilidad: cedemos algo de tiempo. es una medida conflictiva...
                try {
                    Thread.sleep(1);
                }
                catch(Exception e) {}
    			*/

        } // while
    } // run

    /**
     * Método llamado para solicitar que se continue con el
     * active rendering. El "juego" se vuelve a poner en marcha
     * (o se pone en marcha por primera vez).
     */
    public void resume() {

        if (!_running) {
            // Solo hacemos algo si no nos estábamos ejecutando ya
            // (programación defensiva, nunca se sabe quién va a
            // usarnos...)
            _running = true;
            // Lanzamos la ejecución de nuestro método run()
            // en una hebra nueva.
            _renderThread = new Thread(this);
            _renderThread.start();
        } // if (!_running)

    } // resume

    /**
     * Método llamado cuando el active rendering debe ser detenido.
     * Puede tardar un pequeño instante en volver, porque espera a que
     * se termine de generar el frame en curso.
     * <p>
     * Se hace así intencionadamente, para bloquear la hebra de UI
     * temporalmente y evitar potenciales situaciones de carrera (como
     * por ejemplo que Android llame a resume() antes de que el último
     * frame haya terminado de generarse).
     */
    public void pause() {

        if (_running) {
            _running = false;
            while (true) {
                try {
                    _renderThread.join();
                    _renderThread = null;
                    break;
                } catch (InterruptedException ie) {
                    // Esto no debería ocurrir nunca...
                }
            } // while(true)
        } // if (_running)

    } // pause

    @Override
    public void setLogic(Logic logic) {
        _logic = logic;
    }

    /**
     * Superficie de dibujado
     */
    private SurfaceView _surfaceView;
    /**
     * Gestor de graficos
     */
    private Graphics _graphics;
    /**
     * Gestor de input
     */
    private Input _input;
    /**
     * Gesto de ficheros
     */
    private InputStream _inputStream;
    /**
     * Proporciona los recursos de la aplicacion
     */
    private AssetManager _assetManager;
    /**
     * Hilo de ejecucion
     */
    private Thread _renderThread;
    /**
     * Estado del juego
     */
    private Logic _logic;
    /**
     * Booleano que indica si se esta ejecutando el hilo
     */
    volatile boolean _running = false;
}
