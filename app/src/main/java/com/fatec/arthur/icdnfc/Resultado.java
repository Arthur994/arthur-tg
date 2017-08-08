package com.fatec.arthur.icdnfc;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.fatec.arthur.icdnfc.database.BancoDados;

public class Resultado extends AppCompatActivity {

    private BancoDados bd;

    // TODO: 08/08/2017 Receber lista de string com os codigos; 

    // TODO: 08/08/2017 manipular e fazer a busca

    // TODO: 08/08/2017 Exibir resultados

/*
    public String ToStringCap(Cursor curcap){
        return "Capítulo: " + curcap.getString(1);
    }

    public String ToStringBlock(Cursor curblock){
        return "Bloco: " + curblock.getString(3);
    }

    public String ToStringCod(Cursor curcod){
        return "CID: " + curcod.getString(7) +
                "Nome: " + curcod.getString(8);
    }
*/

     public String ToString(Cursor curcod,Cursor curcap,Cursor curblock){
         return "CID: " + curcod.getString(7) +
                 "Nome: " + curcod.getString(8) +
                 "Bloco: " + curblock.getString(3) +
                 "Capítulo: " + curcap.getString(1);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        ListView resultados = (ListView) findViewById(R.id.resultado);
    }
}
