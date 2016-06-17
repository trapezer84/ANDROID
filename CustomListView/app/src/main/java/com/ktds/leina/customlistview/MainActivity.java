package com.ktds.leina.customlistview;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ktds.leina.customlistview.facebook.Facebook;
import com.restfb.types.Post;

import java.util.List;

public class MainActivity extends ActionBarActivity {

    private ListView lvArticleList;
    private Facebook facebook;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();

        facebook = new Facebook(this);
        facebook.auth();

        lvArticleList = (ListView) findViewById(R.id.lvArticleListView);

        /*final List<ArticleVO> articleList = new ArrayList<ArticleVO>();
        for (int i = 0; i < 300; i++) {
            articleList.add(new ArticleVO("제목입니다" + i, "글쓴이 입니다", new Random().nextInt(9999) + ""));
        }*/

        /*lvArticleList.setAdapter(new ArticleListViewAdaptor(this, articleList));
        lvArticleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, id + "", Toast.LENGTH_SHORT).show();

                ArticleVO article = articleList.get(position);
                Toast.makeText(MainActivity.this, article.getSubject(), Toast.LENGTH_SHORT).show();

                // Intent 객체를 new 로 생성할 때 첫번째 파라미터로 보내는곳의 Acvitity ,
                // 두번째 파라미터로 받는 곳의 Activity 를 추가해서 생성한다
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                intent.putExtra("article", article);
                startActivity(intent);


            }
        });*/
    }

    public void setTimeLine() {
        if(facebook.isLogin()) {
            // TimeLine 가져오기 ...
            facebook.getTimeLine(new Facebook.TimelineSerializerable() {
                public void serialize (final List<Post> posts) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            lvArticleList.setAdapter(new ArticleListViewAdaptor(MainActivity.this, posts));
                        }
                    });
                }
            });
        }
    }

    private class ArticleListViewAdaptor extends BaseAdapter {

        /**
         * ListView 에 Item을 셋팅할 요청자의 객체가 들어감 ...
         */
        private Context context;

        /**
         * ListView에 셋팅할 Item 정보들 ...
         */
        private List<Post> articleList;

        private Post article;

        public ArticleListViewAdaptor(Context context, List<Post> articleList) {
            this.context = context;
            this.articleList = articleList;
        }

        @Override
        public int getCount() {
            return articleList.size();
        }

        /**
         * postition 번째 Item 정보를 가져옴 ...
         *
         * @param position
         * @return
         */
        @Override
        public Object getItem(int position) {
            return this.articleList.get(position);
        }

        /**
         * Item Index를 가져옴 ...
         * Item Index = position
         *
         * @param position
         * @return
         */
        @Override
        public long getItemId(int position) {
            return position;
        }

        /**
         * ListView에 Item 들을 셋팅함 ...
         *
         * @param position    : 현재 보여질 Item의 Index, 0부터 getCount() 까지 증가함
         * @param convertView : ListView의 Item Cell 객체를 가져옴 (한칸에 대한 객체)
         * @param parent      : ListView
         * @return
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ItemHolder holder = null;

            // 가장 간단한 방법 그러나 메모리를 가장 많이 먹는 방법!
            // 사용자가 처음으로 Flicking 할 때, 아래 쪽에 만들어지는 Cell은 Null 이다.
            // Null 에 내용을 넣기 위해 ..
            if (convertView == null) {
                // Item Cell 에 Layout을 적용시킬 Inflator 객체
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);

                // Item Cell 에 Layour을 적용시킨다.(실제 객체가 존재하는 곳 converView)
                convertView = inflater.inflate(R.layout.list_item, parent, false);

                holder = new ItemHolder();
                holder.tvSubject = (TextView) convertView.findViewById(R.id.tvSubject);
                holder.tvAuthor = (TextView) convertView.findViewById(R.id.tvAuthor);
                holder.tvHitCount = (TextView) convertView.findViewById(R.id.tvHitCount);

                // 항상 convertView 는 holder를 업어 간다.
                convertView.setTag(holder);
            } else {
                holder = (ItemHolder) convertView.getTag();
            }

            // 맴버 변수로 만들 경우 메모리를 덜 먹는다.
            article = (Post) getItem(position);
            if( article.getMessage() != null) {
                holder.tvSubject.setText(article.getMessage());
            }
            else if( article.getStory() != null) {
                holder.tvSubject.setText(article.getStory());
            }

            holder.tvAuthor.setText(article.getFrom().getName());
            if(article.getLikes() == null) {
                holder.tvHitCount.setText("0");
            }
            else {
                holder.tvHitCount.setText(article.getLikes().getData().size() + "");
            }
            return convertView;
        }
    }

    private class ItemHolder {
        public TextView tvSubject;
        public TextView tvAuthor;
        public TextView tvHitCount;
    }

}
