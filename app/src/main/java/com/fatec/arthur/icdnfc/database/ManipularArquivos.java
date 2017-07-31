package com.fatec.arthur.icdnfc.database;


import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arthur on 30/07/2017.
 */

public class ManipularArquivos {

    public ManipularArquivos(Context myContext) {
        AssetManager mngr = myContext.getAssets();

/*        String text = "";
        try {
            InputStream is = mngr.open("arquivosOms/chapters.txt");
            int tamanho = is.available();
            byte[] buffer = new byte[tamanho];
            is.read(buffer);
            is.close();
            text = new String(buffer);

        } catch (IOException e) {
            e.printStackTrace();
        }*/


        InputStream is = null;
        List<String> capitulos = new ArrayList<>();
        try {
            is = mngr.open("arquivosOms/chapters.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String linha;

            while ((linha = reader.readLine()) != null)
                capitulos.add(linha);

        } catch (IOException e){
            e.printStackTrace();
        }

        for (String c : capitulos){
            // TODO: 31/07/2017 Cortar string pra inserção no banco, necessario converter para os devidos tipos; ex int/string exemplo(02;Neoplasms);
            //System.out.println("CAPITULOS: " + c);
        }


    }

}
