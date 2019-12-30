package com.example.deuzumrehacer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GestionarTarjetas extends AppCompatActivity {

    TextView cGestionarTarjeta;
    EditText tNumeroTarjeta;
    EditText cCVV;
    EditText tFechaCaducidad;
    Button bAtras;
    Button bGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionar_tarjetas);

        cGestionarTarjeta = findViewById(R.id.cajaGestionarTarjeta);
        tNumeroTarjeta = findViewById(R.id.textoNumeroTarjeta);
        cCVV = findViewById(R.id.contrasenyaCVV);
        tFechaCaducidad = findViewById(R.id.textoFechaCaducidad);
        bAtras = findViewById(R.id.botonAtrasGT);
        bGuardar = findViewById(R.id.botonGuardar);

        Thread myThread = new Thread(new MyServerThread());
        myThread.start();

        bAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirMenuPrincipal();
            }
        });

        bGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar();
            }
        });
    }

    public void guardar() {
        MessageSender ms = new MessageSender();
        ms.execute(tNumeroTarjeta.getText().toString(), cCVV.getText().toString(), tFechaCaducidad.getText().toString());

        Intent i = new Intent(this, MenuPrincipal.class);
        startActivity(i);
    }

    public void abrirMenuPrincipal() {
        Intent i = new Intent(this, MenuPrincipal.class);
        startActivity(i);
    }
}
