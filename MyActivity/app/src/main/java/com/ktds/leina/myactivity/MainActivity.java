package com.ktds.leina.myactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //로그캣 e:error d:debug
        Log.d("ACTIVITY_LC", "onCreated 호출됨");
        Toast.makeText(getApplicationContext(), "onCreated 호출됨", Toast.LENGTH_SHORT).show();

        Button button = (Button) findViewById(R.id.newActivity);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 파라미터 2개 (getApplicationContext(), 내가 띄우고 싶은 activity 적어주기)
                Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                // send : 이런식으로 데이터를 보낼 수 있다.
                intent.putExtra("message", "반갑습니다.");
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("ACTIVITY_LC", "onStart 호출됨");
        Toast.makeText(getApplicationContext(), "onStart 호출됨", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("ACTIVITY_LC", "onResume 호출됨");
        Toast.makeText(getApplicationContext(), "onResume 호출됨", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("ACTIVITY_LC", "onPause 호출됨");
        Toast.makeText(getApplicationContext(), "onPause 호출됨", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("ACTIVITY_LC", "onStop 호출됨");
        Toast.makeText(getApplicationContext(), "onStop 호출됨", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("ACTIVITY_LC", "onRestart 호출됨");
        Toast.makeText(getApplicationContext(), "onRestart 호출됨", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("ACTIVITY_LC", "onResume 호출됨");
        Toast.makeText(getApplicationContext(), "onDestroy 호출됨", Toast.LENGTH_SHORT).show();
    }
}
