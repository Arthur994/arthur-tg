package com.fatec.arthur.icdnfc.Screens;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.fatec.arthur.icdnfc.CodTag;
import com.fatec.arthur.icdnfc.R;
import com.fatec.arthur.icdnfc.database.BancoDados;

import java.util.LinkedList;
import java.util.List;

public class Resultado extends AppCompatActivity {

    private BancoDados bd = new BancoDados(this);

    //Busca no banco de dados.
    public List<String> busca(List<String> lista){

        List<String> resultado = new LinkedList<String>();

        for(String c:lista){
            Cursor cod = bd.selecionarCodByID(c);
            Cursor block = bd.selecionarBlocoByID(cod.getString(4));
            Cursor cap = bd.selecionarCapByID(Integer.parseInt(cod.getString(3)));
            
            String aux = ToString(cod,cap,block);
            resultado.add(aux);
        }
        return resultado;
    }

     public String ToString(Cursor curcod,Cursor curcap,Cursor curblock){
         return "\nICD: " + curcod.getString(7) +
                 "\n\nDisease: " + curcod.getString(8) +
                 "\n\nCharpter: " + curcap.getString(1)+
                 "\n\nBlock: " + curblock.getString(3) + "\n";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);


        ListView resultados = (ListView) findViewById(R.id.resultado);

        //Obter lista de cids. Passar o parametro a ser buscado dentro do adapter com tag.getCodigosNFC()
        CodTag tag = new CodTag();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, busca(tag.getCodigosNFC()));
        resultados.setAdapter(adapter);
    }

    @Override
    public void onBackPressed(){
        Intent trocaTela = new Intent(this, MainActivity.class);
        startActivity(trocaTela);

        this.finish();
        super.onBackPressed();
    }

}
