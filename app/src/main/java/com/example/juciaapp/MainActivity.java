package com.example.juciaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.juciaapp.Adapters.PlatosAdaptador;
import com.example.juciaapp.Models.Platos;
import com.example.juciaapp.helpers.QueueUtils;

public class MainActivity extends AppCompatActivity {
    ListView PlatosList;
    PlatosAdaptador platosApatador;
    QueueUtils.QueueObject queue = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = QueueUtils.getInstance(this.getApplicationContext());
        platosApatador =
                new PlatosAdaptador(this, Platos.getCollection(), queue.getImageLoader());

        PlatosList = findViewById(R.id.contactosList);
        //platosApatador = new PlatosAdaptador(this, Platos.getCollection(),queue.getImageLoader());
        PlatosList.setAdapter(platosApatador);
    }
}
