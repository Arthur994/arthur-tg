package com.fatec.arthur.icdnfc.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Arthur on 09/06/2017.
 */

public class BancoDados extends SQLiteOpenHelper {

    private static final int  VERSAO = 1;
    private static final String NOME = "tgbd";

    //variaveis tabela capitulo
    private static final String CAPITULO = "capitulo";
    private static final String CAP_ID = "cap_id";
    private static final String CAP_NOME = "cap_nome";
    //variaveis tabela bloco
    private static final String BLOCO = "bloco";
    private static final String BL_ID_START = "bl_id_start";
    private static final String BL_ID_END = "bl_id_end";
    private static final String FK_CAP_ID = "cap_id";
    private static final String BL_NOME = "bl_nome";
    //variaveis tabela codigo
    private static final String CODIGO = "codigo";
    private static final String COD_COL1 = "col1";
    private static final String COD_COL2 = "col2";
    private static final String COD_COL3 = "col3";
    private static final String COD_CAPID = "cap_id";
    private static final String FK_BL_ID_START = "bl_id_start";
    private static final String COD_COL6 = "col6";
    private static final String COD_COL7 = "col7";
    private static final String COD_ID = "cod_id";
    private static final String COD_DESC = "cod_desc";
    private static final String COD_COL10 = "col10";
    private static final String COD_COL11 = "col11";
    private static final String COD_COL12 = "col12";
    private static final String COD_COL13 = "col13";
    private static final String COD_COL14 = "col14";




