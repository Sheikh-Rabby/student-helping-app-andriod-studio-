package com.example.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class helpLink extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_link);
        TextView textView = findViewById(R.id.w3school);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        TextView textView1 = findViewById(R.id.googlescholar);
        textView1.setMovementMethod(LinkMovementMethod.getInstance());
        TextView textView2 = findViewById(R.id.chatgpt);
        textView2.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
        finish();

    }
}