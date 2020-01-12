package com.example.deuzumrehacer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.FileUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Logger;

public class VerUsuario extends AppCompatActivity {

    private TextView cVerUsuario;
    private EditText tNombreUsuario;
    private EditText tTelefono;
    private EditText cContrasenya;
    private EditText tDireccion;
    private EditText tEmail;
    private Button bGuardarCambios;
    private Button bAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_usuario);

        cVerUsuario = findViewById(R.id.cajaVerUsuario);
        tNombreUsuario = findViewById(R.id.textoNombreUsuario);
        tTelefono = findViewById(R.id.textoTelefono);
        cContrasenya = findViewById(R.id.editarContrasenya);
        tDireccion = findViewById(R.id.textoDireccion);
        tEmail = findViewById(R.id.textoEmail);
        bGuardarCambios = findViewById(R.id.botonGuardarCambios);
        bAtras = findViewById(R.id.botonAtrasVU);


        Thread myThread = new Thread(new MyServerThread());
        myThread.start();

        bAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirMenuPrincipal();
            }
        });

        bGuardarCambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarCambiosUsuario();
            }
        });

        bGuardarCambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarCambiosInformacionUsuario();
            }
        });

    }

    public void guardarCambiosUsuario() {

        JSONObject jsonData = new JSONObject();
        try {
            jsonData.put("tableName","usuario");
            jsonData.put("usuario", String.valueOf(tNombreUsuario.getText()));
            jsonData.put("pass",String.valueOf(cContrasenya.getText()));
        } catch (JSONException e) {
            Toast toast= Toast. makeText(getApplicationContext(),
                    "Local: Error al intentar cambiar usuario.",Toast. LENGTH_SHORT);
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
                    "Server: Error al intentar cambiar usuario. ",Toast. LENGTH_SHORT);
            toast. setMargin(50,50);
            toast. show();
        }

        try {
            Activity activity = new Activity();
            File file = new File("java/exitoVerUsuario.txt");
            FileInputStream is = new FileInputStream(file);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String text = new String(buffer);

            Toast.makeText(activity,text,Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Intent i = new Intent(this, MenuPrincipal.class);
        startActivity(i);

    }

    public void guardarCambiosInformacionUsuario() {
        JSONObject jsonData = new JSONObject();
        try {
            jsonData.put("tableName","infoUsuario");
            jsonData.put("telefono", String.valueOf(tTelefono.getText()));
            jsonData.put("email", String.valueOf(tEmail.getText()));
            jsonData.put("direccion", String.valueOf(tDireccion.getText()));
        } catch (JSONException e) {
            Toast toast= Toast. makeText(getApplicationContext(),
                    "Local: Error al intentar cambiar la información del usuario.",Toast. LENGTH_SHORT);
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
                    "Server: Error al intentar cambiar la información del usuario. ",Toast. LENGTH_SHORT);
            toast. setMargin(50,50);
            toast. show();
        }
    }

    public void abrirMenuPrincipal() {
        Intent i = new Intent(this, MenuPrincipal.class);
        startActivity(i);
    }
}