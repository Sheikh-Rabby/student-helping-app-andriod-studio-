package com.example.signup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class Dashboard extends AppCompatActivity {


    TextView textView, textView1;
    ImageView imageView;

    DatabaseReference reference;
    FirebaseAuth auth;
    FirebaseUser user;
    private String userID;

    CardView cardView, cardView1, cardView2, cardView3, cardView4, cardView5;

    LottieAnimationView lottieAnimationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        auth = FirebaseAuth.getInstance();


        textView = findViewById(R.id.userprofile);
        imageView = findViewById(R.id.userperson);
        textView1 = findViewById(R.id.wel);

        cardView = findViewById(R.id.card);
        cardView1 = findViewById(R.id.Timer);
        cardView2 = findViewById(R.id.TodoList);
        cardView3 = findViewById(R.id.Timer2);
        cardView4 = findViewById(R.id.Timer3);
        cardView5 = findViewById(R.id.Timer4);
        lottieAnimationView = findViewById(R.id.process);


        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Calculator.class);
                startActivity(intent);
                finish();
                lottieAnimationView.setVisibility(View.VISIBLE);
                lottieAnimationView.playAnimation();
                textView.setVisibility(View.GONE);
                textView1.setVisibility(View.GONE);
                imageView.setVisibility(View.GONE);

                cardView.setVisibility(View.GONE);
                cardView1.setVisibility(View.GONE);
                cardView2.setVisibility(View.GONE);
                cardView3.setVisibility(View.GONE);
                cardView4.setVisibility(View.GONE);
                cardView5.setVisibility(View.GONE);


            }
        });


        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), stopwatch.class);
                startActivity(intent);
                finish();
                lottieAnimationView.setVisibility(View.VISIBLE);
                lottieAnimationView.playAnimation();
                textView1.setVisibility(View.GONE);
                imageView.setVisibility(View.GONE);
                textView.setVisibility(View.GONE);
                cardView.setVisibility(View.GONE);
                cardView1.setVisibility(View.GONE);
                cardView2.setVisibility(View.GONE);
                cardView3.setVisibility(View.GONE);
                cardView4.setVisibility(View.GONE);
                cardView5.setVisibility(View.GONE);
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), helpLink.class);
                startActivity(intent);
                finish();
                lottieAnimationView.setVisibility(View.VISIBLE);
                lottieAnimationView.playAnimation();
                textView.setVisibility(View.GONE);
                textView1.setVisibility(View.GONE);
                imageView.setVisibility(View.GONE);
                cardView.setVisibility(View.GONE);
                cardView1.setVisibility(View.GONE);
                cardView2.setVisibility(View.GONE);
                cardView3.setVisibility(View.GONE);
                cardView4.setVisibility(View.GONE);
                cardView5.setVisibility(View.GONE);
            }
        });
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TicTacToe.class);
                startActivity(intent);
                finish();
                lottieAnimationView.setVisibility(View.VISIBLE);
                lottieAnimationView.playAnimation();
                textView1.setVisibility(View.GONE);
                imageView.setVisibility(View.GONE);
                textView.setVisibility(View.GONE);
                cardView.setVisibility(View.GONE);
                cardView1.setVisibility(View.GONE);
                cardView2.setVisibility(View.GONE);
                cardView3.setVisibility(View.GONE);
                cardView4.setVisibility(View.GONE);
                cardView5.setVisibility(View.GONE);
            }
        });
        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NoteActivity.class);
                startActivity(intent);
                finish();
                lottieAnimationView.setVisibility(View.VISIBLE);
                lottieAnimationView.playAnimation();
                textView.setVisibility(View.GONE);
                textView1.setVisibility(View.GONE);
                imageView.setVisibility(View.GONE);
                cardView.setVisibility(View.GONE);
                cardView1.setVisibility(View.GONE);
                cardView2.setVisibility(View.GONE);
                cardView3.setVisibility(View.GONE);
                cardView4.setVisibility(View.GONE);
                cardView5.setVisibility(View.GONE);
            }
        });
        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), About_us.class);
                startActivity(intent);
                finish();
                lottieAnimationView.setVisibility(View.VISIBLE);
                lottieAnimationView.playAnimation();
                textView.setVisibility(View.GONE);
                textView1.setVisibility(View.GONE);
                imageView.setVisibility(View.GONE);
                cardView.setVisibility(View.GONE);
                cardView1.setVisibility(View.GONE);
                cardView2.setVisibility(View.GONE);
                cardView3.setVisibility(View.GONE);
                cardView4.setVisibility(View.GONE);
                cardView5.setVisibility(View.GONE);
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        String username = user.getEmail();
        textView.setText(username);

    }

    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this)

                .setMessage("Are you want to exit?")
                .setCancelable(false)
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        finish();
                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }
}








