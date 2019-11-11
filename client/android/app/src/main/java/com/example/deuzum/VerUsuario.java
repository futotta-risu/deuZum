package com.example.deuzum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class VerUsuario extends AppCompatActivity {
    private TextView cVerUsuario;
    private TextView cNombre;
    private TextView cNombreUsuario;
    private TextView cApellido;
    private TextView cApellidoUsuario;
    private TextView cTelefono;
    private TextView cTelefonoUsuario;
    private TextView cEmail;
    private TextView cEmailUsuario;
    private TextView cDireccion;
    private TextView cDireccionUsuario;
    private Button bAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_usuario);

        cVerUsuario = (TextView) findViewById(R.id.cajaVerUsuario);
        cNombre = (TextView) findViewById(R.id.cajaNombre);
        cNombreUsuario = (TextView) findViewById(R.id.cajaNombreUsuario);
        cApellido = (TextView) findViewById(R.id.cajaApellido);
        cApellidoUsuario = (TextView) findViewById(R.id.cajaApellidoUsuario);
        cTelefono = (TextView) findViewById(R.id.cajaTelefono);
        cTelefonoUsuario = (TextView) findViewById(R.id.cajaTelefonoUsuario);
        cEmail = (TextView) findViewById(R.id.cajaEmail);
        cEmailUsuario = (TextView) findViewById(R.id.cajaEmailUsuario);
        cDireccion = (TextView) findViewById(R.id.cajaDireccion);
        cDireccionUsuario = (TextView) findViewById(R.id.cajaDireccionUsuario);
        bAtras = findViewById(R.id.botonAtrasVU);

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
