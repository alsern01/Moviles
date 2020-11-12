package es.ucm.gdv.desktopengine;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class PCGraphics implements es.ucm.gdv.engine.Graphics {

    PCGraphics(Graphics g) {
        g_ = g;
    }

    @Override
    public void clear(int a, int r, int g, int b) {
        setColor(a, r, g, b);
        g_.fillRect(0, 0, width_, height_);
    }

    @Override
    public void setColor(int a, int r, int g, int b) {
        g_.setColor(new Color(r, g, b, a));
    }

    @Override
    public void translate(int x, int y) {
        g_.translate(x, y);
    }

    /*@Override
    public void setFont(Font font) {

    }*/

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        g_.drawLine(x1, y1, x2, y2);
    }

    @Override
    public void fillRect(int x1, int y1, int x2, int y2) {
        g_.fillRect(x1, y1, x2, y2);
    }

    @Override
    public void drawText(String text, int x, int y) {
        g_.drawString(text, x, y);
    }

    @Override
    public int getWidth() {
        return width_;
    }

    @Override
    public int getHeight() {
        return height_;
    }

    private int width_, height_;
    private Graphics g_;
}