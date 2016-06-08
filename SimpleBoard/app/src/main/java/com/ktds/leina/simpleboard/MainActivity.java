package com.ktds.leina.simpleboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ktds.leina.simpleboard.db.SimpleDB;
import com.ktds.leina.simpleboard.vo.ArticleVO;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // DB에 글 넣기
        prepareSimpleDB();

        LinearLayout l1 = (LinearLayout) findViewById(R.id.itemList);

//         반복 시작
//        Button button = new AppCompatButton(this);
//        button.setText("코드로 만든 버튼입니다.");
//        l1.addView(button);
        // 반복 끝

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

            l1.addView(button);
        }
    }

    // DB만드는 중 ..
    private void prepareSimpleDB() {
        for(int i = 1; i<100; i++) {
            SimpleDB.addArticle(i + "번 글", new ArticleVO(i,  i + "번 글 입니다.", i + "번 글 입니다. 내용입니다.", "내가 씀"));
        }
    }

    private long pressedTime;


        @Override
    public void onBackPressed() {
//        super.onBackPressed();
        if(pressedTime == 0) {
            Toast.makeText(MainActivity.this, "한번 더 누르면 종료됩니다.", Toast.LENGTH_LONG).show();
            pressedTime = System.currentTimeMillis();
        } else {
            int seconds = (int) (System.currentTimeMillis() - pressedTime) / 1000;
             if (seconds > 2000) {
                 pressedTime = 0;
             }
             else {
                 // 앱을 종료시킨다.
                finish();
             }
        }
    }
}
