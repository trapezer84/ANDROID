package com.ktds.leina.helloworld;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //AppCompatActivity 액티비티는 화면에 뿌려주는 것

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //onCreate는 java의 main과 같은 부분

        super.onCreate(savedInstanceState);

        // ~한 화면을 보여줘라 라는 method setContentView
        // R : Resource 약자
        setContentView(R.layout.activity_main);

        final Button button1 = (Button) findViewById(R.id.btn1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), button1.getText(), Toast.LENGTH_SHORT).show();
                Toast.makeText(v.getContext(), ((Button) v).getText(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void call(View view) {

        // String인데 int로 받아오는 이유는, android가 모든 id를 숫자로 받기 때문이다.
        int id = view.getId();

        if(id==R.id.button) {
            //종종 쓰는 단어..
            Toast.makeText(view.getContext(), "버튼을 눌렀습니다", Toast.LENGTH_SHORT).show();

            // 전화를 거는 화면이 뜬다.
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-3333-4444"));
            startActivity(intent);
        }
        else if (id==R.id.textView2) {
            Toast.makeText(view.getContext(), "텍스트을 눌렀습니다", Toast.LENGTH_SHORT).show();
        }

    }
}
