package es.ucm.gdv.engine.androidengine;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class AGraphics implements es.ucm.gdv.engine.Graphics {

    AGraphics(Canvas c) {
        c_ = c;
    }


    @Override
    public void clear(int a, int r, int g, int b) {
        c_.drawColor(Color.argb(a, r, g, b));
    }

    @Override
    public void translate(int x, int y) {
        c_.translate(x, y);
    }

    @Override
    public void scale(float x, float y) {

    }

    @Override
    public void rotate(float angle) {

    }

    @Override
    public void setColor(int a, int r, int g, int b) {
        paint_.setColor(Color.argb(a, r, g, b));
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        c_.drawLine(x1, y1, x2, y2, paint_);
    }

    @Override
    public void fillRect(int x1, int y1, int x2, int y2) {
        c_.drawRect(x1, y1, x2, y2, paint_);
    }

    @Override
    public void drawText(String text, int x, int y) {
        c_.drawText(text, x, y, paint_);
    }

    @Override
    public int getWidth() {
        return c_.getWidth();
    }

    @Override
    public int getHeight() {
        return c_.getHeight();
    }

    private Paint paint_;
    private Canvas c_;
}
