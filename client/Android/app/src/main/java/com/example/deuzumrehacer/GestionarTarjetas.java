package com.example.deuzumrehacer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class GestionarTarjetas extends AppCompatActivity {

    TextView cGestionarTarjeta;
    EditText tNumeroTarjeta;
    EditText cCVV;
    EditText tFechaCaducidad;
    Button bAtras;
    Button bAñadir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionar_tarjetas);

        cGestionarTarjeta = findViewById(R.id.cajaGestionarTarjeta);
        tNumeroTarjeta = findViewById(R.id.textoNumeroTarjeta);
        cCVV = findViewById(R.id.contrasenyaCVV);
        tFechaCaducidad = findViewById(R.id.textoFechaCaducidad);
        bAtras = findViewById(R.id.botonAtrasGT);
        bAñadir = findViewById(R.id.botonAñadir);

        Thread myThread = new Thread(new MyServerThread());
        myThread.start();

        bAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirMenuPrincipal();
            }
        });

        bAñadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar();
            }
        });
    }

    public void guardar() {
        JSONObject jsonData = new JSONObject();
        try {
            jsonData.put("tableName","cuenta");
            jsonData.put("numero_uenta", String.valueOf(tNumeroTarjeta.getText()));
        } catch (JSONException e) {
            Toast toast= Toast. makeText(getApplicationContext(),
                    "Local: Error al intentar añadir tarjeta.",Toast. LENGTH_SHORT);
            toast. setMargin(50,50);
            toast. show();
            e.printStackTrace();
            return;
        }

        Thread t1 = new Thread(new MessageSender("addData", jsonData));
        t1.start();
        try{
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(ServerRespond.result.equals("0")){
            Toast toast= Toast. makeText(getApplicationContext(),
                    "Server: Error al intentar añadir tarjeta.",Toast. LENGTH_SHORT);
            toast. setMargin(50,50);
            toast. show();
        }
        Intent i = new Intent(this, MenuPrincipal.class);
        startActivity(i);
    }

    public void abrirMenuPrincipal() {
        Intent i = new Intent(this, MenuPrincipal.class);
        startActivity(i);
    }
}
