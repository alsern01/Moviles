package es.ucm.gdv.offthelinelogic;

public class Utils {
    public static Point segmentsIntersection(Segment s1, Segment s2) {
        float s1X, s1Y, s2X, s2Y;

        // Diferencia de las Xs del primer segmento
        s1X = s1.getX2() - s1.getX1();
        // Diferencia de las Ys del primer segmento
        s1Y = s1.getY2() - s1.getY1();
        // Diferencia de las Xs del segundo segmento
        s2X = s2.getX2() - s2.getX1();
        // Diferencia de las Ys del segundo segmento
        s2Y = s2.getY2() - s2.getY1();

        // u y t son escalares con los que se puede representar un punto en el segmento
        // van a servir para detectar si hay colision entre los segmentos s1 y s2
        float u, t;
        u = (-s1Y * (s1.getX1() - s2.getX1()) + s1X * (s1.getY1() - s2.getY1())) / (-s2X * s1Y + s1X * s2Y);
        t = (s2X * (s1.getY1() - s2.getY1()) - s2Y * (s1.getX1() - s2.getX1())) / (-s2X * s1Y + s1X * s2Y);

        // Si ambos son = 0, los segmentos son colineares
        // Si estan dentro del rango [0, 1] hay colision
        if (u >= 0 && u <= 1 && t >= 0 && t <= 1) {
            // Collision detected
            return new Point(s1.getX1() + (t * s1X), s1.getY1() + (t * s1Y));
        }

        return null; // No collision
    }

    public static void sqrDistancePointSegment() {

    }
}
