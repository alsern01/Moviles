package es.ucm.gdv.engine;

public interface Graphics {
    /*public Font newFont(String filename, int size, boolean isBold) {
        Font font = new Font();

        return font;
    }*/

    public void clear(int a, int r, int g, int b);

    /**
     * Metodos de control de la transformacion sobre el canvas
     * scale(x, y)
     * rotate(angle)
     * save()
     * restore()
     **/
    public void translate(int x, int y);

    public void scale(float x, float y);

    public void rotate(float angle);

    public void setColor(int a, int r, int g, int b);

    public void drawLine(int x1, int y1, int x2, int y2);

    public void fillRect(int x1, int y1, int x2, int y2);

    void drawText(String text, int x, int y);

    int getWidth();

    int getHeight();
}
