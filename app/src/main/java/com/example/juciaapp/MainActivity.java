package com.example.juciaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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

        final Button btnTipoLimeno = findViewById(R.id.btnTipoLimeno);
        btnTipoLimeno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,
                        "Platos Limeno",
                        Toast.LENGTH_SHORT).show();
                items.clear();
                Platos.injectContactsFromCloud(queue, items, MainActivity.this);
                btnTipoLimeno.setBackgroundColor(Color.parseColor("#008080"));
            }
        });

        final Button btnTipoHuancaino = findViewById(R.id.btnTipoHuancaino);
        btnTipoHuancaino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,
                        "Platos Huancaino",
                        Toast.LENGTH_SHORT).show();
                items.clear();
                Platos.injectContactsFromCloud(queue, items, MainActivity.this);
                btnTipoHuancaino.setBackgroundColor(Color.parseColor("#008080"));
            }
        });

        queue = QueueUtils.getInstance(this.getApplicationContext());

        items = new ArrayList<>();
        PlatosList = findViewById(R.id.contactosList);

        Platos.injectContactsFromCloud(queue, items, this);
        Platos.sendRequestPOST(queue,this);
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
