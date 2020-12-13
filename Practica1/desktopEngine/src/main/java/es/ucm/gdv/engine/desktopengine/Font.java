package es.ucm.gdv.engine.desktopengine;

import java.io.FileInputStream;
import java.io.InputStream;

public class Font implements es.ucm.gdv.engine.Font {
    public Font(String filename, int size, boolean isBold) {
        // Cargamos la fuente del fichero .ttf.
        try (InputStream is = new FileInputStream("assets/Fuentes/" + filename)) {
            _font = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, is);
        } catch (Exception e) {
            // Ouch. No está.
            System.err.println("Error cargando la fuente: " + e);
        }

        if (isBold) _font = _font.deriveFont(java.awt.Font.BOLD, size);
        else _font = _font.deriveFont(size);
    }

    public java.awt.Font getFont() {
        return _font;
    }

    private java.awt.Font _font;
}
