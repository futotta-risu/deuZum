package com.example.deuzum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class CrearUsuario1 extends AppCompatActivity {

    private TextView cCrearUsuario;
    private EditText tCorreo;
    private EditText tNombre;
    private EditText tApellido;
    private EditText tTelefono;
    private EditText tDireccion;
    private EditText dFechaNacimiento;
    private Spinner sGenero;
    private Button bSiguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario_1);

        cCrearUsuario = (TextView) findViewById(R.id.cajaCrearUsuario);
        tCorreo = (EditText) findViewById(R.id.textoCorreoElectronico);
        tNombre = (EditText) findViewById(R.id.textoNombre);
        tApellido = (EditText) findViewById(R.id.textoApellido);
        tTelefono = (EditText) findViewById(R.id.textoTelefono);
        tDireccion = (EditText) findViewById(R.id.textoDireccion);
        dFechaNacimiento = (EditText) findViewById(R.id.fechaFechaNacimiento);
        sGenero = (Spinner) findViewById(R.id.spinnerGenero);
        bSiguiente = (Button) findViewById(R.id.botonSiguiente);

        String[] opciones = {"Hombre","Mujer","Otro"};

        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, opciones);
        sGenero.setAdapter(adapter);

        bSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirCrearUsuario2();
            }
        });
    }

    public void abrirCrearUsuario2() {
        Intent i = new Intent(this, CrearUsuario2.class);
        startActivity(i);
    }
}
