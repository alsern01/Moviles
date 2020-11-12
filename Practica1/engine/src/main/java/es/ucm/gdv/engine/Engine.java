package es.ucm.gdv.engine;

public interface Engine {
    void init();
    void close();

    Graphics getGraphics();
    //Input getInput();
    //InputStream openInputStream(filename);
}