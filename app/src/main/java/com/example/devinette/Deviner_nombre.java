package com.example.devinette;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Deviner_nombre extends AppCompatActivity {

    TextView result;
    EditText nbr;
    Button valider;
    TextView hist;
    TextView time;
    Button start_pause;
    TextView scoreR;
    ImageButton highScore;
    int n = 0;
    String ch = "";
    int score = 100;
    int nbSecondesPasse = 0;
    private  static final long start_time_in_millis = 60000;
    ImageButton restart;
    private CountDownTimer countDownTimer;
    private  boolean mTimerRunning;
    private long mTimeLeftInMiliss = start_time_in_millis;
//    TextView text1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deviner_nombre);
        highScore = (ImageButton)findViewById(R.id.HighScore);
        nbr = (EditText) findViewById(R.id.edit);
        valider = findViewById(R.id.btn2);
        result = (TextView) findViewById(R.id.result);
        hist = (TextView) findViewById(R.id.hist);
        scoreR = (TextView) findViewById(R.id.score);
        start_pause = findViewById(R.id.start_pause);
        restart = findViewById(R.id.restart);
        time = (TextView)findViewById(R.id.timer);
//        text1= (TextView) findViewById(R.id.text1);
//        Bundle extras=getIntent().getExtras();
//        String nom =extras.getString("nom");
        int valeur =  (int) (Math.random()*100);


     highScore.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent i1 = new Intent(Deviner_nombre.this, ListActivity.class);
             startActivity(i1);
             Toast.makeText(getApplicationContext(),"Heigh score",Toast.LENGTH_SHORT).show();

         }
     });

        start_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                text1.setText(valeur);

                if(mTimerRunning){
                    pauseTimer();
                }else{
                    startTimer();
                    nbr.getText().clear();
                    result.setText("");


                }
            }
        });

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              resetTimer();
            }
        });

updateCountDownText();

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int valeurSaisie = Integer.parseInt((nbr.getText().toString()));
               if (valeur == valeurSaisie){

                   result.setText("Bravo, Vous avez Gagné ! : " +String.valueOf(n)+ " " +"nombres d'essais");
                   countDownTimer.cancel();
                 scoreR.setText("Score : " + (score - (n + nbSecondesPasse)));
                 Intent i = new Intent(Deviner_nombre.this, Home.class);
                   i.putExtra("score", scoreR.getText().toString());

                   startActivity(i);
                   start_pause.setText("Restart");


               }
               else if (valeurSaisie  > valeur ) {
                   result.setText("Votre nombre est grande !! " );
                   n++;
                   ch+= String.valueOf(valeurSaisie) + " ";
               }

               else {
                   result.setText("Votre nombre est petite !! " );
                   n++;
                   ch+= String.valueOf(valeurSaisie ) + " ";
               }

                hist.setText("La Valeur Saisie est : \n" + ch );


            }

        });


    }
    private void startTimer(){
        countDownTimer = new CountDownTimer(mTimeLeftInMiliss,1000) {
            @Override
            public void onTick(long milisUntilFinished) {
                mTimeLeftInMiliss = milisUntilFinished;
                updateCountDownText();
                nbSecondesPasse ++;
            }

            @Override
            public void onFinish() {
                time.setText("Vous avez perdu");
                mTimerRunning = false;
                start_pause.setText("start");
                start_pause.setVisibility(View.VISIBLE);
                restart.setVisibility(View.INVISIBLE);
                scoreR.setText("Score :" +(score -(n+ nbSecondesPasse)));

            }
        }.start();
        mTimerRunning = true;
        start_pause.setText("pause");
        restart.setVisibility(View.INVISIBLE);
    }
    private void  pauseTimer(){
        countDownTimer.cancel();
        mTimerRunning = false;
        start_pause.setText("start");
        restart.setVisibility(View.VISIBLE);
    }
    private void resetTimer(){
        mTimeLeftInMiliss = start_time_in_millis;
        updateCountDownText();
        restart.setVisibility(View.INVISIBLE);
       start_pause.setVisibility(View.VISIBLE);
        nbr.getText().clear();
        result.setText("");
        hist.setText("");


    }

    private  void updateCountDownText(){
        int minutes = (int) (mTimeLeftInMiliss / 1000) / 60;
        int seconds = (int) (mTimeLeftInMiliss / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
   time.setText(timeLeftFormatted);
    }

}