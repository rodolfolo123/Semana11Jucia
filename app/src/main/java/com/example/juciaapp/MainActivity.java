package com.example.juciaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.juciaapp.Adapters.PlatosAdaptador;
import com.example.juciaapp.Models.Platos;
import com.example.juciaapp.helpers.QueueUtils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView PlatosList;
    PlatosAdaptador platosApatador;
    QueueUtils.QueueObject queue = null;
    ArrayList<Platos> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = QueueUtils.getInstance(this.getApplicationContext());

        items = new ArrayList<>();
        PlatosList = findViewById(R.id.contactosList);

        Platos.injectContactsFromCloud(queue, items, this);
        platosApatador = new PlatosAdaptador(this, items,queue.getImageLoader());

        PlatosList.setAdapter(platosApatador);
//
//        platosApatador =
//                new PlatosAdaptador(this, Platos.getCollection(), queue.getImageLoader());
//

//        //platosApatador = new PlatosAdaptador(this, Platos.getCollection(),queue.getImageLoader());
//        PlatosList.setAdapter(platosApatador);

    }

    public void refreshList(){
        if ( platosApatador!= null ) {
            platosApatador.notifyDataSetChanged();
        }
    }
}
