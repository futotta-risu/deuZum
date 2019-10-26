package com.example.deuzum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuPrincipal extends AppCompatActivity {

    private TextView cMenuPrincip;
    private Button hTransaccion;
    private Button vUsuario;
    private Button mCuentas;
    private Button ayudaFAQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        cMenuPrincip = (TextView) findViewById(R.id.cajaMenuPrincipal);
        hTransaccion = (Button)findViewById(R.id.botonHacerTrans);
        vUsuario = (Button)findViewById(R.id.botonVerUsuario);
        mCuentas = (Button)findViewById(R.id.botonMisCuentas);
        ayudaFAQ = (Button)findViewById(R.id.botonAyudaFAQ);

        hTransaccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirHacerTransaccion();
            }
        });

        vUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirVerUsuario();
            }
        });

        mCuentas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirMisCuentas();
            }
        });

        ayudaFAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirAyudaFAQ();
            }
        });

    }

    public void abrirHacerTransaccion() {
        Intent i = new Intent(this, HacerTransaccion.class);
        startActivity(i);
    }

    public void abrirVerUsuario() {
        Intent i = new Intent(this, VerUsuario.class);
        startActivity(i);
    }

    public void abrirMisCuentas() {
        Intent i = new Intent(this, MisCuentas.class);
        startActivity(i);
    }

    public void abrirAyudaFAQ() {
        Intent i = new Intent(this, AyudaFAQ.class);
        startActivity(i);
    }
}
