package es.ucm.gdv.offthelinelogic;

import es.ucm.gdv.engine.Graphics;

public class Player extends Entity {
    public Player(float x, float y, float size, float speed, Path path) {
        super(x, y, size, speed);

        _currentSegment = path.getSegments().get(0);
        _actualPath = path;

        _dir = new Point(0, 0);
        _posJump = new Point(0, 0);

        _baseSpeed = speed;
        _segmentDist = _currentSegment.getSqrDistance();
        _dir = new Point(_currentSegment.getNormalizedXDistance(), _currentSegment.getNormalizedYDistance());
    }

    public void render(Graphics g) {
        g.setColor(255, 0, 136, 255);
        super.render(g);
    }

    public void update(double deltaTime) {
        if (!_dead) {

            if (!_jump) {
                _speed = _baseSpeed;
                if (_disTraveled * _disTraveled >= _segmentDist)
                    checkPath();
            } else {
                _speed = _jumpSpeed;
            }
            Point prevP = new Point(_pos.x, _pos.y);

            _pos.x += _speed * _dir.getX() * deltaTime;
            _pos.y += _speed * _dir.getY() * deltaTime;

            _collSeg = new LevelSegment(prevP, _pos);

            _disTraveled += _speed * deltaTime;

            super.update(deltaTime);
        }
    }

    public void jump() {
        if (!_dead) {
            _posJump.setX(_pos.x);
            _posJump.setY(_pos.y);

            _dirJump = _dir;

            if (!_actualPath.getDirs().isEmpty()) {
                // Si tiene vector direccion devuelve ese vector
                _dir = _currentSegment.getDir();
            } else {
                // Devuelve el vector perpendicular en funcion del sentido de giro
                _dir = new Point(_currentSegment.p2.y - _currentSegment.p1.getY(),
                        -(_currentSegment.p2.getX() - _currentSegment.p1.getX()));
            }

            _dir.normalize();

            _disTraveled = 0;
            _jump = true;
        }
    }

    public Point getJumpPos() {
        return _posJump;
    }

    public boolean isJumping() {
        return _jump;
    }

    // Reinicia al jugador
    public void playerDeath(Path path) {
        _currentSegment = path.getSegments().get(0);
        _actualPath = path;

        _pos = _currentSegment.p1;

        _disTraveled = 0;
        _segmentDist = _currentSegment.getSqrDistance();
        _dir = new Point(_currentSegment.getNormalizedXDistance(), _currentSegment.getNormalizedYDistance());
        _normalMove = 1;
        _jump = false;
        _currentSegIndex = 0;
    }

    public float getDistance() {
        return _disTraveled;
    }

    public LevelSegment getCurrentSegment() {
        return _currentSegment;
    }

    public void nextPathSegment(Path p, LevelSegment s, Point i) {
        _currentSegment = s;
        _actualPath = p;
        _jump = false;
        _normalMove = -_normalMove;
        _currentSegIndex = _actualPath.getSegments().indexOf(s);

        _pos.x = i.x;
        _pos.y = i.y;

        setDir();

        _disTraveled = 0;
    }

    public boolean checkSameDir(LevelSegment s) {
        Point p;
        if (_normalMove < 0) {
            p = new Point(s.getNormalizedXDistance(), s.getNormalizedYDistance());
        } else {
            p = new Point(s.getInvertedSegment().getNormalizedXDistance(), s.getInvertedSegment().getNormalizedYDistance());
        }

        boolean b1 = p.getX() == _dirJump.getX();
        boolean b2 = p.getY() == _dirJump.getY();
        return b1 && b2;
    }

    public LevelSegment get_collSeg() {
        return _collSeg;
    }


    private void checkPath() {
        _currentSegIndex += _normalMove;

        // Devuelve el siguiente segmento en orden ascedente
        if (_currentSegIndex == _actualPath.getSegments().size()) {
            _currentSegIndex = 0;
        } else if (_currentSegIndex < 0) { // Devuelve el primero en caso de ser el último segmento del vector
            _currentSegIndex = _actualPath.getSegments().size() - 1;
        }

        _currentSegment = _actualPath.getSegments().get(_currentSegIndex);

        if (_normalMove > 0) {
            _pos.x = _currentSegment.p1.x;
            _pos.y = _currentSegment.p2.y;

        } else {
            _pos.x = _currentSegment.getInvertedSegment().p1.x;
            _pos.y = _currentSegment.getInvertedSegment().p1.x;
        }

        setDir();
    }


    private void setDir() {
        if (_normalMove > 0) {
            _dir.x = _currentSegment.p2.x - _currentSegment.p1.x;
            _dir.y = _currentSegment.p2.y - _currentSegment.p1.y;
            _segmentDist = Utils.sqrDistancePointPoint(_pos, _currentSegment.p2);

        } else {
            _dir.x = _currentSegment.getInvertedSegment().p2.x - _pos.x;
            _dir.y = _currentSegment.getInvertedSegment().p2.y - _pos.y;
            _segmentDist = Utils.sqrDistancePointPoint(_pos, _currentSegment.getInvertedSegment().p2);

        }

        _dir.normalize();

        _disTraveled = 0;
    }

    // Velocidad base del jugador
    private final float _baseSpeed;

    // Direccion en la que se mueve el jugador
    private Point _dir;

    // El segmento actual por el que avanza el jugador
    private LevelSegment _currentSegment;

    // Path del segmento actual
    private Path _actualPath;

    // Distancia recorrida por el jugador del segmento actual
    private float _disTraveled = 0;
    private float _segmentDist;

    // Booleano para saber si el player esta en el aire
    private boolean _jump = false;
    // Velocidad del desplazamiento durante el salto
    private final float _jumpSpeed = 1500;
    // Posicion del jugador antes del salto
    private Point _posJump;
    // Direccion del jugador antes del salto
    private Point _dirJump;
    private LevelSegment _collSeg;
    // Booleano para saber si el jugador sigue con vida
    private boolean _dead = false;
    // Entero que marca el sentido en el que se mueve el jugador
    private int _normalMove = 1;
    // Contador para seleccionar el siguiente segmento de desplazamiento
    private int _currentSegIndex = 0;
}
