package com.example.juciaapp.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*nuevo import*/

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
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


    public static void sendRequestPOST(QueueUtils.QueueObject o, final MainActivity _interface) {
        String url = "http://rrojasen.alwaysdata.net/purchaseorders.json";
        url = "http://fipo.equisd.com/api/users/new.json";
      /*  url = "http://192.168.58.3:8056/api/users/new.json";*/
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //Do it with this it will work
                            JSONObject _response = new JSONObject(response);
                            if (_response.has("object")) {
                                JSONObject object_response = null;
                                try {
                                    object_response = _response.getJSONObject("object");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                if ( object_response != null ) {
                                    try {
                                        System.out.println(object_response.getInt("id"));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("first_name","gaa");
                params.put("last_name","gaa");
                params.put("avatar","Amor por Siempre :v");

                return params;
            }
        };
        o.addToRequestQueue(stringRequest);
    }
}

