package com.ktds.leina.simpleboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.ktds.leina.simpleboard.db.SimpleDB;
import com.ktds.leina.simpleboard.vo.ArticleVO;

public class DetailActivity extends AppCompatActivity {

    private TextView tvSubject;
    private TextView tvAuthor;
    private TextView tvDescription;
    private TextView tvArticleNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvSubject = (TextView) findViewById(R.id.tvSubject);
        tvAuthor = (TextView) findViewById(R.id.tvAuthor);
        tvDescription = (TextView) findViewById(R.id.tvDescription);
        tvArticleNumber = (TextView) findViewById(R.id.tvArticleNumber);

        Intent intent = getIntent();
        String key = intent.getStringExtra("key");
//         만일 받아오는 값이 int라면
//        int key = intent.getIntExtra("key");

        ArticleVO articleVO = SimpleDB.getArticle(key);

        // 앱의 프로젝트 명 쪽을 바꾸는 코드
        setTitle(articleVO.getSubject());

        tvSubject.setText(articleVO.getSubject());
        tvArticleNumber.setText(articleVO.getArticleNo() + "");
        tvAuthor.setText(articleVO.getAuthor());
        tvDescription.setText(articleVO.getDescription());
    }

    // back버튼을 누를 때 = 종료가 될 경우에 ...
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(DetailActivity.this, "엑티비티를 종료합니다.", Toast.LENGTH_SHORT).show();
    }
}
