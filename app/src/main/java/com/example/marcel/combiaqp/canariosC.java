package com.example.marcel.combiaqp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.GridLayout;

public class canariosC extends AppCompatActivity {


    String  html = "https://www.google.com/maps/d/u/0/embed?mid=1ScKT-Ax4TPcq4f5kHrMQhmz3nks";
    Button botonmostrar_canarioC;
    GridLayout gridlayour_canarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canarios_c);

        botonmostrar_canarioC = (Button) findViewById( R.id.btn_ruta_canarioC);

        gridlayour_canarios = (GridLayout) findViewById(R.id.grid_canarios_C);

    }
    public void Web_CanarioC (View view)
    {
        WebView mapawebcanarioC = (WebView) this.findViewById(R.id.web_canariosC);
        mapawebcanarioC.loadUrl(html);
    }

    public void mostrar_precios(View button)
    {

        gridlayour_canarios.setVisibility(View.VISIBLE);
    }

    public void ocultarprecio(View button)
    {
        gridlayour_canarios.setVisibility(View.GONE);

    }

}
