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
    private Button ajustes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        cQQH = findViewById(R.id.textoQQH);
        eDinero = findViewById(R.id.botonEnviarDinero);
        vUsuario = findViewById(R.id.botonVerUsuario);
        gTarjetas = findViewById(R.id.botonGestionarTarjetas);
        actividad = findViewById(R.id.botonActividad);
        ajustes = findViewById(R.id.botonAjustes);


        /*
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

        gTarjetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirGestionarTarjetas();
            }
        });

         */

    }

    /*
    public void abrirEnviarDinero() {
        Intent i = new Intent(this, HacerTransaccion.class);
        startActivity(i);
    }

    public void abrirVerUsuario() {
        Intent i = new Intent(this, VerUsuario.class);
        startActivity(i);
    }

    public void abrirGestionarTarjetas() {
        Intent i = new Intent(this, MiCuenta.class);
        startActivity(i);
    }
     */

}

