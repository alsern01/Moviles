package es.ucm.gdv.engine.androidengine;

import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Input implements es.ucm.gdv.engine.Input, View.OnTouchListener {

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
    public boolean onTouch(View v, MotionEvent event) {

        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                TouchEvent e1 = new TouchEvent(_graphics, TouchEventType.TOUCHED, (int) x, (int) y, event.getDeviceId());

                _touchEvents.add(e1);
                break;
            case MotionEvent.ACTION_UP:
                TouchEvent e2 = new TouchEvent(_graphics, TouchEventType.RELEASED, (int) x, (int) y, event.getDeviceId());

                _touchEvents.add(e2);
                break;
            case MotionEvent.ACTION_MOVE:
                TouchEvent e3 = new TouchEvent(_graphics, TouchEventType.DRAGGED, (int) x, (int) y, event.getDeviceId());

                _touchEvents.add(e3);
                break;
            default:
                break;
        }

        return true;
    }

    private Graphics _graphics;
    private List<TouchEvent> _touchEvents;
}
