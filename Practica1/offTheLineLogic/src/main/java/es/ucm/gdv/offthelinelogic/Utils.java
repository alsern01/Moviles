package es.ucm.gdv.offthelinelogic;

public class Utils {
    /**
     * Devuelve el punto en el que se cruzan 2 segmentos
     *
     * @param s1 Primero segmento
     * @param s2 Segundo segmento
     * @return En el caso de que haya interseccion devuelve el punto en el que se cruzan
     */
    public static Point segmentsIntersection(Segment s1, Segment s2) {
        float s1X, s1Y, s2X, s2Y;

        // s1X y s1Y forman un vector de tamaño la distancia entre los puntos
        // del primer segmento
        s1X = s1.p2.getX() - s1.p1.getX();
        s1Y = s1.p2.getY() - s1.p1.getY();
        // s2X y s2Y forman un vector de tamaño la distancia entre los puntos
        // del segundo segmento
        s2X = s2.p2.getX() - s2.p1.getX();
        s2Y = s2.p2.getY() - s2.p1.getY();

        // u y t son escalares con los que se puede representar un punto en el segmento
        // van a servir para detectar si hay colision entre los segmentos s1 y s2
        float u, t;
        u = (-s1Y * (s1.p1.getX() - s2.p1.getX()) + s1X * (s1.p1.getY() - s2.p1.getY())) / (-s2X * s1Y + s1X * s2Y);
        t = (s2X * (s1.p1.getY() - s2.p1.getY()) - s2Y * (s1.p1.getX() - s2.p1.getX())) / (-s2X * s1Y + s1X * s2Y);

        // Si ambos son = 0, los segmentos son colineares
        // Si estan dentro del rango [0, 1] hay colision
        if (u >= 0 && u <= 1 && t >= 0 && t <= 1) {
            // Hay colision
            return new Point(s1.p1.getX() + (t * s1X), s1.p1.getY() + (t * s1Y));
        }

        return null; // No hay colision
    }


    /**
     * Devuelve la distancia al cuadrado de un punto dado a un segmento dado
     *
     * @param p Punto desde  el cual se quiere calcular la distancia
     * @param s Segmento
     * @return Distancia al cuadrodo de un punto a un segmento
     */
    public static float sqrDistancePointSegment(Point p, Segment s) {

        // S es el vector formado por los 2 puntos del segmento
        float sx = s.p2.getX() - s.p1.getX();
        float sy = s.p2.getY() - s.p1.getY();
        // P es el vector formado por el punto y el primer punto del segemento
        float px = p.getX() - s.p1.getX();
        float py = p.getY() - s.p1.getY();

        float dot = px * sx + py * sy; // Producto escalar de P y S

        if (dot <= 0) // S*P <= 0?
            return px * px + py * py; // Devuelve modulo de P al cuadrado
        else if (dot >= (sx * sx + sy * sy)) { // S*P >= S*S?
            // T es el vector que forman el segundo punto del vector y el punto
            float tx = s.p2.getX() - p.getX();
            float ty = s.p2.getY() - p.getY();
            return tx * tx + ty * ty; // Devuelve devuelve modulo de T al cuadrado
        } else {
            // Devuelve modulo de P al cuadrado - proy(P sobre S) al cuadrado
            return (px * px + py * py - (dot * dot) / (sx * sx + sy * sy));
        }
    }

    public static float sqrDistancePointPoint(Point p1, Point p2) {
        float d = 0.0f;
        d = (float) (Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
        return d;
    }
}
