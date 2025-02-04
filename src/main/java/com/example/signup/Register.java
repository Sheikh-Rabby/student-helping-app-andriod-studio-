package com.example.signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.signup.Class.Student;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    TextInputEditText editTextEmail,editTextPassword,editTextUsername;
    Button buttonReg;
    ProgressBar progressBar;
    FirebaseAuth mAuth;
    TextView textView;
    DatabaseReference databaseReference;
    LottieAnimationView lottieAnimationView;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("student");

        editTextEmail = findViewById(R.id.email);
        editTextUsername = findViewById(R.id.username);
        editTextPassword = findViewById(R.id.password);
        buttonReg = findViewById(R.id.signup);
        lottieAnimationView=findViewById(R.id.reload);

        textView = findViewById(R.id.LoginNow);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });
        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              lottieAnimationView.setVisibility(View.VISIBLE);
              buttonReg.setVisibility(View.GONE);
                String email, password,username;



                username = editTextUsername.getText().toString();
                email = editTextEmail.getText().toString();
                password = editTextPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    lottieAnimationView.setVisibility(View.GONE);
                    buttonReg.setVisibility(View.VISIBLE);


                    Toast.makeText(Register.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;


                } if (TextUtils.isEmpty(username)) {
                    lottieAnimationView.setVisibility(View.GONE);
                    buttonReg.setVisibility(View.VISIBLE);

                    Toast.makeText(Register.this, "Enter username", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    lottieAnimationView.setVisibility(View.GONE);
                    buttonReg.setVisibility(View.VISIBLE);
                    Toast.makeText(Register.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;

                }
                mAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                              lottieAnimationView.setVisibility(View.GONE);
                                if (task.isSuccessful()) {


                                    Toast.makeText(Register.this, "Account created", Toast.LENGTH_SHORT).show();



                                } else {

                                    buttonReg.setVisibility(View.VISIBLE);
                                    Toast.makeText(Register.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();



                                }
                            }


                        });
savedata();
            }
        });








    }
    public void savedata(){
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
       String username = editTextUsername.getText().toString();

       String key= databaseReference.push().getKey();
       Student student = new Student(username,email,password);
       databaseReference.child(key).setValue(student);




    }

}