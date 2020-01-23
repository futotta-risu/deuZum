package com.example.deuzumrehacer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InicioSesion extends AppCompatActivity {

    private EditText nUsuario;
    private EditText cUsuario;
    private TextView tLogin;
    private Button bIni;
    private Button bReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        nUsuario = findViewById(R.id.nomUsuario);
        cUsuario = findViewById(R.id.contrUsuario);
        tLogin = findViewById(R.id.textoLogin);
        bIni = findViewById(R.id.botonIni);
        bReg = findViewById(R.id.botonRegis);

        Logger logger = Logger.getLogger(InicioSesion.class.getName());
        logger.log(Level.INFO, "1");
        logger.log(Level.WARNING, "2");

        bIni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirCrearUsuario2();
            }
        });

        bReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirCrearUsuario1();
            }
        });
    }



    public void comprobarUsuarioContraseña() {

        JSONObject data = new JSONObject();
        try{
            data.put("tableName","usuario");
            data.put("user",nUsuario.getText().toString());
            data.put("pass",cUsuario.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Thread t1 = new Thread(new MessageSender("logUser", data));
        t1.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(ServerRespond.result.equals("1")){
            Toast toast= Toast. makeText(getApplicationContext(),"Conectado:"+ServerRespond.result,Toast. LENGTH_SHORT);
            toast. setMargin(50,50);
            toast. show();
            abrirCrearUsuario2();
        }else{
            Toast toast= Toast. makeText(getApplicationContext(),"Contraseña equivocada:"+ServerRespond.result,Toast. LENGTH_SHORT);
            toast. setMargin(50,50);
            toast. show();
        }

    }

    public void abrirCrearUsuario1() {
        Intent i = new Intent(this, CrearUsuario.class);
        startActivity(i);
    }
    public void abrirCrearUsuario2() {
        Intent i = new Intent(this, MenuPrincipal.class);
        startActivity(i);
    }
}
