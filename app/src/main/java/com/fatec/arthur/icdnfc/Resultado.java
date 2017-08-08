package com.fatec.arthur.icdnfc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fatec.arthur.icdnfc.database.BancoDados;

public class Resultado extends AppCompatActivity {

    private BancoDados bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
    }
}
