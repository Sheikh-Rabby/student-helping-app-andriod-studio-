package com.example.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Locale;

public class stopwatch extends AppCompatActivity {
    TextView textView;
    LottieAnimationView lottieAnimationView;

    private  int second = 0;
    private boolean running;
    private  boolean wasrunning;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

     lottieAnimationView=findViewById(R.id.clock);


        if (savedInstanceState !=null){

            second = savedInstanceState.getInt("second");
            running = savedInstanceState.getBoolean("running");
            wasrunning = savedInstanceState.getBoolean("wasrunning");
        }
        runTimmer();

    }
    public void runTimmer(){

        final    TextView  textView =findViewById(R.id.time);

        final Handler handler = new Handler();
           handler.post(new Runnable() {
               @Override
               public void run() {
                   int hour = second/3600;
                   int minite = (second/3600)/60;
                   int sec=second % 60;

                   String time =String.format(Locale.getDefault(),"%d:%02d:%02d",hour,minite,sec);

;                   textView.setText(time);
if (running){
    second++;
}
handler.postDelayed(this,1000);
               }
           });




    }

    @Override
    protected void onPause() {
        super.onPause();
        wasrunning = running;
        running = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (wasrunning){
            running=true;
        }
    }

    public void onClickstart(View view) {

        running= true;
        lottieAnimationView.playAnimation();


    }

    public void onClickstop(View view) {

        running= false;
        lottieAnimationView.cancelAnimation();
    }

    public void onClickReset(View view) {
        running= false;
        second=0;
        lottieAnimationView.cancelAnimation();


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
        finish();

    }
}