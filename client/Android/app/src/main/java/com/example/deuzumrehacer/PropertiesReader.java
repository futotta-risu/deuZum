package com.example.deuzumrehacer;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    private Context context;
    private Properties properties;

    public PropertiesReader(Context context){
        this.context=context;
        properties = new Properties();
    }

    public Properties getMyProperties(String file){
        try{
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open(file);
            properties.load(inputStream);

        }catch (Exception e){
            System.out.print(e.getMessage());
        }

        return properties;
    }
}
