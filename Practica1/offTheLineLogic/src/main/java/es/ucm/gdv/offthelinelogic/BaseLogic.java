package es.ucm.gdv.offthelinelogic;

import es.ucm.gdv.engine.Engine;
import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.Input;

public class BaseLogic implements es.ucm.gdv.engine.Logic {
    @Override
    public boolean init(Engine app) {
        _app = app;
        _lvlLoader = new LoadLevel(app, 4);

        System.out.println(_lvlLoader.getName());

        _graphics = _app.getGraphics();
        _input = _app.getInput();

        _player = new Player(_lvlLoader.getPaths().get(0).getVerts().get(0).getX() - 6, _lvlLoader.getPaths().get(0).getVerts().get(0).getY() - 6,
                12, 12, 250, _lvlLoader.getPaths());

        return true;
    }

    @Override
    public void update(double deltaTime) {

        for (int i = 0; i < _lvlLoader.getItems().size(); i++) {
            _lvlLoader.getItems().get(i).update(deltaTime);
        }

        for (int i = 0; i < _lvlLoader.getEnemies().size(); i++) {
            _lvlLoader.getEnemies().get(i).update(deltaTime);
        }

        _player.update(deltaTime);
    }

    @Override
    public void render() {
        // limpia la pantalla pintando de negro
        _graphics.clear(255, 0, 0, 0);

        _graphics.calculateCanvasSize();
        float scale = _graphics.getCanvasScale();

        _graphics.translate(_graphics.getWidth() / 2, _graphics.getHeight() / 2);
        _graphics.scale(scale, scale);

        for (int i = 0; i < _lvlLoader.getPaths().size(); i++) {
            _lvlLoader.getPaths().get(i).render(_graphics);
        }

        for (int i = 0; i < _lvlLoader.getEnemies().size(); i++) {
            _lvlLoader.getEnemies().get(i).render(_graphics);
        }

        for (int i = 0; i < _lvlLoader.getItems().size(); i++) {
            _lvlLoader.getItems().get(i).render(_graphics);
        }

        _player.render(_graphics);

    }

    private Engine _app;
    // Gestores para ahorrar llamadas a la aplicacion
    private Graphics _graphics;
    private Input _input;

    // Cargador de niveles
    private LoadLevel _lvlLoader;

    // Entidad del jugador
    Player _player;
}
