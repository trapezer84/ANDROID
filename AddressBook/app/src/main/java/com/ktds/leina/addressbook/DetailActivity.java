package com.ktds.leina.addressbook;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ktds.leina.addressbook.db.SimpleDB;
import com.ktds.leina.addressbook.vo.AddressVO;

public class DetailActivity extends AppCompatActivity {

    private TextView tvName;
    private TextView tvAddress;
    private TextView tvPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvName = (TextView) findViewById(R.id.tvName);
        tvAddress = (TextView) findViewById(R.id.tvAddress);
        tvPhoneNumber = (TextView) findViewById(R.id.tvPhoneNumber);

        Intent intent = getIntent();
        String key = intent.getStringExtra("key");

        AddressVO addressVO = SimpleDB.getArticle(key);
        setTitle(addressVO.getName() + "의 상세정보");

        tvPhoneNumber.setText("번호 : " + addressVO.getPhoneNumber());
        tvAddress.setText("주소 : " + addressVO.getAddress());
        tvName.setText("이름 : " + addressVO.getName());

    }

    public void call(View view) {
        int id = view.getId();

        if (id == R.id.tvPhoneNumber) {
            Toast.makeText(view.getContext(), "전화를 겁니다", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tvPhoneNumber.getText()));
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(DetailActivity.this, "주소 리스트로 되돌아 갑니다.", Toast.LENGTH_SHORT).show();

    }
}
