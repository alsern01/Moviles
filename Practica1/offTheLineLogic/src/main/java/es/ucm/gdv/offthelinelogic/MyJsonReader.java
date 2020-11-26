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
                    lv.getPaths().add(new Path());

                    // cada path tiene vertices y puede tener direcciones (arrays)
                    JSONArray verts = (JSONArray) actualPath.get("vertices");
                    JSONArray dirs = (JSONArray) actualPath.get("directions");

                    for (int j = 0; j < verts.size(); j++) {
                        JSONObject actualVert = (JSONObject) verts.get(j);

                        float x = (float) actualVert.get("x");
                        float y = (float) actualVert.get("y");

                        lv.getPaths().get(i).pushVertice(x, y);
                    }

                    if (dirs != null) {
                        for (int k = 0; k < dirs.size(); k++) {
                            JSONObject actualDir = (JSONObject) dirs.get(k);

                            int x = (int) actualDir.get("x");
                            int y = (int) actualDir.get("y");

                            lv.getPaths().get(i).pushDirection(x, y);
                        }
                    }
                }
            }

            JSONArray items = (JSONArray) level.get("items");
            if (items != null) {
                for (int i = 0; i < items.size(); i++) {
                    JSONObject actualItem = (JSONObject) items.get(i);
                    float x = (float) actualItem.get("x");
                    float y = (float) actualItem.get("y");
                    float radius = (float) actualItem.get("radius");
                    float speed = (float) actualItem.get("speed");
                    float angle = (float) actualItem.get("angle");

                    lv.getItems().add(new Item(x, y, radius, speed, angle));
                }
            }

            JSONArray enemies = (JSONArray) level.get("enemies");
            if (enemies != null) {
                for (int i = 0; i < enemies.size(); i++) {
                    JSONObject actualEnemy = (JSONObject) enemies.get(i);

                    float x = (float) actualEnemy.get("x");
                    float y = (float) actualEnemy.get("y");
                    float length = (float) actualEnemy.get("length");
                    float angle = (float) actualEnemy.get("angle");

                    float speed = 0, time1 = 0, time2 = 0, offX = 0, offY = 0;

                    if (actualEnemy.get("speed") != null)
                        speed = (float) actualEnemy.get("speed");

                    if (actualEnemy.get("time1") != null)
                        time1 = (float) actualEnemy.get("time1");

                    if (actualEnemy.get("time2") != null)
                        time2 = (float) actualEnemy.get("time2");


                    JSONObject offset = (JSONObject) actualEnemy.get("offset");
                    if (offset != null) {
                        offX = (float) offset.get("x");
                        offY = (float) offset.get("y");
                    }

                    lv.getEnemies().add(new Enemy(x, y, length, angle, speed, time1, time2, offX, offY));
                }
            }

            int time = (int) level.get("time");
            if (time > 0) {
                lv.setTime(time);
            }

            is.close();

        } catch (Exception e) {
            System.out.println("No se puedo leer el JSON: " + e);
        }
    }

    private JSONParser _jsonParser;
    private Engine _engine;
}
