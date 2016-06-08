package com.ktds.leina.calculator01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //우선 텍스트 뷰를 설정해준다.
        // add, append는 없다. setText로 설정해야한다. 그냥 textView는 보여주기만 하면 됨
        final TextView textView = (TextView) findViewById(R.id.textView);

        View.OnClickListener clickListner = new View.OnClickListener() {
            int firstNumber = 0;
            int firstCheck = 0;
            int lastMark = 0;
            @Override
            public void onClick(View v) {
//                Toast.makeText(v.getContext(), ((Button) v).getText(), Toast.LENGTH_SHORT).show();
                String text = "";

                if (v.getId() == R.id.btnPlus) {
                    if (lastMark == 1 ) {
                        firstNumber = firstNumber + Integer.parseInt(textView.getText()+"");

                    } else if (lastMark ==2) {
                        firstNumber = firstNumber - Integer.parseInt(textView.getText()+"");
                    }

                    textView.setText("0");
                    lastMark = 1;
                    firstCheck++;

                } else if (v.getId() == R.id.btnMinus) {
                    if (lastMark == 1 ) {
                        firstNumber = firstNumber + Integer.parseInt(textView.getText()+"");

                    } else if (lastMark ==2) {
                        firstNumber = firstNumber - Integer.parseInt(textView.getText()+"");
                    }

                    textView.setText("0");
                    lastMark = 2;
                    firstCheck++;
                }
                else if (v.getId() == R.id.btnEqual) {
                    int secondNumber = Integer.parseInt(textView.getText()+"");
                    if (lastMark == 1 ) {
                        textView.setText( (firstNumber + secondNumber) + "" );
                    } else if (lastMark ==2) {
                        int result =  firstNumber - secondNumber;
                        textView.setText( result + "" );
                    }
                    firstNumber = 0;
                    firstCheck = 0;
                } else if (v.getId() == R.id.btnReset) {
                    textView.setText("0");
                    firstNumber = 0;
                    firstCheck = 0;
                }
                else {
                    text = (String) textView.getText();
                    text += (((Button) v).getText());
                    // 앞에 0 붙는 거 없애려고 ...
                    textView.setText(Integer.parseInt(text) + "");
                }
            }
        };


        Button button0 = (Button) findViewById(R.id.btn0);
        Button button1 = (Button) findViewById(R.id.btn1);
        Button button2 = (Button) findViewById(R.id.btn2);
        Button button3 = (Button) findViewById(R.id.btn3);
        Button button4 = (Button) findViewById(R.id.btn4);
        Button button5 = (Button) findViewById(R.id.btn5);
        Button button6 = (Button) findViewById(R.id.btn6);
        Button button7 = (Button) findViewById(R.id.btn7);
        Button button8 = (Button) findViewById(R.id.btn8);
        Button button9 = (Button) findViewById(R.id.btn9);
        Button buttonPlus = (Button) findViewById(R.id.btnPlus);
        Button buttonEqual = (Button) findViewById(R.id.btnEqual);
        Button buttonReset = (Button) findViewById(R.id.btnReset);
        Button buttonMinus = (Button) findViewById(R.id.btnMinus);

        button0.setOnClickListener(clickListner);
        button1.setOnClickListener(clickListner);
        button2.setOnClickListener(clickListner);
        button3.setOnClickListener(clickListner);
        button4.setOnClickListener(clickListner);
        button5.setOnClickListener(clickListner);
        button6.setOnClickListener(clickListner);
        button7.setOnClickListener(clickListner);
        button8.setOnClickListener(clickListner);
        button9.setOnClickListener(clickListner);
        buttonPlus.setOnClickListener(clickListner);
        buttonEqual.setOnClickListener(clickListner);
        buttonReset.setOnClickListener(clickListner);
        buttonMinus.setOnClickListener(clickListner);

    }
}
