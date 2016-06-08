package com.ktds.leina.addressbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ktds.leina.addressbook.db.SimpleDB;
import com.ktds.leina.addressbook.vo.AddressVO;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //DB 넣기
        prepareSimpleDB();

        LinearLayout ll = (LinearLayout) findViewById(R.id.itemList);

        for (int i = 0; i < SimpleDB.getIndexs().size(); i++) {
            Button button = new AppCompatButton(this);
            button.setText(SimpleDB.getIndexs().get(i));

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DetailActivity.class);
                    String buttonText = (String) ((Button) v).getText();
                    intent.putExtra("key", buttonText);
                    startActivity(intent);
                }
            });

            ll.addView(button);
        }
    }

    private void prepareSimpleDB () {
        for(int i =1; i<20; i++) {
            SimpleDB.addArticle(i+"번 사람", new AddressVO(i +"번째 김씨", i + "번지 살고있음", i+""+i+""+i+""+i+""+i+""+i));

        }
    }

    private long pressedTime = 0;

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        if(pressedTime==0) {
            Toast.makeText(MainActivity.this, "한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
        else {
            int second = (int) System.currentTimeMillis();
            if(second > 2000) {
                pressedTime = 0;
            }
            else {
                finish();
            }

        }
    }
}
