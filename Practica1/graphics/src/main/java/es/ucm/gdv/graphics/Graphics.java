package es.ucm.gdv.graphics;

import es.ucm.gdv.font.Font;

public class Graphics {
    public Font newFont(String filename, int size, boolean isBold) {
        Font font = new Font();

        return font;
    }

    public void clear(/*color*/) {

    }

    /**
     * Metodos de control de la transformacion sobre el canvas
     * translate(x, y)
     * scale(x, y)
     * rotate(angle)
     * save()
     * restore()
     **/

    public void setColor(/*color*/) {

    }

    public void drawLine(int x1, int y1, int x2, int y2) {

    }

    public void fillRect(int x1, int y1, int x2, int y2) {

    }

    public void drawText(String text, int x, int y) {

    }

    public int getWidth() {
        return width_;
    }

    public int getHeight() {
        return height_;
    }

    private int width_, height_;
}