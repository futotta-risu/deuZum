package com.example.deuzumrehacer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class CrearUsuario extends AppCompatActivity {

    private EditText tNombre;
    private EditText tApellido;
    private EditText tTelefono;
    private EditText nUsuario;
    private EditText cUsuario;
    private Spinner sPreguntaSeguridad;
    private EditText tRespuestaSeguridad;
    private Button bSiguiente;
    private Button bAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);

        tNombre = findViewById(R.id.textoNombre);
        tApellido = findViewById(R.id.textoApellido);
        tTelefono = findViewById(R.id.textoTelefono);
        nUsuario = findViewById(R.id.textoNombreUsuario);
        cUsuario = findViewById(R.id.contrasenyaContrasenya);
        sPreguntaSeguridad = findViewById(R.id.spinnerPreguntaSeguridad);
        tRespuestaSeguridad = findViewById(R.id.textoRespuestaSeguridad);
        bSiguiente = findViewById(R.id.botonCrear);
        bAtras = findViewById(R.id.botonAtrasCU1);

        ArrayList<String> opciones = new ArrayList<String>();

        opciones.add("¿Donde cursaste la educación primaria?");
        opciones.add("¿Como se llama tú heman@ pequeñ@?");
        opciones.add("¿Como se llamaba tu primera mascota?");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);
        sPreguntaSeguridad.setAdapter(adapter);

        try {
            bAtras.setText(Util.getProperty("atras",getApplicationContext()));
            bSiguiente.setText(Util.getProperty("siguiente",getApplicationContext()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        bSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearUsuario();
            }
        });

        bSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                informacionUsuario();
            }
        });

        bAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirInicioSesion();
            }
        });
    }

    public void crearUsuario() {

        JSONObject jsonData = new JSONObject();
        try {
            jsonData.put("tableName","usuario");
            jsonData.put("user", String.valueOf(nUsuario.getText()));
            jsonData.put("pass",String.valueOf(cUsuario.getText()));
            jsonData.put("pregSeguridad", String.valueOf(sPreguntaSeguridad.getSelectedItemPosition()+1));
            jsonData.put("respSeguridad",String.valueOf(tRespuestaSeguridad.getText()) );
        } catch (JSONException e) {
            Toast toast= Toast. makeText(getApplicationContext(),
                    "Local: Error al intentar añadir al usuario.",Toast. LENGTH_SHORT);
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
                    "Server: Error al intentar añadir al usuario. ",Toast. LENGTH_SHORT);
            toast. setMargin(50,50);
            toast. show();
        }
        Intent i = new Intent(this, MenuPrincipal.class);
        startActivity(i);

    }

    public void informacionUsuario() {
        JSONObject jsonData = new JSONObject();
        try {
            jsonData.put("tableName","informacionUsuario");
            jsonData.put("nombre", String.valueOf(tNombre.getText()));
            jsonData.put("apellidos",String.valueOf(tApellido.getText()));
            jsonData.put("telefono",String.valueOf(tTelefono.getText()) );
        } catch (JSONException e) {
            Toast toast= Toast. makeText(getApplicationContext(),
                    "Local: Error al intentar añadir la informacion del usuario.",Toast. LENGTH_SHORT);
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
                    "Server: Error al intentar añadir la informacion del usuario. ",Toast. LENGTH_SHORT);
            toast. setMargin(50,50);
            toast. show();
        }
    }

    public void abrirInicioSesion() {
        Intent i = new Intent(this, InicioSesion.class);
        startActivity(i);
    }
}

