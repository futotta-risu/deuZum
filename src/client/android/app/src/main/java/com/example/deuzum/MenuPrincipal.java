package com.example.deuzum;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MenuPrincipal extends AppCompatActivity {

    Button hTransaccion;
    Button vUsuario;
    Button mCuentas;
    Button ayudaFAQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        hTransaccion = findViewById(R.id.botonHacerTrans);
        vUsuario = findViewById(R.id.botonVerUsuario);
        mCuentas = findViewById(R.id.botonMisCuentas);
        ayudaFAQ = findViewById(R.id.botonAyudaFAQ);
    }
}
