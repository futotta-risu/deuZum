package com.example.deuzumrehacer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class InicioSesion extends AppCompatActivity {

    private ImageView lDeuzum;
    private EditText nUsuario;
    private EditText cUsuario;
    private Button bIni;
    private Button bReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        lDeuzum = findViewById(R.id.iconoDeuzum);
        nUsuario = findViewById(R.id.nomUsuario);
        cUsuario = findViewById(R.id.contrUsuario);
        bIni = findViewById(R.id.botonIni);
        bReg = findViewById(R.id.botonRegis);

        bIni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirMenuPrincipal();
            }
        });

        bReg.setOnClickListener(new View.OnClickListener() {
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
        Intent i = new Intent(this, CrearUsuario.class);
        startActivity(i);
    }
}
