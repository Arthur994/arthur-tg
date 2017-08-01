package com.fatec.arthur.icdnfc.database;


import android.content.Context;
import android.content.res.AssetManager;

import com.fatec.arthur.icdnfc.MainActivity;

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

    List<String> capitulos = new ArrayList<>();
    List<String> blocos = new ArrayList<>();
    List<String> codigos = new ArrayList<>();


    public ManipularArquivos(Context myContext) {
        AssetManager mngr = myContext.getAssets();

        InputStream is = null;

        //capitulos
        try {
            is = mngr.open("arquivosOms/chapters.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String linha;

            while ((linha = reader.readLine()) != null)
                capitulos.add(linha);

        } catch (IOException e){
            e.printStackTrace();
        }

        //blocos
        try {
            is = mngr.open("arquivosOms/blocks.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String linha;

            while ((linha = reader.readLine()) != null)
                blocos.add(linha);

        } catch (IOException e){
            e.printStackTrace();
        }

        // TODO: 01/08/2017 Fazer tratamento dos codigos.
        //codigos
        try {
            is = mngr.open("arquivosOms/codes.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String linha;

            while ((linha = reader.readLine()) != null)
                codigos.add(linha);

        } catch (IOException e){
            e.printStackTrace();
        }
        

    }

    public List<String> getCapitulos() {
        return capitulos;
    }

    public List<String> getBlocos() {
        return blocos;
    }

    public List<String> getCodigos() {
        return codigos;
    }

}
