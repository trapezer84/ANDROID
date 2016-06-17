package com.ktds.leina.customlistview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

public class SubActivity extends ActionBarActivity {

    private TextView tvSubject;
    private TextView tvAuthor;
    private TextView tvHitCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        tvSubject = (TextView) findViewById(R.id.tvSubject);
        tvAuthor = (TextView) findViewById(R.id.tvAuthor);
        tvHitCount = (TextView) findViewById(R.id.tvHitCount);

        Intent intent = getIntent();
        ArticleVO article = (ArticleVO) intent.getSerializableExtra("article");

        tvSubject.setText(article.getSubject());
        tvAuthor.setText(article.getAuthor());
        tvHitCount.setText(article.getHitCount());
    }

}