    //Cria a database
    public BancoDados(Context context) {
        super(context, NOME, null, VERSAO);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {

        //Cria a tabela capitulo
        String tb_capitulo = "CREATE TABLE " + CAPITULO + "("
                + CAP_ID + " INT(2) UNIQUE PRIMARY KEY, "
                + CAP_NOME + " VARCHAR(150))";
        db.execSQL(tb_capitulo);

        //Cria a tabela bloco
        String tb_bloco = "CREATE TABLE " + BLOCO + "("
                + BL_ID_START + " VARCHAR(3) PRIMARY KEY, "
                + BL_ID_END + " VARCHAR(3), "
                + FK_CAP_ID + " INT(2), "
                + BL_NOME + " VARCHAR(100), CONSTRAINT FK_BLOCOS FOREIGN KEY (CAP_ID) REFERENCES CAPITULOS(CAP_ID))";
        db.execSQL(tb_bloco);

        //Cria a tabela
        String tb_codigo = "CREATE TABLE " + CODIGO + "("
                + COD_COL1 + " INT(1) DEFAULT NULL, "
                + COD_COL2 + " VARCHAR(2) DEFAULT NULL, "
                + COD_COL3 + " VARCHAR(1) DEFAULT NULL, "
                + COD_CAPID + " INT(2) DEFAULT NULL, "
                + FK_BL_ID_START + " VARCHAR(3) DEFAULT NULL, "
                + COD_COL6 + " VARCHAR(6) DEFAULT NULL, "
                + COD_COL7 + " VARCHAR(5) DEFAULT NULL, "
                + COD_ID + " VARCHAR(4) PRIMARY KEY, "
                + COD_DESC + " VARCHAR(185) DEFAULT NULL, "
                + COD_COL10 + " VARCHAR(5) DEFAULT NULL, "
                + COD_COL11 + " VARCHAR(5) DEFAULT NULL, "
                + COD_COL12 + " VARCHAR(5) DEFAULT NULL, "
                + COD_COL13 + " VARCHAR(5) DEFAULT NULL, "
                + COD_COL14 + " VARCHAR(5) DEFAULT NULL, CONSTRAINT FK_COD FOREIGN KEY (CAP_ID) REFERENCES CAPITULOS(CAP_ID), CONSTRAINT FK_CODIGOS2 FOREIGN KEY (bl_id_start) REFERENCES blocos(bl_id_start))";
        db.execSQL(tb_codigo);

        // TODO: 31/07/2017 popular o banco a partir daqui 
        //popula o banco.
        /*
        this.addCapitulos();
        this.addBlocos();
        */

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /*CRUD*/

    //adicionar capitulos
    public void addCapitulos(int id, String cap){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(CAP_ID,id);
        values.put(CAP_NOME,cap);

        db.insert(CAPITULO, null, values);
        db.close();
    }
    //adicionar blocos
    public void addBlocos(String id_start, String id_end, int fk_cap_id, String nome){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(BL_ID_START,id_start);
        values.put(BL_ID_END,id_end);
        values.put(FK_CAP_ID,fk_cap_id);
        values.put(BL_NOME,nome);

        db.insert(BLOCO, null, values);
        db.close();
    }
    //adicionar codigos
    public void addBlocos(int i, String s, String s1, int i1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9, String s10, String s11){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COD_COL1, i);
        values.put(COD_COL2, s);
        values.put(COD_COL3, s1);
        values.put(COD_CAPID, i1);
        values.put(FK_BL_ID_START, s2);
        values.put(COD_COL6, s3);
        values.put(COD_COL7,s4);
        values.put(COD_ID,s5);
        values.put(COD_DESC, s6);
        values.put(COD_COL10, s7);
        values.put(COD_COL11, s8);
        values.put(COD_COL12, s9);
        values.put(COD_COL13, s10);
        values.put(COD_COL14, s11);


        db.insert(CODIGO, null, values);
        db.close();
    }
    
    //buscas capitulos
    public Cursor selecionarCapByID(int i){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] campos = {BancoDados.CAP_ID, BancoDados.CAP_NOME};

        Cursor cursor = db.query(CAPITULO, campos, CAP_ID + " = ?",
                new String[] {String.valueOf(i)}, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor selecionarTodosCapituos(){
        Cursor cursor;
        String[] campos = {BancoDados.CAP_ID, BancoDados.CAP_NOME};
        SQLiteDatabase db = this.getReadableDatabase();

        cursor = db.query(BancoDados.CAPITULO, campos, null, null, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    //Buscas Blocos
    public Cursor selecionarBlocoByID(String i){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] campos = {BancoDados.BL_ID_START, BancoDados.BL_ID_END, BancoDados.FK_CAP_ID, BancoDados.BL_NOME};

        Cursor cursor = db.query(BLOCO, campos, BL_ID_START + " = ?",
                new String[] {String.valueOf(i)}, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor selecionarTodosBlocos(){
        Cursor cursor;
        String[] campos = {BancoDados.BL_ID_START, BancoDados.BL_ID_END, BancoDados.FK_CAP_ID, BancoDados.BL_NOME};
        SQLiteDatabase db = this.getReadableDatabase();

        cursor = db.query(BancoDados.BLOCO, campos, null, null, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    //Buscas Codigos
    public Cursor selecionarCodByID(String i){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] campos = {BancoDados.COD_COL1, BancoDados.COD_COL2, BancoDados.COD_COL3, BancoDados.COD_CAPID, BancoDados.FK_BL_ID_START, BancoDados.COD_COL6, BancoDados.COD_COL7, BancoDados.COD_ID, BancoDados.COD_DESC, BancoDados.COD_COL10, BancoDados.COD_COL11, BancoDados.COD_COL12, BancoDados.COD_COL13, BancoDados.COD_COL14};

        Cursor cursor = db.query(CODIGO, campos, COD_ID + " = ?",
                new String[] {String.valueOf(i)}, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor selecionarTodosCodigos(){
        Cursor cursor;
        String[] campos = {BancoDados.COD_COL1, BancoDados.COD_COL2, BancoDados.COD_COL3, BancoDados.COD_CAPID, BancoDados.FK_BL_ID_START, BancoDados.COD_COL6, BancoDados.COD_COL7, BancoDados.COD_ID, BancoDados.COD_DESC, BancoDados.COD_COL10, BancoDados.COD_COL11, BancoDados.COD_COL12, BancoDados.COD_COL13, BancoDados.COD_COL14};
        SQLiteDatabase db = this.getReadableDatabase();

        cursor = db.query(BancoDados.CODIGO, campos, null, null, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

}