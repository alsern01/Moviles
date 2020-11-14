package es.ucm.gdv.engine.desktopengine;

import java.awt.Color;

import javax.swing.JFrame;

public class Graphics implements es.ucm.gdv.engine.Graphics {

    public Graphics(JFrame window) {
        _window = window;
    }

    public void setGraphics(java.awt.Graphics g) {
        _g = g;
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

    }

    @Override
    public void rotate(float angle) {

    }

    /*@Override
    public void setFont(Font font) {

    }*/

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

    private java.awt.Graphics _g;
    private JFrame _window;
}