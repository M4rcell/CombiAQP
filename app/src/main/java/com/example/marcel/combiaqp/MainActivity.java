package com.example.marcel.combiaqp;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.datatype.Duration;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<String> listaopciones;


    Button boton,boton_r,boton_posicion;
    /***** GPS****/

    ObtenerWebService hiloconexion;
    Location location;
    LocationManager locationManager;
    LocationListener locationListener;
    android.app.AlertDialog alert = null;
    private TextView nombre;
    /***** GPS****/

    String [] combis =
            {"Canarios A", "Canarios B", "Canarios C", "Cotum A", "Cotum B",};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boton = (Button) findViewById( R.id.btnbuscar);
        boton_r=(Button)findViewById(R.id.button_ruta);
        boton_posicion = (Button) findViewById(R.id.button_posicion);

        final TextView  mostrar = (TextView) findViewById(R.id.txtmostrar);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,combis);

        final AutoCompleteTextView nombres = (AutoCompleteTextView)findViewById(R.id.txtcombis);

        nombres.setAdapter(adapter2);

      boton_posicion.setOnClickListener(this);
       // boton_posicion.setOnClickListener((View.OnClickListener) this);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        /****Mejora****/
        if ( !locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            AlertNoGps();
        }
        /********/

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;
            } else {
                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }
        } else {
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }
        MostrarLocalizacion(location);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                MostrarLocalizacion(location);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);

        /***  buscar Rutas ****/
        boton_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mostrarRuta=new Intent(MainActivity.this,Buscar_Ruta.class);
                startActivity(mostrarRuta);
            }
        });

        /* cambio de panatalla de boton*/
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String aux = nombres.getText().toString();


                if ( aux.equals("Canarios A"))
                {
                    //mostrar.setText( "USTED ESCOGIÓ :  " + aux);
                    Intent mostrarcanarioA = new Intent(MainActivity.this,CanarioA.class);
                    startActivity(mostrarcanarioA);

                }

                if ( aux.equals("Canarios B"))
                {

                    //mostrar.setText( "USTED ESCOGIÓ :  " + aux);
                    Intent mostrarcanarioB = new Intent(MainActivity.this,canarioB.class);
                    startActivity(mostrarcanarioB);

                }
                if ( aux.equals("Canarios C"))
                {

                    //mostrar.setText( "USTED ESCOGIÓ :  " + aux);
                    Intent mostrarcanarioC = new Intent(MainActivity.this,canariosC.class);
                    startActivity(mostrarcanarioC);

                }



                if ( aux.equals("Cotum A"))
                {

                    //mostrar.setText( "USTED ESCOGIÓ :  " + aux);
                    Intent mostrarcotumA = new Intent(MainActivity.this,cotumA.class);
                    startActivity(mostrarcotumA);

                }

                if ( aux.equals("Cotum B"))
                {

                    //mostrar.setText( "USTED ESCOGIÓ :  " + aux);
                    Intent mostrarcotumB = new Intent(MainActivity.this,cotumb.class);
                    startActivity(mostrarcotumB);

                }

                else
                {

                    mostrar.setText("Uste no escogio nada");

                }
            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

         /* Actin Bar*/

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher);
        /* Actin Bar*/


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override

    public void onBackPressed() {

        final AlertDialog.Builder myalerta = new AlertDialog.Builder(this);
        myalerta.setMessage("¿Desea salir de COMBIAQP?");
        myalerta.setTitle("Mensaje de confirmación");
        myalerta.setPositiveButton("Si", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Intent intent = new Intent(getApplicationContext(),Pantalla_Inicio.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        myalerta.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = myalerta.create();

        dialog.show();
    }
    public void lanzar (View view)
    {
        Intent i = new Intent(this,CanarioA.class);
        startActivity(i);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override


    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        Context context=getApplicationContext();
        CharSequence text=null;
          int duration=Toast.LENGTH_SHORT;


        //noinspection SimplifiableIfStatement
        if (id == R.id.opcion1) {
            text="Desea buscar";
           // return true;
        }
        else  if (id==R.id.opcion2) {
            text="Desea Compartir";
            //return true;
        }
        else  if (id==R.id.opcion3) {
            text="Configuraciones";
           // return true;
        }
        Toast toast=Toast.makeText(context,text, duration);
        toast.show();

        return super.onOptionsItemSelected(item);
    }

    /*Gps**************/
    private void AlertNoGps() {
        final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setMessage("El sistema GPS esta desactivado, ¿Desea activarlo?")
                .setCancelable(false)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        alert = builder.create();
        alert.show();
    }
    protected void onDestroy(){
        super.onDestroy();
        if(alert != null)
        {
            alert.dismiss ();
        }
    }
    protected void onPause() {
        super.onPause();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;
            } else {
                locationManager.removeUpdates(locationListener);
            }
        } else {
            locationManager.removeUpdates(locationListener);
        }
    }

    protected void onResume()
    {
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;
            } else {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            }
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }
    }

    public void MostrarLocalizacion(Location loc){



        if (loc!=null){
            hiloconexion = new ObtenerWebService();
            hiloconexion.execute(String.valueOf(loc.getLatitude()),String.valueOf(loc.getLongitude()));
        }
    }

    @Override
    public void onClick(View view) {


              hiloconexion = new ObtenerWebService();
             // hiloconexion.execute();


    }

    /*public void onClick(View v) {

        switch (v.getId()){

            case R.id.datos:
                hiloconexion = new ObtenerWebService();
                hiloconexion.execute(latitud.getText().toString(),longitud.getText().toString());   // Parámetros que recibe doInBackground
                break;

            default:
                break;

        }

    }
    */

    public class ObtenerWebService extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {

            String cadena = "https://www.google.com/maps/d/u/0/embed?mid=1ScKT-Ax4TPcq4f5kHrMQhmz3nks";

                 //   "http://maps.googleapis.com/maps/api/geocode/json?latlng="

            //http://maps.googleapis.com/maps/api/geocode/json?latlng=38.404593,-0.529534&sensor=false
            cadena = cadena + params[0];
            cadena = cadena + ",";
            cadena = cadena + params[1];
            cadena = cadena + "&sensor=false";


            String devuelve = "";

            URL url = null; // Url de donde queremos obtener información
            try {
                url = new URL(cadena);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //Abrir la conexión
                connection.setRequestProperty("User-Agent", "Mozilla/5.0" +
                        " (Linux; Android 1.5; es-ES) Ejemplo HTTP");
                //connection.setHeader("content-type", "application/json");

                int respuesta = connection.getResponseCode();
                StringBuilder result = new StringBuilder();

                if (respuesta == HttpURLConnection.HTTP_OK) {


                    InputStream in = new BufferedInputStream(connection.getInputStream());  // preparo la cadena de entrada

                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));  // la introduzco en un BufferedReader

                    // El siguiente proceso lo hago porque el JSONOBject necesita un String y tengo
                    // que tranformar el BufferedReader a String. Esto lo hago a traves de un
                    // StringBuilder.

                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);        // Paso toda la entrada al StringBuilder
                    }

                    //Creamos un objeto JSONObject para poder acceder a los atributos (campos) del objeto.
                    JSONObject respuestaJSON = new JSONObject(result.toString());   //Creo un JSONObject a partir del StringBuilder pasado a cadena
                    //Accedemos al vector de resultados
                    JSONArray resultJSON = respuestaJSON.getJSONArray("results");   // results es el nombre del campo en el JSON

                    //Vamos obteniendo todos los campos que nos interesen.
                    //En este caso obtenemos la primera dirección de los resultados.
                    String direccion = "SIN DATOS PARA ESA LONGITUD Y LATITUD";
                    if (resultJSON.length() > 0) {
                        direccion = resultJSON.getJSONObject(0).getString("formatted_address");    // dentro del results pasamos a Objeto la seccion formated_address
                    }
                    devuelve = "Dirección: " + direccion;   // variable de salida que mandaré al onPostExecute para que actualice la UI

                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return devuelve;
        }

        protected void onCancelled(String aVoid) {
            super.onCancelled(aVoid);

        }

    }

}
