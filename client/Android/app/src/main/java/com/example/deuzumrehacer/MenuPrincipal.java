package com.example.deuzumrehacer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuPrincipal extends AppCompatActivity {

    private TextView cQQH;
    private Button eDinero;
    private Button vUsuario;
    private Button gTarjetas;
    private Button actividad;
    private Button cerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        cQQH = findViewById(R.id.cajaQQH);
        eDinero = findViewById(R.id.botonEnviarDinero);
        vUsuario = findViewById(R.id.botonVerUsuario);
        gTarjetas = findViewById(R.id.botonGestionarTarjetas);
        actividad = findViewById(R.id.botonActividad);
        cerrarSesion = findViewById(R.id.botonCerrarSesion);


        eDinero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirEnviarDinero();
            }
        });

        vUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirVerUsuario();
            }
        });
/*
        gTarjetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirGestionarTarjetas();
            }
        });

        actividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirActividad();
            }
        });
*/
        cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirInicioSesion();
            }
        });

    }

    public void abrirEnviarDinero() {
        Intent i = new Intent(this, EnviarDinero.class);
        startActivity(i);
    }

    public void abrirVerUsuario() {
        Intent i = new Intent(this, VerUsuario.class);
        startActivity(i);
    }
/*
    public void abrirGestionarTarjetas() {
        Intent i = new Intent(this, GestionarTarjetas.class);
        startActivity(i);
    }

    public void abrirActividad() {
        Intent i = new Intent(this, Actividad.class);
        startActivity(i);
    }
*/
    public void abrirInicioSesion() {
        Intent i = new Intent(this, InicioSesion.class);
        startActivity(i);
    }

}

