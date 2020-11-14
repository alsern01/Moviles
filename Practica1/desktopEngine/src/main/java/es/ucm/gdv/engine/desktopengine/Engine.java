package es.ucm.gdv.engine.desktopengine;

import es.ucm.gdv.engine.Input;
import es.ucm.gdv.engine.InputStream;

import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Engine implements es.ucm.gdv.engine.Engine {

    public Engine(int width, int height) {
        _width = width;
        _height = height;
    }

    @Override
    public boolean init() {

        try {
            _window = new JFrame();
            _window.setSize(_width, _height);
            _window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            _graphics = new Graphics(_window);

            //_input = new Input();
            //_inputStream = new InputStream();

            return true;

        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
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
    public InputStream openInputStream() {
        return _inputStream;
    }

    private JFrame _window;
    private int _width, _height;
    private BufferStrategy _strategy;
    private Graphics _graphics;
    private Input _input;
    private InputStream _inputStream;
}
