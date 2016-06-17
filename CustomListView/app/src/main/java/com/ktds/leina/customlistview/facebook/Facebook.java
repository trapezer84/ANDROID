package com.ktds.leina.customlistview.facebook;

import android.content.Context;
import android.util.Log;

import com.ktds.leina.customlistview.MainActivity;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.Post;
import com.restfb.types.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 206-025 on 2016-06-14.
 */
public class Facebook {

    /**
     * 인증과 관련된 상수들
     */
    private static final String APP_ID = "1081641218565217";
    private static final String APP_SECRET = "d1ac3616e4181df270514a9147fc2276";
    private static final String ACCESS_TOKEN = "EAAPXvzibZAGEBABX6UTWZBzOSjwwtmW1iHXD6dMaz6Hmx11Eo6lSaaHZBjjFbHlANxWtHpkxeYqdsQh9etQALPwoS4qH8ZBzFhS26lGrKI5tYV7WSVOhW9Cak1JHCiLfIYcPUKu4qjo8s2b9I23ZA9tItEwWRmIIBySg7F6ZCsQwZDZD";

    private Context context;

    public Facebook(Context context) {
        this.context = context;
    }

    /**
     * 페이스북 인증 객체
     */
    private FacebookClient myFacebook;

    private boolean isLogin;

    /**
     * Facebook으로 로그인 한다.
     *
     * @return : 로그인 성공 시 true
     */
    public void auth() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                // Facebook Login
                myFacebook = new DefaultFacebookClient(ACCESS_TOKEN, Version.LATEST);

                // 성공적인 로그인 체크, me 하면 내 정보 가져오는 것
                // 로그인된 계정의 정보를 가져온다
                User me = myFacebook.fetchObject("me", User.class);

                Log.d("FACEBOOK", me.getName() + "계정으로 로그인 함");

                isLogin = (me != null);

                if (isLogin) {
                    // MainActivity를 사용하기 위해서 Context를 가져오고
                    // Context에서는 바로 setTimeLine()이 없기 때문에, 캐스팅을 한다.
                    // 원래는 인터페이스를 주고 처리하면 된다...!
                    ((MainActivity) context).setTimeLine();
                }
            }
        }).start();


    }

    public boolean isLogin() {
        return isLogin;
    }

    public void getTimeLine(final TimelineSerializerable timelineSerializerable) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Connection<Post> feeds = myFacebook.fetchConnection("me/feed", Post.class, Parameter.with("fields", "from,likes,message,story"));

                List<Post> postList = new ArrayList<Post>();

                // 타임라인 정보들 ...
                for (List<Post> posts : feeds) {
                    postList.addAll(posts);

                    // 타입라인에 등록되어 있는 포스트 1건
                    /*for (Post post : posts) {
                        // story는 연혁

                        if (post.getStory() != null) {
                            Log.d("FACEBOOK", post.getStory());
                        }
                        if (post.getMessage() != null) {
                            Log.d("FACEBOOK", post.getMessage());
                        }
                        if (post.getName() != null) {
                            Log.d("FACEBOOK", post.getName());
                        }
                        if (post.getCreatedTime() != null) {
                            Log.d("FACEBOOK", post.getCreatedTime() + "");
                        }
                        if (post.getLikesCount() != null) {
                            Log.d("FACEBOOK", post.getLikesCount() + "");
                        }
                    }*/
                }
                timelineSerializerable.serialize(postList);
            }
        }).start();
        // 나의 타임라인에서 포스트들을 가져온다.
    }
        public interface TimelineSerializerable {
            public void serialize(List<Post> posts);
    }
}
