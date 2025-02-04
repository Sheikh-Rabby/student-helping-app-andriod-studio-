package com.example.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import  org.mozilla.javascript.Scriptable;

public class Calculator extends AppCompatActivity implements View.OnClickListener {
    TextView result, solution;
    MaterialButton buttonC, openbrak, closebrak;
    MaterialButton buttonDivide, buttonMultiply, buttonMinus, buttonPlus, buttonEqual;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    MaterialButton buttonAc, buttonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        result = findViewById(R.id.result);
        solution = findViewById(R.id.solution);
        assignId(button0, R.id.zero);
        assignId(button1, R.id.botton_1);
        assignId(button2, R.id.button_2);
        assignId(button3, R.id.button_3);
        assignId(button4, R.id.botton_4);
        assignId(button5, R.id.button_5);
        assignId(button6, R.id.button_6);
        assignId(button7, R.id.botton_7);
        assignId(button8, R.id.button_8);
        assignId(button9, R.id.button_9);
        assignId(buttonC, R.id.botton_c);
        assignId(buttonAc, R.id.botton_Ac);
        assignId(buttonDot, R.id.dot);
        assignId(buttonDivide, R.id.divide);
        assignId(buttonEqual, R.id.equal);
        assignId(buttonMinus, R.id.minus);
        assignId(buttonMultiply, R.id.multiply);
        assignId(buttonPlus, R.id.plus);
        assignId(openbrak, R.id.open_brak);
        assignId(closebrak, R.id.close_brak);


    }

    void assignId(MaterialButton btn, int id) {

        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttontext = button.getText().toString();
        String calculate = solution.getText().toString();

        if (buttontext.equals("Ac")) {
            solution.setText("");
            result.setText("0");
            return;

        }
        if (buttontext.equals("=")) {
            solution.setText(result.getText());
            return;
        }
        if (buttontext.equals("C")) {
            calculate = calculate.substring( 0,calculate.length() - 1);
        } else {
            calculate = calculate + buttontext;
        }
        solution.setText(calculate);

        String finalResult = getResult(calculate);

        if (!finalResult.equals("Err")) {
            result.setText(finalResult);
        }

    }


    String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
             String finalResult =context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            return finalResult;

        }catch (Exception e){
            return "Err";
        }
    }

        @Override
        public void onBackPressed() {
            Intent intent = new Intent(this, Dashboard.class);
            startActivity(intent);
            finish();

        }
    }
