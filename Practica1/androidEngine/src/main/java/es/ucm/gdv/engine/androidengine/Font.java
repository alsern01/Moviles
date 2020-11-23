package es.ucm.gdv.engine.androidengine;

import android.content.res.AssetManager;
import android.graphics.Typeface;

public class Font implements es.ucm.gdv.engine.Font {
    public Font(String filename, AssetManager assetManager) {
        _font = Typeface.createFromAsset(assetManager, filename);
    }

    public Typeface getFont() {
        return _font;
    }

    private Typeface _font;
}
