package es.ucm.gdv.engine.desktopengine;

import java.awt.Color;

import javax.swing.JFrame;


/**
 * Extiende la clase AbstractGraphics del motor. Implementa los metodos de
 * pintado exclusivos en PC
 * <p>
 * Se apoya en la funcionalidad que ofrece JFrame (ventana) y java.awt para
 * el apartado grafico
 */

public class Graphics extends es.ucm.gdv.engine.AbstractGraphics {
    /**
     * Constructora
     *
     * @param window recibe una ventana de JFrame sobre la que pintar
     */
    public Graphics(JFrame window) {
        _window = window;
    }

    /**
     * Asigna un contexto gráfico
     *
     * @param g contexto gráfico
     */
    public void setGraphics(java.awt.Graphics g) {
        _g = (java.awt.Graphics2D) g;
    }

    @Override
    public Font newFont(String filename, int size, boolean isBold) {
        Font font = new Font(filename, size, isBold);

        return font;
    }

    @Override
    public boolean init() {
        if (_window != null)
            return true;
        else return false;
    }

    @Override
    public void clear(int a, int r, int g, int b) {
        setColor(a, r, g, b);
        _g.fillRect(0, 0, getWidth(), getHeight());
    }

    @Override
    public void setColor(int a, int r, int g, int b) {
        _g.setColor(new Color(r, g, b, a));
    }

    @Override
    public void translate(int x, int y) {
        _g.translate(x, y);
    }

    @Override
    public void scale(float x, float y) {
        _g.scale(x, y);
    }

    @Override
    public void rotate(float angle) {
        _g.rotate(angle);
    }

    @Override
    public void save() {

    }

    @Override
    public void restore() {

    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        _g.drawLine(x1, y1, x2, y2);
    }

    @Override
    public void fillRect(int x1, int y1, int x2, int y2) {
        _g.fillRect(x1, y1, x2, y2);
    }

    @Override
    public void drawText(String text, int x, int y) {
        logicToPhysic(x, y);
        //scale(x, y);

        _font = newFont("assets/Fuentes/Bangers-Regular.ttf", 40, true);
        _g.setFont(_font.getFont());

        _g.drawString(text, x, y);
    }

    @Override
    public int getWidth() {
        return _window.getWidth();
    }

    @Override
    public int getHeight() {
        return _window.getHeight();
    }

    private java.awt.Graphics2D _g;
    private JFrame _window;
    private Font _font;
}