package com.example.deuzumrehacer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EnviarDinero extends AppCompatActivity {

    private TextView cEnviarDinero;
    private EditText tUsuarioDestinatario;
    private EditText tImporte;
    private EditText tConcepto;
    private Button bFinalizar;
    private Button bAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_dinero);

        cEnviarDinero = findViewById(R.id.cajaEnviarDinero);
        tUsuarioDestinatario = findViewById(R.id.textoNombreDestinatario);
        tImporte = findViewById(R.id.numImporte);
        tConcepto = findViewById(R.id.textoConcepto);
        bFinalizar = findViewById(R.id.botonFinalizar);
        bAtras = findViewById(R.id.botonAtrasED);

        bFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirMenuPrincipal();
            }
        });

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
