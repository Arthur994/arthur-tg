package com.fatec.arthur.icdnfc.Screens;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.fatec.arthur.icdnfc.CodTag;
import com.fatec.arthur.icdnfc.R;
import com.fatec.arthur.icdnfc.database.BancoDados;
import com.fatec.arthur.icdnfc.database.ManipularArquivos;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> leituraTag = new ArrayList<String>();
    private NfcAdapter nfcAdapter;
    private PendingIntent pendingIntent;
    private IntentFilter writeTagFilters[];
    private NfcAdapter mAdapter;
    private PendingIntent mPendingIntent;
    private NdefMessage mNdefPushMessage;



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
            bd.addCodigos(Integer.parseInt(aux[0]),aux[1],aux[2],Integer.parseInt(aux[3]),aux[4],aux[5],aux[6],aux[7],aux[8],aux[9],aux[10],aux[11],aux[12],aux[13]);
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
        // TODO: 15/08/2017 Leitura NFC

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter == null) {
            Toast.makeText(this, "Este dispositivo não tem suporte a NFC", Toast.LENGTH_LONG).show();
            finish();
        }
        lerIntent(getIntent());

        pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        tagDetected.addCategory(Intent.CATEGORY_DEFAULT);
        writeTagFilters = new IntentFilter[] { tagDetected };
    }

    private void lerIntent(Intent intent) {
        String action = intent.getAction();
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            NdefMessage[] msgs = null;
            if (rawMsgs != null) {
                msgs = new NdefMessage[rawMsgs.length];
                for (int i = 0; i < rawMsgs.length; i++) {
                    msgs[i] = (NdefMessage) rawMsgs[i];
                }
            }
            buildTagViews(msgs);
        }
    }

    private void buildTagViews(NdefMessage[] msgs) {
        if (msgs == null || msgs.length == 0) return;

        String texto = "";
        byte[] payload = msgs[0].getRecords()[0].getPayload();
        String textEncoding = ((payload[0] & 128) == 0) ? "UTF-8" : "UTF-16";//Codificação do texto
        int languageCodeLength = payload[0] & 0063;

        try {
            //Pegar texto
            texto = new String(payload, languageCodeLength + 1, payload.length - languageCodeLength - 1, textEncoding);
        } catch (UnsupportedEncodingException e) {
            Log.e("UnsupportedEncoding", e.toString());
        }
        tratamentoTag(texto);
    }

    private void tratamentoTag(String text){
        String[] cid = text.split(";");

        for(String s : cid){
            leituraTag.add(s);
        }

        //Envia a lista cids, atraves da classe tag para a tela resultado onde é feita a busca;
        CodTag tag = new CodTag();
        tag.setCodigosNFC(leituraTag);

        //chamando a tela resultado.
        Intent resultado = new Intent(this, Resultado.class);
        startActivity(resultado);
    }

    @Override
    protected void onResume(){
        super.onResume();
        if (mAdapter != null) {
            if (!mAdapter.isEnabled()) {
                //showWirelessSettingsDialog();
            }
            mAdapter.enableForegroundDispatch(this, mPendingIntent, null, null);
            //mAdapter.enableForegroundNdefPush(this, mNdefPushMessage);
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        super.onPause();
        if (mAdapter != null) {
            mAdapter.disableForegroundDispatch(this);
           //mAdapter.disableForegroundNdefPush(this);
        }
    }

}
