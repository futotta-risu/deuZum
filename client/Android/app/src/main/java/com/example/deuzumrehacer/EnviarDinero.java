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

public class EnviarDinero extends AppCompatActivity {

    private TextView cEnviarDinero;
    private EditText tUsuarioDestinatario;
    private EditText tImporte;
    private Button bFinalizar;
    private Button bAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_dinero);

        cEnviarDinero = findViewById(R.id.cajaEnviarDinero);
        tUsuarioDestinatario = findViewById(R.id.textoNombreDestinatario);
        tImporte = findViewById(R.id.numImporte);
        bFinalizar = findViewById(R.id.botonFinalizar);
        bAtras = findViewById(R.id.botonAtrasED);

        bFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarDinero();
            }
        });

        bAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirMenuPrincipal();
            }
        });
    }

    public void enviarDinero(){
        JSONObject jsonData = new JSONObject();
        try {
            jsonData.put("tableName","cuenta");
            jsonData.put("account", String.valueOf(tUsuarioDestinatario.getText()));
            jsonData.put("ammount", String.valueOf(tImporte.getText()));
        } catch (JSONException e) {
            Toast toast= Toast. makeText(getApplicationContext(),
                    "Local: Error al intentar añadir tarjeta.",Toast. LENGTH_SHORT);
            toast. setMargin(50,50);
            toast. show();
            e.printStackTrace();
            return;
        }

        Thread t1 = new Thread(new MessageSender("addMoney", jsonData));
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
