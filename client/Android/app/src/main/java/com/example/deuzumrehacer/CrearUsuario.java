package com.example.deuzumrehacer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class CrearUsuario extends AppCompatActivity {

    private EditText tCorreo;
    private EditText tNombre;
    private EditText tApellido;
    private EditText tTelefono;
    private EditText tDireccion;
    private EditText dFechaNacimiento;
    private Spinner sGenero;
    private EditText nUsuario;
    private EditText cUsuario;
    private Spinner sPreguntaSeguridad;
    private EditText tRespuestaSeguridad;
    private Button bSiguiente;
    private Button bAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);

        tCorreo = findViewById(R.id.textoCorreoElectronico);
        tNombre = findViewById(R.id.textoNombre);
        tApellido = findViewById(R.id.textoApellido);
        tTelefono = findViewById(R.id.textoTelefono);
        tDireccion = findViewById(R.id.textoDireccion);
        dFechaNacimiento = findViewById(R.id.fechaFechaNacimiento);
        sGenero = findViewById(R.id.spinnerGenero);
        nUsuario = findViewById(R.id.textoNombreUsuario);
        cUsuario = findViewById(R.id.contrasenyaContrasenya);
        sPreguntaSeguridad = findViewById(R.id.spinnerPreguntaSeguridad);
        tRespuestaSeguridad = findViewById(R.id.textoRespuestaSeguridad);
        bSiguiente = findViewById(R.id.botonCrear);
        bAtras = findViewById(R.id.botonAtrasCU1);

        String[] opciones1 = {"Hombre", "Mujer", "Otro"};

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones1);
        sGenero.setAdapter(adapter1);

        String[] opciones2 = {"¿Donde cursaste la educación primaria?", "¿Como se llama tú heman@ pequeñ@?", "¿Como se llamaba tu primera mascota?"};

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones2);
        sPreguntaSeguridad.setAdapter(adapter2);

        try {
            tNombre.setText(Util.getProperty("nombre",getApplicationContext()));
            tApellido.setText(Util.getProperty("apellido",getApplicationContext()));
            bSiguiente.setText(Util.getProperty("siguiente",getApplicationContext()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        bSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearUsuario();
            }
        });

        bAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirInicioSesion();
            }
        });
    }

    public void crearUsuario(){
        try{
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/bizum_bd", "root", "");
            PreparedStatement pst = cn.prepareStatement("insert into usuario values(?,?,?,?,?,?,?,?,?)");

            pst.setString(1, "0");
            pst.setString(2, String.valueOf(nUsuario.getText()));
            pst.setString(3, String.valueOf(cUsuario.getText()));
            pst.setString(4, String.valueOf(tNombre.getText()));
            pst.setString(5, String.valueOf(tApellido.getText()));
            pst.setString(6, String.valueOf(tCorreo.getText()));
            pst.setString(7, String.valueOf(tTelefono.getText()));
            pst.setString(8, sPreguntaSeguridad.getSelectedItem().toString().trim());
            pst.setString(9, String.valueOf(tRespuestaSeguridad.getText()));

            pst.executeUpdate();

            nUsuario.setText("");
            cUsuario.setText("");
            tNombre.setText("");
            tApellido.setText("");
            tCorreo.setText("");
            tTelefono.setText("");

            tRespuestaSeguridad.setText("");

            Intent i = new Intent(this, MenuPrincipal.class);
            startActivity(i);

        }catch (Exception e){
            e.printStackTrace();
        }



    }
    public void abrirInicioSesion() {
        Intent i = new Intent(this, InicioSesion.class);
        startActivity(i);
    }
}

