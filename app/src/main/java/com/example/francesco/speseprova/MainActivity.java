package com.example.francesco.speseprova;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }//onCreateh

    /**Svuota la textbox*/
    public void emptyImporto (View view){
        EditText importo = (EditText) findViewById(R.id.textImporto);
        importo.setText("");
    }//emptyImporto

    /**Svuota la textbox*/
    public void emptyData (View view){
        EditText data = (EditText) findViewById(R.id.textData);
        data.setText("");
    }//emptyData

    /**Svuota la textbox*/
    public void emptyDescrizione (View view){
        EditText descrizione = (EditText) findViewById(R.id.textType);
        descrizione.setText("");
    }//emptyDescrizione

    /**Messaggio che visualizza a schermo l'importo */
    public void saveValue (View view){
        EditText importo = (EditText) findViewById(R.id.textImporto);
        EditText data = (EditText) findViewById(R.id.textData);
        EditText desc = (EditText) findViewById(R.id.textType);
        TextView textView  = (TextView) findViewById(R.id.textView);
        String toSave =(importo.getText()+"|"+data.getText()+"|"+desc.getText());
        //Debug per vedere se ha preso correttamente i dati
        textView.setText("importo/data/descrizione"+"\n"+toSave);
        //TO DO: salvare il valore su un file
        String filename = "listaScontrini";

        //(cristian) quando si salva il valore,il box torna come all'inizio
        EditText new_importo = (EditText) findViewById(R.id.textImporto);
        new_importo.setText("importo");
    }//saveValue

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }//if
        return false;
    }//isExternalStorageWritable

    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }//if
        return false;
    }//isExternalStorageReadable

    /**Metodo di debug per la lettura del file*/
    public void readValueDEBUG (View view){
        String filename = "listaScontrini";
        //TO DO: lettura del file ed inserimento nella textview
        TextView debug = (TextView) findViewById(R.id.textView);
        debug.setText(" ");
    }//readValueDEBUG
}//MainActivity
