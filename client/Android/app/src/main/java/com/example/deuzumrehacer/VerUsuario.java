package com.example.deuzumrehacer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class VerUsuario extends AppCompatActivity {

    private TextView cVerUsuario;
    private EditText tNombreUsuario;
    private EditText tTelefono;
    private EditText tEmail;
    private EditText tDireccion;
    private EditText cContrasenya;
    private Button bGuardarCambios;
    private Button bAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_usuario);

        cVerUsuario = findViewById(R.id.cajaVerUsuario);
        tNombreUsuario = findViewById(R.id.textoNombreUsuario);
        tTelefono = findViewById(R.id.textoTelefono);
        tEmail = findViewById(R.id.textoEmail);

    }
}
