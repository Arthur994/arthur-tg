package com.fatec.arthur.icdnfc;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fatec.arthur.icdnfc.database.BancoDados;
import com.fatec.arthur.icdnfc.database.ManipularArquivos;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public void PrimeiraExec() {
        boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("isFirstRun", true);
        if (isFirstRun){
            //Checa se é a primeira execução do aplicativo e chama outro metodo caso seja.
            popularBD();

            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("isFirstRun", false).apply();
        }
    }

    public void popularBD(){
        //Popula o BD
        BancoDados bd = new BancoDados(this);
        ManipularArquivos a = new ManipularArquivos(this);

        List<String> capitulos = a.getCapitulos();
        List<String> blocos = a.getBlocos();
        List<String> codigos = a.getCodigos();

        for (String c : capitulos) {
            String[] aux = c.split(";");
            bd.addCapitulos(Integer.parseInt(aux[0]),aux[1]);
        }

        for (String c : blocos) {
            String[] aux = c.split(";");
            bd.addBlocos(aux[0], aux[1], Integer.parseInt(aux[2]), aux[3]);
        }

        for (String c : codigos) {
            String[] aux = c.split(";");
            bd.addBlocos(Integer.parseInt(aux[0]),aux[1],aux[2],Integer.parseInt(aux[3]),aux[4],aux[5],aux[6],aux[7],aux[8],aux[9],aux[10],aux[11],aux[12],aux[13]);
        }
    }

    public void teste(BancoDados bd){

        //teste seleção unitaria - Capitulos
        Cursor cursor1 = bd.selecionarCapByID(12);
        System.out.println("teste capitulos --- selecionarCapById id: " + cursor1.getString(0) + " Nome: " + cursor1.getString(1));

        //teste seleção unitaria - Blocos
        Cursor cursor2 = bd.selecionarBlocoByID("Z80");
        System.out.println("teste --- selecionarBlocoById: Start  " + cursor2.getString(0) + " Final " + cursor2.getString(1) + " FK " + cursor2.getString(2) + " Nome: " + cursor2.getString(3));


        //teste seleção unitaria - Codigos
        Cursor cursor4 = bd.selecionarCodByID("A00");
        System.out.println("Teste --- selecionarCodById Col1" + cursor4.getString(0) + " Col2 " + cursor4.getString(1) + " Col3 " + cursor4.getString(2) + " cap_id " + cursor4.getString(3)
                + " Col5 " + cursor4.getString(4) + " Col6 " + cursor4.getString(5) + " Col7 " + cursor4.getString(6) + " Cod_id " + cursor4.getString(7) + " Cod_desc " + cursor4.getString(8)
                + " Col10 " + cursor4.getString(9) + " Co11 " + cursor4.getString(10) + " Col12 " + cursor4.getString(11) + " col13 " + cursor4.getString(12) + " Col14 " + cursor4.getString(13)
        );

        Cursor cursor5 = bd.selecionarCodByID("B182");
        System.out.println("Teste --- selecionarCodById Col1" + cursor5.getString(0) + " Col2 " + cursor5.getString(1) + " Col3 " + cursor5.getString(2) + " cap_id " + cursor5.getString(3)
                + " Col5 " + cursor5.getString(4) + " Col6 " + cursor5.getString(5) + " Col7 " + cursor5.getString(6) + " Cod_id " + cursor5.getString(7) + " Cod_desc " + cursor5.getString(8)
                + " Col10 " + cursor5.getString(9) + " Co11 " + cursor5.getString(10) + " Col12 " + cursor5.getString(11) + " col13 " + cursor5.getString(12) + " Col14 " + cursor5.getString(13)
        );

        Cursor cursor6 = bd.selecionarCodByID("U801");
        System.out.println("Teste --- selecionarCodById Col1" + cursor6.getString(0) + " Col2 " + cursor6.getString(1) + " Col3 " + cursor6.getString(2) + " cap_id " + cursor6.getString(3)
                + " Col5 " + cursor6.getString(4) + " Col6 " + cursor6.getString(5) + " Col7 " + cursor6.getString(6) + " Cod_id " + cursor6.getString(7) + " Cod_desc " + cursor6.getString(8)
                + " Col10 " + cursor6.getString(9) + " Co11 " + cursor6.getString(10) + " Col12 " + cursor6.getString(11) + " col13 " + cursor6.getString(12) + " Col14 " + cursor6.getString(13)
        );

/*
        BancoDados bd = new BancoDados();

        //teste inserção
        bd.addCapitulos();
        bd.addBlocos();
        bd.addCodigos();

        //teste seleção - Capitulos
        Cursor cursor = bd.selecionarTodosCapituos();
        System.out.println("teste capitulos --- selecionarTodosCapitulos id: " + cursor.getString(0) + " Nome: " + cursor.getString(1));

        //teste seleção - Blocos
        Cursor cursor3 = bd.selecionarTodosBlocos();
        System.out.println("teste --- selecionarTodosBlocos: Start " + cursor3.getString(0) + " Final " + cursor3.getString(1) + " FK " + cursor3.getString(2) + " Nome: " + cursor3.getString(3));

        //teste seleção  - Codigos
        Cursor cursor5 = bd.selecionarTodosCodigos();
        System.out.println("Teste --- selecionarTodosCodigos Col1" + cursor5.getString(0) + " Col2 " + cursor5.getString(1) + " Col3 " + cursor5.getString(2) + " cap_id " + cursor5.getString(3)
                + " Col5 " + cursor5.getString(4) + " Col6 " + cursor5.getString(5) + " Col7 " + cursor5.getString(6) + " Cod_id " + cursor5.getString(7) + " Cod_desc " + cursor5.getString(8)
                + " Col10 " + cursor5.getString(9) + " Co11 " + cursor5.getString(10) + " Col12 " + cursor5.getString(11) + " col13 " + cursor5.getString(12) + " Col14 " + cursor5.getString(13)
        );
        */
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PrimeiraExec();

        List<String> teste = new ArrayList<String>();
        teste.add("A00");
        teste.add("B182");

        CodTag tag = new CodTag();
        tag.setCodigosNFC(teste);

        //Envia a lista cids para a tela resultado onde é feita a busca;

        //chamando a tela resultado.
        Intent resultado = new Intent(this, Resultado.class);
        startActivity(resultado);

    }

}
