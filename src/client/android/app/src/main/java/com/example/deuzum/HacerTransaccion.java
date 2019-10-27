package com.example.deuzum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HacerTransaccion extends AppCompatActivity {

    private TextView cHacerTransaccion;
    private EditText tUsuarioDestinatario;
    private EditText tImporte;
    private EditText tConcepto;
    private Button bFinalizar;
    private Button bAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hacer_transaccion);

        cHacerTransaccion = (TextView) findViewById(R.id.cajaHacerTransaccion);
        tUsuarioDestinatario = (EditText) findViewById(R.id.textoNombreDestinatario);
        tImporte = (EditText) findViewById(R.id.numImporte);
        tConcepto = (EditText) findViewById(R.id.textoConcepto);
        bFinalizar = (Button) findViewById(R.id.botonFinalizar);
        bAtras = (Button) findViewById(R.id.botonAtrasHT);

        bFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirMenuPrincipal1();
            }
        });

        bAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirMenuPrincipal2();
            }
        });
    }

    public void abrirMenuPrincipal1() {
        Intent i = new Intent(this, MenuPrincipal.class);
        startActivity(i);
    }

    public void abrirMenuPrincipal2() {
        Intent i = new Intent(this, MenuPrincipal.class);
        startActivity(i);
    }

}
