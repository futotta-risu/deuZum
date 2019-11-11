package com.example.deuzum;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConexionSQLServer extends AppCompatActivity {
    private Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conexion_sql_server);

        boton = findViewById(R.id.button);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbConnect();
            }
        });
    }

    public List<String> dbConnect() {
        List<String> Db_list = new ArrayList<String>();
        try {
            String ConnectionString = "jdbc:jtds:sqlserver://";
            // Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection(ConnectionString);
            System.out.println("connected");
            Statement statement = conn.createStatement();
            String queryString = "select name from sys.databases";
            ResultSet rs = statement.executeQuery(queryString);
            while (rs.next()) {
                Db_list.add(rs.getString(1));
            }
        } catch (Exception e) {
            Db_list.add("Error");
            e.printStackTrace();
        }
        return Db_list;
    }
}
