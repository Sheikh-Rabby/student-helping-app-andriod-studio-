package com.example.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PlayerSetup extends AppCompatActivity {
  private EditText player1;
  private  EditText player2;
  Button Btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_setup);
  player1 = findViewById(R.id.playerone);
  player2 = findViewById(R.id.playertwo);
  Btn = findViewById(R.id.submitBtn);

  Btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
           String player1name = player1.getText().toString();
          String player2name = player2.getText().toString();

          Intent intent = new Intent(getApplicationContext(), GameDisplay.class);
           intent.putExtra("PlayerNames",new String[]{player1name,player2name});
           startActivity(intent);

      }
  });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, TicTacToe.class);
        startActivity(intent);
        finish();

    }
}