package es.ucm.gdv.engine;

import java.util.ArrayList;

public interface Input {
    /**
     * Tipo del evento de pulsación
     */
    enum TouchEventType {TOUCHED, RELEASED, DRAGGED, CLICKED}

    class TouchEvent {
        /**
         * Constructora evento de pulsación
         *
         * @param g  contexto gráfico
         * @param t  tipo de evento
         * @param x  posicion X de la pantalla donde se ha producido el evento
         * @param y  posicion Y de la pantalla donde se ha producido el evento
         * @param id numero para identificar el evento
         */
        public TouchEvent(Graphics g, TouchEventType t, int x, int y, int id) {
            _type = t;
            _x = x;
            _y = y;
            _id = id;
        }

        /**
         * Devuelve el tipo de vento
         */
        public TouchEventType getEventType() {
            return _type;
        }

        /**
         * Devuelve la posicion X donde se produjo el evento
         */
        public int getEventX() {
            return _x;
        }

        /**
         * Devuelve la posicion Y donde se produjo el evento
         */
        public int getEventY() {
            return _y;
        }

        /**
         * Devuelve la id del evento
         */
        public int getEventId() {
            return _id;
        }

        public TouchEventType _type;
        private int _x, _y;
        private int _id;
    }

    /**
     * Devuelve la lista de todos los eventos de pulsacion
     */
    public ArrayList<TouchEvent> getTouchEvents();

    /**
     * Crea un evento de pulsacion
     *
     * @param e evento de pulsacion
     */
    public void setTouchEvent(TouchEvent e);

    /**
     * Limpia todos los eventos de pulsacion
     */
    public void clearTouchEvents();
}
