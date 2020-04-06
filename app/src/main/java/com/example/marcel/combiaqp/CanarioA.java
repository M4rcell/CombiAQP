package com.example.marcel.combiaqp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class CanarioA extends AppCompatActivity {

    String html = "https://www.google.com/maps/d/u/0/embed?mid=1ScKT-Ax4TPcq4f5kHrMQhmz3nks";
    Button botonmostrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canario);

        botonmostrar = (Button) findViewById( R.id.btnRutaCanarioA);




    }


    public void Web_CanarioA (View view)
    {
        WebView mapawebcanario = (WebView) this.findViewById(R.id.web_canarioA);
        mapawebcanario.loadUrl(html);
    }
}


