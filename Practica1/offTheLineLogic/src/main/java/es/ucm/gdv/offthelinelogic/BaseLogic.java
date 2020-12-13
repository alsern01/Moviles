package es.ucm.gdv.offthelinelogic;

import es.ucm.gdv.engine.Engine;
import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.Input;

public class BaseLogic implements es.ucm.gdv.engine.Logic {
    @Override
    public boolean init(Engine app) {
        _app = app;
        _lvlLoader = new LoadLevel(app, _nLevel);

        System.out.println(_lvlLoader.getName());

        _graphics = _app.getGraphics();
        _input = _app.getInput();
        if (_hardMode) {
            _playerSpeed = 400;
            lifes = 5;
        } else {
            _playerSpeed = 250;
            lifes = 10;
        }
        _player = new Player(_lvlLoader.getPaths().get(0).getVerts().get(0).getX(), _lvlLoader.getPaths().get(0).getVerts().get(0).getY(),
                6, _playerSpeed, _lvlLoader.getPaths().get(0));
        _levelInfo = new UI(80, 180, 10, lifes);

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

        checkCollision();
        checkPlayerOutofBounds();
        deleteCoins();
        nextLevel();
    }

    @Override
    public void render() {
        // limpia la pantalla pintando de negro
        _graphics.clear(255, 0, 0, 0);

        _graphics.calculateCanvasSize();
        float scale = _graphics.getCanvasScale();

        _graphics.translate(_graphics.getWidth() / 2, _graphics.getHeight() / 2);
        _graphics.scale(scale, scale);
        _graphics.setColor(255, 255, 255, 255);
        _graphics.drawText("LEVEL " + (_nLevel + 1) + " - " + _lvlLoader.getName(), -300, -170);

        _graphics.scale(scale, -scale);

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
        _levelInfo.render(_graphics);
    }

    @Override
    public void handleInput(Input input) {
        for (Input.TouchEvent e : input.getTouchEvents()) {
            if (e.getEventType() == Input.TouchEventType.CLICKED || e.getEventType() == Input.TouchEventType.TOUCHED) {
                _player.jump();
            }
        }

        input.clearTouchEvents();
    }

    private void checkCollision() {
        Segment playerSegment = _player.get_collSeg();

        Point intersection = null;

        boolean collision = false;

        for (Enemy e : _lvlLoader.getEnemies()) {
            intersection = Utils.segmentsIntersection(playerSegment, e.getSegment());

            if (intersection != null) {
                restartLevel();
            }
        }

        if (_player.isJumping()) {
            playerSegment = new Segment(_player.getJumpPos().getX(), _player.getJumpPos().getY(), _player.getX(), _player.getY());

            for (int i = 0; i < _lvlLoader.getPaths().size() && !collision; i++) {
                for (int j = 0; j < _lvlLoader.getPaths().get(i).getSegments().size() && !collision; j++) {
                    intersection = Utils.segmentsIntersection(playerSegment,
                            _lvlLoader.getPaths().get(i).getSegments().get(j));
                    // Si ha habido colision
                    if (intersection != null && _player.getDistance() > 5f) {
                        LevelSegment s = _lvlLoader.getPaths().get(i).getSegments().get(j);
                        if (s != _player.getCurrentSegment()
                                && !_player.checkSameDir(s)) {
                            Path p = _lvlLoader.getPaths().get(i);
                            _player.nextPathSegment(p, s, intersection);
                            collision = true;
                        }
                    }
                }
            }

            for (Item c : _lvlLoader.getItems()) {
                float distance = Utils.sqrDistancePointSegment(new Point(c.getX(), c.getY()), playerSegment);
                if (distance <= 20) {
                    c.destroy();
                }
            }
        }

    }

    private void deleteCoins() {
        for (int c = 0; c < _lvlLoader.getItems().size(); c++) {
            if (_lvlLoader.getItems().get(c).isDestroyed())
                _lvlLoader.getItems().remove(_lvlLoader.getItems().get(c));
        }
    }

    public void checkPlayerOutofBounds() {
        if (_player.getX() > _graphics.getWidth() / 2 ||
                _player.getY() > _graphics.getHeight() / 2 ||
                _player.getX() < -_graphics.getWidth() / 2 ||
                _player.getY() < -_graphics.getHeight() / 2)
            restartLevel();
    }

    public void nextLevel() {
        if (_lvlLoader.getItems().isEmpty()) {
            _nLevel++;
            if (_nLevel > 19) {
                System.out.println("SACABO PERO BIEN");
            } else {
                clearLevel();

                _lvlLoader.getParser().parseLevel(_nLevel, _lvlLoader);
                _lvlLoader.buildPaths();
                _player.playerDeath(_lvlLoader.getPaths().get(0));
            }
        }
    }

    public void clearLevel() {
        _lvlLoader.getItems().clear();
        _lvlLoader.getEnemies().clear();
        _lvlLoader.getPaths().clear();
    }

    public void restartLevel() {
        clearLevel();

        _lvlLoader.getParser().parseLevel(_nLevel, _lvlLoader);
        _lvlLoader.buildPaths();

        lifes--;
        _levelInfo.setLifes(lifes);

        _player.playerDeath(_lvlLoader.getPaths().get(0));

        if (lifes == 0) {
            System.out.println("SACABO");
        }
    }

    private Engine _app;
    // Gestores para ahorrar llamadas a la aplicacion
    private Graphics _graphics;
    private Input _input;

    // Cargador de niveles
    private LoadLevel _lvlLoader;
    private int _nLevel = 1;

    // Entidad del jugador
    private Player _player;
    private boolean _hardMode = false;
    private int _playerSpeed = 0, lifes = 0;
    // Muestra las vidas en pantalla
    private UI _levelInfo;
}
