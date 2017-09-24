package com.example.android.kronometre;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        txt=(TextView)findViewById(R.id.crm);
        Intent intent=getIntent();
        Bundle bundle=new Bundle();
        bundle=intent.getExtras();
        String gelen=bundle.getString("kronometre");
        txt.setText(gelen);
    }
}
