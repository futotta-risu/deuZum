package com.example.deuzumrehacer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class CrearUsuario1 extends AppCompatActivity {
    public class CrearUsuario1 extends AppCompatActivity {

        private EditText tCorreo;
        private EditText tNombre;
        private EditText tApellido;
        private EditText tTelefono;
        private EditText tDireccion;
        private EditText dFechaNacimiento;
        private Spinner sGenero;
        private EditText nUsuario;
        private EditText cUsuario;
        private Spinner sPreguntaSeguridad;
        private EditText tRespuestaSeguridad;
        private Button bSiguiente;
        private Button bAtras;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_crear_usuario1);

            tCorreo = findViewById(R.id.textoCorreoElectronico);
            tNombre = findViewById(R.id.textoNombre);
            tApellido = findViewById(R.id.textoApellido);
            tTelefono = findViewById(R.id.textoTelefono);
            tDireccion = findViewById(R.id.textoDireccion);
            dFechaNacimiento = findViewById(R.id.fechaFechaNacimiento);
            sGenero = findViewById(R.id.spinnerGenero);
            nUsuario = findViewById(R.id.textoNombreUsuario);
            cUsuario =  findViewById(R.id.contrasenyaContrasenya);
            sPreguntaSeguridad =  findViewById(R.id.spinnerPreguntaSeguridad);
            tRespuestaSeguridad =  findViewById(R.id.textoRespuestaSeguridad);
            bSiguiente = findViewById(R.id.botonCrear);
            bAtras = findViewById(R.id.botonAtrasCU1);

            String[] opciones = {"Hombre","Mujer","Otro"};

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, opciones);
            sGenero.setAdapter(adapter);

            bSiguiente.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    abrirCrearUsuario2();
                }
            });

            bAtras.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    abrirInicioSesion();
                }
            });
        }

        public void abrirCrearUsuario2() {
            Intent i = new Intent(this, CrearUsuario2.class);
            startActivity(i);
        }

        public void abrirInicioSesion() {
            Intent i = new Intent(this, InicioSesion.class);
            startActivity(i);
        }
    }

