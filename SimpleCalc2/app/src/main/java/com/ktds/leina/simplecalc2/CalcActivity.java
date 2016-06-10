package com.ktds.leina.simplecalc2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CalcActivity extends AppCompatActivity {

    private TextView tvClacText;
    private Button btnResult;
    private float result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        tvClacText = (TextView) findViewById(R.id.tvClacText);
        btnResult = (Button) findViewById(R.id.btnResult);

        Intent intent = getIntent();
        String firstNum = intent.getStringExtra("tvFirstNumber");
        String secondNum = intent.getStringExtra("tvSecondNumber");
        int operator = intent.getIntExtra("operator", 0);
        String operatorString = "";
        result = 0;

        if (operator == R.id.btnAdd) {
            result = Integer.parseInt(firstNum) + Integer.parseInt(secondNum);
            operatorString = "+";
        } else if (operator == R.id.btnSub) {
            result = Integer.parseInt(firstNum) - Integer.parseInt(secondNum);
            operatorString = "-";
        } else if (operator == R.id.btnDiv) {
            result = Float.parseFloat(firstNum) / Float.parseFloat(secondNum);
            operatorString = "/";
        } else if (operator == R.id.btnMulti) {
            result = Integer.parseInt(firstNum) * Integer.parseInt(secondNum);
            operatorString = "*";
        }

        tvClacText.setText(firstNum + operatorString + secondNum + "=" + result);


        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CalcActivity.this, "계산 결과를 내보냅니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra("result", tvClacText.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });


    }
}
