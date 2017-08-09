package com.fatec.arthur.icdnfc;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.fatec.arthur.icdnfc.database.BancoDados;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Resultado extends AppCompatActivity {

    private BancoDados bd = new BancoDados(this);

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
    // TODO: 08/08/2017 Receber lista de string com os codigos;

    // TODO: 08/08/2017 manipular e fazer a busca
    public List<String> busca(List<String> lista){

        List<String> resultado = new LinkedList<String>();

        for(String c:lista){
            Cursor cod = bd.selecionarCodByID(c);
            Cursor block = bd.selecionarBlocoByID(cod.getString(4));
            Cursor cap = bd.selecionarCapByID(Integer.parseInt(cod.getString(3)));
            
            String aux = ToString(cod,cap,block);
            resultado.add(aux);

            //System.out.println("teste: " + cod.getString(4));

        }
        return resultado;
    }

    // TODO: 08/08/2017 Exibir resultados


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

        //teste
        List<String> teste = new ArrayList<String>();
        teste.add("A00");
        teste.add("B182");

        //busca(teste);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, busca(teste));
        resultados.setAdapter(adapter);
    }
}
