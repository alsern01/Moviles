package es.ucm.gdv.engine.desktopengine;

/*estos hacen falta de momento para que no explote*/

import java.awt.image.BufferStrategy;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.swing.JFrame;

import es.ucm.gdv.engine.Input;
import es.ucm.gdv.engine.Logic;

public class Engine implements es.ucm.gdv.engine.Engine {

    /**
     * Constructora
     *
     * @param width  ancho de la ventana
     * @param height alto de la ventana
     */
    public Engine(int width, int height) {
        _width = width;
        _height = height;
    }

    @Override
    public boolean init(Logic logic) {

        _logic = logic;

        try {
            _window = new JFrame();
            _window.setSize(_width, _height);
            _window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            _graphics = new Graphics(_window);

            //_input = new Input();
            //_inputStream = new es.ucm.gdv.engine.desktopengine.InputStream();
            //_window.addMouseListener(_input);

            return true;

        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }

    /**
     * Se encarga de crear un buffer de pintado para la aplicacion
     */
    private void createBufferStrategy() {
        // Vamos a usar renderizado activo. No queremos que Swing llame al
        // método repaint() porque el repintado es continuo en cualquier caso.
        _window.setIgnoreRepaint(true);

        // Hacemos visible la ventana.
        _window.setVisible(true);

        // Intentamos crear el buffer strategy con 2 buffers.
        int intentos = 100;
        while (intentos-- > 0) {
            try {
                _window.createBufferStrategy(2);
                break;
            } catch (Exception e) {
            }
        } // while pidiendo la creación de la buffeStrategy
        if (intentos == 0) {
            System.err.println("No pude crear la BufferStrategy");
            return;
        } else {
            // En "modo debug" podríamos querer escribir esto.
            //System.out.println("BufferStrategy tras " + (100 - intentos) + " intentos.");
        }

        // Obtenemos el Buffer Strategy que se supone que acaba de crearse.
        _strategy = _window.getBufferStrategy();
    }

    @Override
    public void run() {
        // primero se crea un buffer strategy
        createBufferStrategy();

        long lastFrameTime = System.nanoTime();

        long informePrevio = lastFrameTime; // Informes de FPS
        int frames = 0;

        // Bucle principal
        while (true) {
            long currentTime = System.nanoTime();
            long nanoElapsedTime = currentTime - lastFrameTime;
            lastFrameTime = currentTime;
            double elapsedTime = (double) nanoElapsedTime / 1.0E9;

            // Update de la logica con el deltaTime calculado
            _logic.update(elapsedTime);

            // Informe de FPS
            if (currentTime - informePrevio > 1000000000l) {
                long fps = frames * 1000000000l / (currentTime - informePrevio);
                System.out.println("" + fps + " fps");
                frames = 0;
                informePrevio = currentTime;
            }
            ++frames;

            // Pintamos el frame con el BufferStrategy
            do {
                do {
                    java.awt.Graphics graphics = _strategy.getDrawGraphics();
                    // Cambiamos el contexto grafico al controlador de graficos
                    _graphics.setGraphics(graphics);
                    try {
                        // Render de la logica
                        _logic.render();
                    } finally {
                        graphics.dispose();
                    }
                } while (_strategy.contentsRestored());
                _strategy.show();
            } while (_strategy.contentsLost());
        } // while
    }

    @Override
    public void setLogic(Logic logic) {
        _logic = logic;
    }


    @Override
    public es.ucm.gdv.engine.Graphics getGraphics() {
        return _graphics;
    }

    @Override
    public Input getInput() {
        return _input;
    }

    @Override
    public InputStream openInputStream(String filename) {
        try {
            _inputStream = new FileInputStream("assets/" + filename);
        } catch (Exception e) {
            System.err.println("Error cargando el fichero: " + e);
            return null;
        }
        return _inputStream;
    }

    /**
     * Proporciona soporte para la hebra de Swing
     */
    private JFrame _window;
    /**
     * Ancho y alto de la ventana
     */
    private int _width, _height;
    /**
     * Gestion de la memoria de la ventana
     */
    private BufferStrategy _strategy;
    /**
     * Gestor de graficos
     */
    private Graphics _graphics;
    /**
     * Estado del juego
     */
    private Logic _logic;
    /**
     * Controlador de input
     */
    private Input _input;
    /**
     * Gestor de ficheros
     */
    private InputStream _inputStream;
}
