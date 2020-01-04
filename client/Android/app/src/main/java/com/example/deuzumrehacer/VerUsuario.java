package com.example.deuzumrehacer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.FileUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Logger;

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
        tDireccion = findViewById(R.id.textoDireccion);
        cContrasenya = findViewById(R.id.editarContrasenya);
        bGuardarCambios = findViewById(R.id.botonGuardarCambios);
        bAtras = findViewById(R.id.botonAtrasVU);


        Thread myThread = new Thread(new MyServerThread());
        myThread.start();

        bAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirMenuPrincipal();
            }
        });

        bGuardarCambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarCambios();
            }
        });

    }

    public void guardarCambios() {
        MessageSender ms = new MessageSender();
        ms.execute(tNombreUsuario.getText().toString(), tTelefono.getText().toString(), tEmail.getText().toString(), tDireccion.getText().toString(),
                cContrasenya.getText().toString());

        Intent i = new Intent(this, MenuPrincipal.class);
        startActivity(i);

        try {
            Activity activity = new Activity();
            File file = new File("java/exitoVerUsuario.txt");
            FileInputStream is = new FileInputStream(file);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String text = new String(buffer);

            Toast.makeText(activity,text,Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void abrirMenuPrincipal() {
        Intent i = new Intent(this, MenuPrincipal.class);
        startActivity(i);
    }
}
