package es.ucm.gdv.offthelinelogic;

public class Utils {
    /**
     * Devuelve el punto en el que se cruzan 2 segmentos
     *
     * @param s1 Primero segmento
     * @param s2 Segundo segmento
     *
     * @return En el caso de que haya interseccion devuelve el punto en el que se cruzan
     */
    public static Point segmentsIntersection(Segment s1, Segment s2) {
        float s1X, s1Y, s2X, s2Y;

        // s1X y s1Y forman un vector de tamaño la distancia entre los puntos
        // del primer segmento
        s1X = s1.getX2() - s1.getX1();
        s1Y = s1.getY2() - s1.getY1();
        // s2X y s2Y forman un vector de tamaño la distancia entre los puntos
        // del segundo segmento
        s2X = s2.getX2() - s2.getX1();
        s2Y = s2.getY2() - s2.getY1();

        // u y t son escalares con los que se puede representar un punto en el segmento
        // van a servir para detectar si hay colision entre los segmentos s1 y s2
        float u, t;
        u = (-s1Y * (s1.getX1() - s2.getX1()) + s1X * (s1.getY1() - s2.getY1())) / (-s2X * s1Y + s1X * s2Y);
        t = (s2X * (s1.getY1() - s2.getY1()) - s2Y * (s1.getX1() - s2.getX1())) / (-s2X * s1Y + s1X * s2Y);

        // Si ambos son = 0, los segmentos son colineares
        // Si estan dentro del rango [0, 1] hay colision
        if (u >= 0 && u <= 1 && t >= 0 && t <= 1) {
            // Hay colision
            return new Point(s1.getX1() + (t * s1X), s1.getY1() + (t * s1Y));
        }

        return null; // No hay colision
    }


    /**
     * Devuelve la distancia al cuadrado de un punto dado a un segmento dado
     *
     * @param p Punto desde  el cual se quiere calcular la distancia
     * @param s Segmento
     *
     * @return Distancia al cuadrodo de un punto a un segmento
     */
    public static float sqrDistancePointSegment(Point p, Segment s) {

        // S es el vector formado por los 2 puntos del segmento
        float sx = s.getX2() - s.getX1();
        float sy = s.getY2() - s.getY1();
        // P es el vector formado por el punto y el primer punto del segemento
        float px = p.getX() - s.getX1();
        float py = p.getY() - s.getY1();

        float dot = px * sx + py * sy; // Producto escalar de P y S

        if (dot <= 0) // S*P <= 0?
            return px * px + py * py; // Devuelve modulo de P al cuadrado
        else if (dot >= (sx * sx + sy * sy)) { // S*P >= S*S?
            // T es el vector que forman el segundo punto del vector y el punto
            float tx = s.getX2() - p.getX();
            float ty = s.getY2() - p.getY();
            return tx * tx + ty * ty; // Devuelve devuelve modulo de T al cuadrado
        } else {
            // Devuelve modulo de P al cuadrado - proy(P sobre S) al cuadrado
            return (px * px + py * py - (dot * dot) / (sx * sx + sy * sy));
        }
    }
}
