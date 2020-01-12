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
    private EditText tEmail;
    private EditText tFechaNacimiento;
    private EditText tDireccion;
    private Spinner sSexo;
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
        tEmail = findViewById(R.id.textoEmail);
        tDireccion = findViewById(R.id.textoDireccion);
        tFechaNacimiento = findViewById(R.id.fechaNacimiento);
        sSexo = findViewById(R.id.spinnerSexo);
        sPreguntaSeguridad = findViewById(R.id.spinnerPreguntaSeguridad);
        tRespuestaSeguridad = findViewById(R.id.textoRespuestaSeguridad);
        bSiguiente = findViewById(R.id.botonCrear);
        bAtras = findViewById(R.id.botonAtrasCU1);

        ArrayList<String> opcionesP = new ArrayList<String>();

        opcionesP.add("¿Donde cursaste la educación primaria?");
        opcionesP.add("¿Como se llama tú heman@ pequeñ@?");
        opcionesP.add("¿Como se llamaba tu primera mascota?");

        ArrayAdapter<String> adapterP = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opcionesP);
        sPreguntaSeguridad.setAdapter(adapterP);

        ArrayList<String> opcionesF = new ArrayList<String>();

        opcionesF.add("Hombre");
        opcionesF.add("Mujer");
        opcionesF.add("otro");

        ArrayAdapter<String> adapterF = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opcionesF);
        sPreguntaSeguridad.setAdapter(adapterF);

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
            jsonData.put("usuario", String.valueOf(nUsuario.getText()));
            jsonData.put("pass",String.valueOf(cUsuario.getText()));
            jsonData.put("preg_seguridad", String.valueOf(sPreguntaSeguridad.getSelectedItemPosition()+1));
            jsonData.put("resp_seguridad",String.valueOf(tRespuestaSeguridad.getText()) );
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
            jsonData.put("tableName","infoUsuario");
            jsonData.put("nombre", String.valueOf(tNombre.getText()));
            jsonData.put("apellido",String.valueOf(tApellido.getText()));
            jsonData.put("telefono",String.valueOf(tTelefono.getText()) );
            jsonData.put("email", String.valueOf(tEmail.getText()));
            jsonData.put("direccion", String.valueOf(tDireccion.getText()));
            jsonData.put("fecha_nacimiento", String.valueOf(tFechaNacimiento.getText()));
            jsonData.put("sexo", String.valueOf(sSexo.getSelectedItemPosition()+1));
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

