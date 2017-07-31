package com.fatec.arthur.icdnfc;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fatec.arthur.icdnfc.database.BancoDados;
import com.fatec.arthur.icdnfc.database.ManipularArquivos;

public class MainActivity extends AppCompatActivity {

    public void teste(){

        BancoDados bd = new BancoDados(this);

        //teste inserção
        bd.addCapitulos();
        bd.addBlocos();
        bd.addCodigos();

        //teste seleção - Capitulos
        Cursor cursor = bd.selecionarTodosCapituos();
        System.out.println("teste --- selecionarTodosCapitulos id: " + cursor.getString(0) + " Nome: " + cursor.getString(1));

        //teste seleção unitaria - Capitulos
        Cursor cursor1 = bd.selecionarCapByID(1);
        System.out.println("teste --- selecionarCapById id: " + cursor1.getString(0) + " Nome: " + cursor1.getString(1));

        //teste seleção unitaria - Blocos
        Cursor cursor2 = bd.selecionarBlocoByID("A00");
        System.out.println("teste --- selecionarBlocoById: Start  " + cursor2.getString(0) + " Final " + cursor2.getString(1) + " FK " + cursor2.getString(2) + " Nome: " + cursor2.getString(3));

        //teste seleção - Blocos
        Cursor cursor3 = bd.selecionarTodosBlocos();
        System.out.println("teste --- selecionarBlocoById: Start " + cursor3.getString(0) + " Final " + cursor3.getString(1) + " FK " + cursor3.getString(2) + " Nome: " + cursor3.getString(3));

        //teste seleção unitaria - Codigos
        Cursor cursor4 = bd.selecionarCodByID("A00");
        System.out.println("Teste --- selecionarCodById Col1" + cursor4.getString(0) + " Col2 " + cursor4.getString(1) + " Col3 " + cursor4.getString(2) + " cap_id " + cursor4.getString(3)
                + " Col5 " + cursor4.getString(4) + " Col6 " + cursor4.getString(5) + " Col7 " + cursor4.getString(6) + " Cod_id " + cursor4.getString(7) + " Cod_desc " + cursor4.getString(8)
                + " Col10 " + cursor4.getString(9) + " Co11 " + cursor4.getString(10) + " Col12 " + cursor4.getString(11) + " col13 " + cursor4.getString(12) + " Col14 " + cursor4.getString(13)
        );

        //teste seleção unitaria - Codigos
        Cursor cursor5 = bd.selecionarTodosCodigos();
        System.out.println("Teste --- selecionarCodById Col1" + cursor5.getString(0) + " Col2 " + cursor5.getString(1) + " Col3 " + cursor5.getString(2) + " cap_id " + cursor5.getString(3)
                + " Col5 " + cursor5.getString(4) + " Col6 " + cursor5.getString(5) + " Col7 " + cursor5.getString(6) + " Cod_id " + cursor5.getString(7) + " Cod_desc " + cursor5.getString(8)
                + " Col10 " + cursor5.getString(9) + " Co11 " + cursor5.getString(10) + " Col12 " + cursor5.getString(11) + " col13 " + cursor5.getString(12) + " Col14 " + cursor5.getString(13)
        );
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        teste();

        // TODO: 31/07/2017 Essa chamada do manipular arquivos deve ser feita do banco de dados;
        ManipularArquivos a = new ManipularArquivos(this);

    }


}
