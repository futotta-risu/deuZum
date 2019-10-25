package com.example.deuzum;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class CrearUsuario1 extends AppCompatActivity {

    private TextView cCrearUsuario;
    private EditText nCorreo;
    private EditText nNombre;
    private EditText nApellido;
    private EditText nTelefono;
    private EditText nDireccion;
    private EditText dFechaNacimiento;
    private Spinner sGenero;
    private Button bSiguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario_1);

        cCrearUsuario = (TextView) findViewById(R.id.cajaCrearUsuario);
        nCorreo = (EditText) findViewById(R.id.textoCorreoElectronico);
        nNombre = (EditText) findViewById(R.id.textoNombre);
        nApellido = (EditText) findViewById(R.id.textoApellido);
        nTelefono = (EditText) findViewById(R.id.textoTelefono);
        nDireccion = (EditText) findViewById(R.id.textoDireccion);
        dFechaNacimiento = (EditText) findViewById(R.id.fechaFechaNacimiento);
        sGenero = (Spinner) findViewById(R.id.spinnerGenero);
        bSiguiente = (Button) findViewById(R.id.botonSiguiente);

        String[] opciones = {"Hombre","Mujer","Otro"};

        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, opciones);
        sGenero.setAdapter(adapter);
    }
}
