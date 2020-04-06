package com.example.marcel.combiaqp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Buscar_Ruta extends AppCompatActivity {

    Button boton_Buscar;
    ArrayList<String> listas_Rutas;
    android.app.AlertDialog alert = null;

    String [] paraderos =
            {"Plaza de Armas","Mall Aventura", "Universida Alas Peruanas", "Universidad UNSA", "Mariscal Castilla", "Ovalo Avelino","PUENTE GRAU","SEGURO SOCIAL","SAGA","TELEFONICA","REAL PLAZA","TOTTUS","PLAZA VEA","HOSPITAL HONORIO DELAGADO","CLINICA DANIEL ALCIDES CARRION"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar__ruta);

        boton_Buscar=(Button)findViewById(R.id.btn_buscar_Ruta);

       // final TextView mostrar = (TextView) findViewById(R.id.txtMostrar);
        final  TextView listar=(TextView)findViewById(R.id.txtListar) ;

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,paraderos);
        final AutoCompleteTextView nombres = (AutoCompleteTextView)findViewById(R.id.txtParadero);
        nombres.setAdapter(adapter2);

        boton_Buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String aux = nombres.getText().toString();


                if ( aux.equals("Mall Aventura"))
                {
                  //  mostrar.setText( "Usted Escogio :  " + aux);

                     listar.setText("COTUM A " +" o  "+"COTUM B " );

                }
                else if (aux.equals("Universida Alas Peruanas"))
                {

                    listar.setText("Canarios C "  );
                }
                else if (aux.equals("Plaza de Armas"))
                {

                    listar.setText("Canarios C "  +" o  "+"Canarios A " );
                }
            }
        });

    }
}
