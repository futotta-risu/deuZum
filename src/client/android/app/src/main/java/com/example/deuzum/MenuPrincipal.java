package com.example.deuzum;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
    }
}
