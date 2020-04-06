package com.example.marcel.combiaqp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class Pantalla_Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla__inicio);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mostrar = new Intent(Pantalla_Inicio.this,MainActivity.class);
                startActivity(mostrar);
            }
        },3000);
    }
}
