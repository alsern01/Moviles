package es.ucm.gdv.engine;

public interface Engine {
    boolean init();

    Graphics getGraphics();
    Input getInput();
    InputStream openInputStream(/*filename*/);
}