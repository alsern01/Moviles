package es.ucm.gdv.app.desktop;

import es.ucm.gdv.engine.desktopengine.Engine;
import es.ucm.gdv.offthelinelogic.BaseLogic;

public class Main {

    public static void main(String[] args) {

        // Se crea el juego y el estado del juego
        Engine game = new Engine(640, 480);
        BaseLogic logic = new BaseLogic();
        // estado logico

        // Iniciar ambos modulos y si algo falla cerrar el programa
        if (!game.init(logic)) return;
        if (!logic.init(game)) return;

        // Lanzar el bucle principal
        game.run();
    }
}