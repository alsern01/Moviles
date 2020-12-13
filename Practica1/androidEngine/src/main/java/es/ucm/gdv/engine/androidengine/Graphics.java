package es.ucm.gdv.engine.androidengine;

import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;

public class Graphics extends es.ucm.gdv.engine.AbstractGraphics {

    Graphics(AssetManager assetManager, SurfaceView surfaceView) {
        _assetManager = assetManager;
        _surfaceView = surfaceView;
    }

    public void setCanvas(Canvas c) {
        _c = c;
    }

    @Override
    public Font newFont(String filename, int size, boolean isBold) {
        _font = new Font(filename, _assetManager);
        if (_font != null) {
            // Tenemos fuente. Vamos a escribir texto.
            // Preparamos la configuración de formato en el
            // objeto _paint que utilizaremos en cada frame.
            _paint.setTypeface(_font.getFont());
            if (isBold)
                _paint.setFakeBoldText(true);
            _paint.setTextSize(size);
        }
        return _font;
    }

    @Override
    public boolean init() {
        _paint = new Paint();
        if (_paint != null && _assetManager != null && _surfaceView != null)
            return true;
        else return false;
    }

    @Override
    public void clear(int a, int r, int g, int b) {
        _c.drawColor(Color.argb(a, r, g, b));
    }

    @Override
    public void translate(int x, int y) {
        _c.translate(x, y);
    }

    @Override
    public void scale(float x, float y) {
        _c.scale(x, y);
    }

    @Override
    public void rotate(float angle) {
        _c.rotate(angle);
    }

    @Override
    public void save() {
        _c.save();
    }

    @Override
    public void restore() {
        _c.restore();
    }

    @Override
    public void setColor(int a, int r, int g, int b) {
        _paint.setColor(Color.argb(a, r, g, b));
    }

    @Override
    public void drawLine(float x1, float y1, float x2, float y2) {
        _c.drawLine(x1, y1, x2, y2, _paint);
    }

    @Override
    public void fillRect(int x1, int y1, int x2, int y2) {
        _c.drawRect(x1, y1, x2, y2, _paint);
    }

    @Override
    public void drawText(String text, int x, int y) {
        if (_font != null)
            _c.drawText(text, x, y, _paint);
    }

    @Override
    public int getWidth() {
        return _c.getWidth();
    }

    @Override
    public int getHeight() {
        return _c.getHeight();
    }

    private Paint _paint;
    private AssetManager _assetManager;
    private Canvas _c;
    private SurfaceView _surfaceView;
    private Font _font;
}
