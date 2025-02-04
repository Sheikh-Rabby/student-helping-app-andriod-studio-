package com.example.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameDisplay extends AppCompatActivity {

    Button Playagain,home;
    private TicTacToeBoard ticTacToeBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_display);
        Playagain = findViewById(R.id.playagain);
        home = findViewById(R.id.Home);
        Button playAgainBtn = findViewById(R.id.playagain);
        Button homeBtn = findViewById(R.id.Home);
        TextView playerTurn= findViewById(R.id.playerDisplay);
        playAgainBtn.setVisibility(View.GONE);
        homeBtn.setVisibility(View.GONE);
        String[] playerNames = getIntent().getStringArrayExtra("PlayerNames");
        if (playerNames!=null){
            playerTurn.setText((playerNames[0]+"'s turn"));
        }
        ticTacToeBoard = findViewById(R.id.ticTacToeBoard);
        ticTacToeBoard.setUpGame(playAgainBtn,homeBtn,playerTurn,playerNames);


        Playagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ticTacToeBoard.resetGame();
                ticTacToeBoard.invalidate();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TicTacToe.class);
                startActivity(intent);
            }
        });
    }



    }
