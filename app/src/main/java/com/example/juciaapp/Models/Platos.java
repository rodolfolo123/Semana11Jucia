package com.example.juciaapp.Models;

import java.util.ArrayList;

public class Platos {
    public String Nombre;
    public String Descripcion;
    public String Imagen;

    public Platos(String _nombre, String _descripcion, String _imagen) {
        this.Nombre = _nombre   ;
        this.Descripcion = _descripcion;
        this.Imagen = _imagen;
    }

    public static ArrayList getCollection() {
        ArrayList<Platos> collection = new ArrayList<>();
        collection.add(new Platos("Pachamanca", "Rica Pachamanca","https://mymodernmet.com/wp/wp-content/uploads/2019/09/food-art-jacobs-food-diaries-10.jpg"));
        collection.add(new Platos("Aji de Pollo", "Rico Aji de Pollo","https://mymodernmet.com/wp/wp-content/uploads/2019/09/food-art-jacobs-food-diaries-13.jpg"));
        collection.add(new Platos("Arroz Chaufa", "Rico Arroz Chaufa","https://mymodernmet.com/wp/wp-content/uploads/2019/09/food-art-jacobs-food-diaries-7.jpg"));
        return collection;
    }

    public String getSmallImage(){
        return  this.Imagen;
    }

}
