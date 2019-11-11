package com.example.deuzum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MiCuenta extends AppCompatActivity {
    private TextView cMiCuenta;
    private Button bAtras;
    private Button bAñadirCuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_cuenta);

        cMiCuenta = findViewById(R.id.cajaMiCuenta);
        bAtras = findViewById(R.id.botonAtrasMC);
        bAñadirCuenta = findViewById(R.id.botonAñadirCuenta);

        bAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirMenuPrincipal();
            }
        });
    }

    public void abrirMenuPrincipal() {
        Intent i = new Intent(this, MenuPrincipal.class);
        startActivity(i);
    }
}
