package com.fatec.arthur.icdnfc;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fatec.arthur.icdnfc.database.BancoDados;

public class MainActivity extends AppCompatActivity {

    public void teste(){

        BancoDados bd = new BancoDados(this);

        //teste inserção
        bd.addCapitulos();
        bd.addBlocos();
        bd.addCodigos();

        //teste seleção - Capitulos
        Cursor cursor = bd.selecionarTodosCapituos();
        System.out.print("teste --- selecionarTodosCapitulos id: " + cursor.getString(0) + " Nome: " + cursor.getString(1));

        //teste seleção unitaria - Capitulos
        Cursor cursor1 = bd.selecionarCapByID(1);
        System.out.print("teste --- selecionarCapById id: " + cursor1.getString(0) + " Nome: " + cursor1.getString(1));

        //teste seleção unitaria - Blocos
        Cursor cursor2 = bd.selecionarBlocoByID("A00");
        System.out.print("teste --- selecionarBlocoById: Start  " + cursor2.getString(0) + " Final " + cursor2.getString(1) + " FK " + cursor2.getString(2) + " Nome: " + cursor2.getString(3));

        //teste seleção - Blocos
        Cursor cursor3 = bd.selecionarTodosBlocos();
        System.out.print("teste --- selecionarBlocoById: Start " + cursor3.getString(0) + " Final " + cursor3.getString(1) + " FK " + cursor3.getString(2) + " Nome: " + cursor3.getString(3));

        //teste seleção unitaria - Codigos
        Cursor cursor4 = bd.selecionarCodByID("A00");
        //ToDo Escrever a parada toda
        System.out.println("Teste --- selecionarCodById " + cursor4.getString(0));


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    teste();

    }
}
