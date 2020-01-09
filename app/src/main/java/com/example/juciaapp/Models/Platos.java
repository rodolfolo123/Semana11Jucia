package com.example.juciaapp.Models;

import java.util.ArrayList;

  /*nuevo import*/

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.juciaapp.MainActivity;
import com.example.juciaapp.helpers.QueueUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class Platos {
    public String Nombre;
    public String Descripcion;
    public String Imagen;

    public Platos(String _nombre, String _descripcion, String _imagen) {
        this.Nombre = _nombre   ;
        this.Descripcion = _descripcion;
        this.Imagen = _imagen;
    }

  /*  public static ArrayList getCollection() {
        ArrayList<Platos> collection = new ArrayList<>();
        collection.add(new Platos("Pachamanca", "Rica Pachamanca","https://mymodernmet.com/wp/wp-content/uploads/2019/09/food-art-jacobs-food-diaries-10.jpg"));
        collection.add(new Platos("Aji de Pollo", "Rico Aji de Pollo","https://mymodernmet.com/wp/wp-content/uploads/2019/09/food-art-jacobs-food-diaries-13.jpg"));
        collection.add(new Platos("Arroz Chaufa", "Rico Arroz Chaufa","https://mymodernmet.com/wp/wp-content/uploads/2019/09/food-art-jacobs-food-diaries-7.jpg"));
        return collection;
    }*/
 /*   public String getSmallImage(){
        return  this.Imagen;
    }*/
    public static void injectContactsFromCloud(final QueueUtils.QueueObject o,
                                               final ArrayList<Platos> platos,
                                               final MainActivity _interface) {
        String url = "http://fipo.equisd.com/api/users.json";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        if (response.has("objects")) {

                            try {
                                JSONArray list = response.getJSONArray("objects");
                                for (int i=0; i < list.length(); i++) {
                                    JSONObject o = list.getJSONObject(i);
                                    platos.add(new Platos(o.getString("first_name"),
                                            o.getString("last_name"),""));
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            _interface.refreshList(); // Esta función debemos implementarla
                            // en nuestro activity
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                    int x = 5;
                    x++;
                    }
                });

        o.addToRequestQueue(jsonObjectRequest);
    }
}

