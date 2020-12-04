package es.ucm.gdv.engine.desktopengine;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

public class Input implements es.ucm.gdv.engine.Input, MouseListener, MouseMotionListener {

    public Input(Graphics g) {
        _graphics = g;
        _touchEvents = new ArrayList<>();
    }

    @Override
    public List<TouchEvent> getTouchEvents() {
        return _touchEvents;
    }

    @Override
    public void setTouchEvent(TouchEvent e) {
        _touchEvents.add(e);
    }

    @Override
    public void clearTouchEvents() {
        _touchEvents.clear();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        TouchEvent e = new TouchEvent(_graphics, TouchEventType.CLICKED, mouseEvent.getX(), mouseEvent.getY(), mouseEvent.getID());

        _touchEvents.add(e);
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        TouchEvent e = new TouchEvent(_graphics, TouchEventType.TOUCHED, mouseEvent.getX(), mouseEvent.getY(), mouseEvent.getID());

        _touchEvents.add(e);
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        TouchEvent e = new TouchEvent(_graphics, TouchEventType.RELEASED, mouseEvent.getX(), mouseEvent.getY(), mouseEvent.getID());

        _touchEvents.add(e);
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }


    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        TouchEvent e = new TouchEvent(_graphics, TouchEventType.DRAGGED, mouseEvent.getX(), mouseEvent.getY(), mouseEvent.getID());

        _touchEvents.add(e);
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }

    private Graphics _graphics;
    private List<TouchEvent> _touchEvents;
}
