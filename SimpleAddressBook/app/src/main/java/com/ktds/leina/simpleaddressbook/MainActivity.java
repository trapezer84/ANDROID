package com.ktds.leina.simpleaddressbook;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void call(View view) {
        int id = view.getId();

        if (id == R.id.button) {
            Toast.makeText(view.getContext(), "전화를 겁니다", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel: 010-2738-4878"));
            startActivity(intent);
        }
        else {

        }
    }
}
