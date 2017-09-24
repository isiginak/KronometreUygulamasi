package com.example.android.kronometre;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
private Chronometer sayac;
    Button basla,durdur,kaydet,goster;
    private static final String TAG = "";
    String FILENAME = "txt";
    long a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sayac=(Chronometer)findViewById(R.id.sayac);
 basla=(Button)findViewById(R.id.baslat);
        durdur=(Button)findViewById(R.id.durdur);
        basla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 a=SystemClock.elapsedRealtime();
                sayac.setBase(a);
                sayac.start();
            }
        });
        durdur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sayac.stop();
            }
        });

    }
    private void writeToFile(String data) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(FILENAME, Context.MODE_APPEND));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e(TAG, "File write failed: " + e.toString());
        }
    }

    public String readFromFile() {
        String ret = "";

        try {
            InputStream inputStream = openFileInput(FILENAME);

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e(TAG, "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e(TAG, "Can not read file: " + e.toString());
        }
        return ret;
    }


    public void save(View view) {
        kaydet=(Button)findViewById(R.id.kaydet);
        SimpleDateFormat bicim2 = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        Date tarihSaat = new Date();
       String ss=" "+ bicim2.format(tarihSaat).toString() + " tarihinde " + a + " sn kronometre çalıştı.\n"+"\n";
        writeToFile(ss);
        Toast.makeText(MainActivity.this, "Kayıt gerçekleşti", Toast.LENGTH_SHORT).show();
    }

    public void show(View view) {
        goster=(Button)findViewById(R.id.goster);
        String oku=readFromFile();
        Intent i=new Intent(getApplicationContext(),Main2Activity.class);
        Bundle b=new Bundle();
        b.putString("kronometre",oku);
        i.putExtras(b);
        startActivity(i);
    }
}
