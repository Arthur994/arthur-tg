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
    // TODO: 31/07/2017 Fazer manipução de arquivos para os outros tipos do BD
    List<String> blocos = new ArrayList<>();
    List<String> codigos = new ArrayList<>();


    public ManipularArquivos(Context myContext) {
        AssetManager mngr = myContext.getAssets();

        InputStream is = null;

        try {
            is = mngr.open("arquivosOms/chapters.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String linha;

            while ((linha = reader.readLine()) != null)
                capitulos.add(linha);

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
