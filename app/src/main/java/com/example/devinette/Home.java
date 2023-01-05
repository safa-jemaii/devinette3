package com.example.devinette;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Home extends AppCompatActivity {
    Button entrer;
    EditText nom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        nom = (EditText) findViewById(R.id.edit);
        entrer = findViewById(R.id.btnEntrer);



        entrer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BD myDB = new BD(Home.this);
                Intent i1 = getIntent();
                String score = i1.getStringExtra("score");
                myDB.addNewGamer(nom.getText().toString().trim(),score.trim());
                Intent i = new Intent(getApplicationContext(),ListActivity.class);
                startActivity(i);
                finish();
            }});
    }

}