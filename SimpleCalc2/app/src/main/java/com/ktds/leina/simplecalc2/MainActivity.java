package com.ktds.leina.simplecalc2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText tvFirstNumber;
    private EditText tvSecondNumber;
    private Button btnAdd;
    private Button btnSub;
    private Button btnDiv;
    private Button btnMulti;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvFirstNumber = (EditText) findViewById(R.id.tvFirstNumber);
        tvSecondNumber = (EditText) findViewById(R.id.tvSecondNumber);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnSub = (Button) findViewById(R.id.btnSub);
        btnDiv = (Button) findViewById(R.id.btnDiv);
        btnMulti = (Button) findViewById(R.id.btnMulti);
        tvResult = (TextView) findViewById(R.id.tvResult);

    }

    public void calculate(View view) {

        int id = view.getId();
        if (id == R.id.btnAdd || id == R.id.btnSub || id == R.id.btnDiv || id == R.id.btnMulti) {
            if (tvFirstNumber.getText().toString().length() == 0) {
                Toast.makeText(MainActivity.this, "첫번째 수를 입력하세요.", Toast.LENGTH_SHORT).show();
                tvFirstNumber.requestFocus();
            }

            if (tvSecondNumber.getText().toString().length() == 0) {
                Toast.makeText(MainActivity.this, "두번째 수를 입력하세요.", Toast.LENGTH_SHORT).show();
                tvSecondNumber.requestFocus();
            }

            Intent intent = new Intent(getApplicationContext(), CalcActivity.class);
            intent.putExtra("tvFirstNumber", tvFirstNumber.getText().toString());
            intent.putExtra("tvSecondNumber", tvSecondNumber.getText().toString());
            int operator = 0;
            // 어떻게 operator을 보낼 수 있을까? id를 보낸다면 알 수 있는 방법이 있나?
            if (id == R.id.btnAdd) {
                operator = 1;
            } else if (id == R.id.btnSub) {
                operator = 2;
            } else if (id == R.id.btnDiv) {
                operator = 3;
            } else if (id == R.id.btnMulti) {
                operator = 4;
            }
            intent.putExtra("operator", operator);

            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            // intent으로 갔다가 다시 받아올 때, startActivity 말고 startActivityForResult 을 사용합니다.
            startActivityForResult(intent, 1000);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Toast.makeText(MainActivity.this, "계산을 완료했습니다.", Toast.LENGTH_SHORT).show();
        tvResult.setText(data.getStringExtra("result"));
    }
}
