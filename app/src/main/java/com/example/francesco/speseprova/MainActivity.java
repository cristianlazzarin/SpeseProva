package com.example.francesco.speseprova;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }//onCreate

    /**Svuota la textbox*/
    public void emptyImporto (View view){
        EditText importo = (EditText) findViewById(R.id.textImporto);
        importo.setText("");
    }//emptyImporto

    /**Messaggio che visualizza a schermo l'importo */
    public void saveValue (View view){
        EditText importo = (EditText) findViewById(R.id.textImporto);
        EditText data = (EditText) findViewById(R.id.textData);
        EditText desc = (EditText) findViewById(R.id.textType);
        TextView textView  = (TextView) findViewById(R.id.textView);
        String toSave =(importo.getText()+" | "+data.getText()+" | "+desc.getText()+"\n");
        //Debug per vedere se ha preso correttamente i dati
        //textView.setText("importo/data/descrizione"+"\n"+toSave);
        String filename = "listaScontrini";
        //writeToFile(filename,toSave,getApplicationContext());
        writeToFile(filename,toSave,getApplicationContext());
        //(cristian) quando si salva il valore,il box torna come all'inizio
        EditText new_importo = (EditText) findViewById(R.id.textImporto);
        new_importo.setText("importo");
    }//saveValue

    private void writeToFileOLD(String filename,String data,Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(filename, Context.MODE_APPEND));
            outputStreamWriter.write("\n"+data);
            outputStreamWriter.close();
        }//try
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }//catch
    }//writeToFile

    private String readFromFileOLD(Context context,String filename) {
        String ret = "";
        try {
            InputStream inputStream = context.openFileInput(filename);
            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();
                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }//while
                inputStream.close();
                ret = stringBuilder.toString();
            }//if
        }//try
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }//catch
        return ret;
    }//readFromFile

    private void writeToFile(String filename,String data,Context context) {
        File path = context.getFilesDir();
        File file = new File(path, filename);
        try {
            FileOutputStream stream = new FileOutputStream(file);
            stream.write(data.getBytes());
            stream.close();
        } catch(java.io.IOException e){
            //gestione
        }//catch
    }//writeToFileAlt

    private String readFromFile(String filename,Context context){
        String ris="";
        File path = context.getFilesDir();
        File file = new File(path, filename);
        int length = (int) file.length();
        byte[] bytes = new byte[length];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(bytes);
            in.close();
        } catch (java.io.IOException e){
            //gestione eccezione
            ris=e.getMessage();
        }//catch
        ris += context.getFilesDir().toString()+"/"+filename+"\n"+new String(bytes);
        return ris;
    }//readFromFileAlt

    /**Metodo per la lettura del file*/
    public void readValueDEBUG (View view){
        String filename = "listaScontrini";
        //String toPrint = readFromFile(getApplicationContext(),filename).replace("|","  ")+"\n";
        String toPrint = readFromFile(filename,getApplicationContext()).replace("|","  ")+"\n";
        TextView debug = (TextView) findViewById(R.id.textView);
        debug.setText(toPrint);
    }//readValueDEBUG
}//MainActivity
