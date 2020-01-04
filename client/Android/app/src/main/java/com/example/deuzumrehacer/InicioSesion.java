package com.example.deuzumrehacer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
                comprobarUsuarioContraseña();
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

        try{
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/bizum_bd", "root", "");
            PreparedStatement pst = cn.prepareStatement("select * from usuario where User = ?");
            pst.setString(1, String.valueOf(nUsuario.getText()));

            ResultSet rs = pst.executeQuery();

            if(rs.next()){
                String temp_pass = rs.getString("Pass");

                if (temp_pass.contentEquals(cUsuario.getText())){
                    Intent i = new Intent(this, MenuPrincipal.class);
                    startActivity(i);

                }else {
                    tLogin.setText("Contraseña incorrecta");
                }


            } else {
                tLogin.setText("Usuario no registrado.");
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }


    }

    public void abrirCrearUsuario1() {
        Intent i = new Intent(this, CrearUsuario.class);
        startActivity(i);
    }
}
