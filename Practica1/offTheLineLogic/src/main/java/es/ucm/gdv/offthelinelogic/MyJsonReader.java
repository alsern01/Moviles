package es.ucm.gdv.offthelinelogic;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import java.io.InputStream;
import java.io.InputStreamReader;

import es.ucm.gdv.engine.Engine;

public class MyJsonReader {
    public MyJsonReader(Engine e) {
        _engine = e;
        _jsonParser = new JSONParser();
    }

    public void parseLevel(int nLevel, LoadLevel lv) {
        try {
            InputStream is = _engine.openInputStream("levels.json"); // con el API correcto
            Object o = _jsonParser.parse(new InputStreamReader(is));

            /*
             * El JSON es un array que contiene en cada una de sus posiciones un nivel (objeto)
             * Se parsea el objeto leido dentro del array
             */
            JSONArray levels = (JSONArray) JSONValue.parse(o.toString());
            // Se lee la posicion nLevel del array de objetos y se coge ese objeto
            JSONObject level = (JSONObject) levels.get(nLevel);

            // Se leen cada una de las partes del objeto
            String name = (String) level.get("name");
            lv.setName(name);

            JSONArray paths = (JSONArray) level.get("paths");
            if (paths != null) {
                for (int i = 0; i < paths.size(); i++) {
                    // coge posicion i del array de objetos
                    JSONObject actualPath = (JSONObject) paths.get(i);
                    lv.addPath();

                    // cada path tiene vertices y puede tener direcciones (arrays)
                    JSONArray verts = (JSONArray) actualPath.get("vertices");
                    JSONArray dirs = (JSONArray) actualPath.get("directions");

                    for (int j = 0; j < verts.size(); j++) {
                        JSONObject actualVert = (JSONObject) verts.get(j);

                        String x = String.valueOf(actualVert.get("x"));
                        String y = String.valueOf(actualVert.get("y"));

                        lv.getPaths().get(i).pushVertice(Float.parseFloat(x), Float.parseFloat(y));
                    }

                    if (dirs != null) {
                        for (int k = 0; k < dirs.size(); k++) {
                            JSONObject actualDir = (JSONObject) dirs.get(k);

                            String x = String.valueOf(actualDir.get("x"));
                            String y = String.valueOf(actualDir.get("y"));

                            lv.getPaths().get(i).pushDirection(Float.parseFloat(x), Float.parseFloat(y));
                        }
                    }
                }
            }

            JSONArray items = (JSONArray) level.get("items");
            if (items != null) {
                for (int i = 0; i < items.size(); i++) {
                    JSONObject actualItem = (JSONObject) items.get(i);
                    long x = (long) actualItem.get("x");
                    long y = (long) actualItem.get("y");

                    long radius = 0, speed = 0, angle = 0;

                    if (actualItem.get("radius") != null)
                        radius = (long) actualItem.get("radius");
                    if (actualItem.get("speed") != null)
                        speed = (long) actualItem.get("speed");
                    if (actualItem.get("angle") != null)
                        angle = (long) actualItem.get("angle");

                    lv.getItems().add(new Item(x, y, 4f, radius, speed, angle));
                }
            }

            JSONArray enemies = (JSONArray) level.get("enemies");
            if (enemies != null) {
                for (int i = 0; i < enemies.size(); i++) {
                    JSONObject actualEnemy = (JSONObject) enemies.get(i);

                    long x = (long) actualEnemy.get("x");
                    long y = (long) actualEnemy.get("y");
                    long length = (long) actualEnemy.get("length");
                    long angle = (long) actualEnemy.get("angle");

                    long speed = 0, offX = 0, offY = 0;
                    String time1 = "0", time2 = "0";

                    if (actualEnemy.get("speed") != null)
                        speed = (long) actualEnemy.get("speed");

                    if (actualEnemy.get("time1") != null)
                        time1 = String.valueOf(actualEnemy.get("time1"));

                    if (actualEnemy.get("time2") != null)
                        time2 = String.valueOf(actualEnemy.get("time2"));


                    JSONObject offset = (JSONObject) actualEnemy.get("offset");
                    if (offset != null) {
                        offX = (long) offset.get("x");
                        offY = (long) offset.get("y");
                    }

                    lv.getEnemies().add(new Enemy(x, y, length, angle, speed, Float.parseFloat(time1), Float.parseFloat(time2), offX, offY));
                }
            }

            if (level.get("time") != null) {
                lv.setTime(Integer.parseInt((String) level.get("time")));
            }

            is.close();

        } catch (Exception e) {
            System.out.println("No se puedo leer el JSON: " + e);
        }
    }

    private JSONParser _jsonParser;
    private Engine _engine;
}
