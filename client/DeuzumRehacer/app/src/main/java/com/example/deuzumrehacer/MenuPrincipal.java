package com.example.deuzumrehacer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuPrincipal extends AppCompatActivity {

    private TextView cQQH;
    private Button hTransaccion;
    private Button vUsuario;
    private Button mCuentas;
    private Button ayudaFAQ;
    private Button bAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        cQQH = findViewById(R.id.textoQQH);
        hTransaccion = findViewById(R.id.botonEnviarDinero);
        vUsuario = findViewById(R.id.botonVerUsuario);
        mCuentas = findViewById(R.id.botonGestionarTarjetas);
        ayudaFAQ = findViewById(R.id.botonAyudaFAQ);
        bAtras = findViewById(R.id.botonAtrasMP);

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

        bAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirInicioSesion();
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
        Intent i = new Intent(this, MiCuenta.class);
        startActivity(i);
    }

    public void abrirAyudaFAQ() {
        Intent i = new Intent(this, AyudaFAQ.class);
        startActivity(i);
    }

    public void abrirInicioSesion() {
        Intent i = new Intent(this, InicioSesion.class);
        startActivity(i);
    }
}

