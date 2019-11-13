package com.example.deuzum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class CrearUsuario2 extends AppCompatActivity {

    private TextView cCrearUsuario;
    private EditText nUsuario;
    private EditText cUsuario;
    private Spinner sPreguntaSeguridad;
    private EditText tRespuestaSeguridad;
    private Button bCrear;
    private Button bAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario_2);

        cCrearUsuario = (TextView) findViewById(R.id.cajaCrearUsuario);
        nUsuario = (EditText) findViewById(R.id.textoNombreUsuario);
        cUsuario = (EditText) findViewById(R.id.contraseñaContraseña);
        sPreguntaSeguridad = (Spinner) findViewById(R.id.spinnerPreguntaSeguridad);
        tRespuestaSeguridad = (EditText) findViewById(R.id.textoRespuestaSeguridad);
        bCrear = (Button) findViewById(R.id.botonFinalizar);
        bAtras = (Button) findViewById(R.id.botonAtrasCU2);


        String[] opciones = {"¿Cual fue el nombre de tu primera mascota?","¿En que colegio hiciste primaria?","¿Cual fue tu ciudad de nacimiento?","¿Cual es el nombre de tu hermano mayor?"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, opciones);
        sPreguntaSeguridad.setAdapter(adapter);

        bCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirMenuPrincipal();
            }
        });

        bAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirCrearUsuario1();
            }
        });
    }

    public void abrirMenuPrincipal() {
        Intent i = new Intent(this, MenuPrincipal.class);
        startActivity(i);
    }

    public void abrirCrearUsuario1() {
        Intent i = new Intent(this, CrearUsuario1.class);
        startActivity(i);
    }
}
