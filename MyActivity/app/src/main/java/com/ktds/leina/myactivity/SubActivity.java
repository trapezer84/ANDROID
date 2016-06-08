package com.ktds.leina.myactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        // get Intent 데이터...
        Intent intent = getIntent();
        // 아래와 같은 방식으로 main_Activity에서 보낸 데이터를 받을 수 있다.
        Log.d("ACTIVITY_LC", intent.getStringExtra("message"));
    }
}
