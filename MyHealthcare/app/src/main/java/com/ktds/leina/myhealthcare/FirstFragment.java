package com.ktds.leina.myhealthcare;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.ktds.leina.myhealthcare.Fisrt.WaterImgFragment;
import com.ktds.leina.myhealthcare.Fisrt.WaterTextFragment;

public class FirstFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FirstFragment() {
        // Required empty public constructor
    }

  public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private ViewPager pager;
    private PagerSlidingTabStrip tabs;

    private Fragment WaterImgFragment;
    private Fragment WaterTextFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_first, container, false);

        WaterImgFragment = new WaterImgFragment();
        WaterTextFragment = new WaterTextFragment();

        // View Pager를 선언합니다.
        pager = (ViewPager) view.findViewById(R.id.pager);
        pager.setAdapter(new PagerAdapter(getChildFragmentManager()));
        pager.setOffscreenPageLimit(2);
        pager.setCurrentItem(0);

        // 처음으로 0번째 Fragment를 보여줍니다.
        pager.setCurrentItem(0);

        tabs = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
        tabs.setViewPager(pager);

        // Title을 설정합니다.
        getActivity().setTitle("물이 어떻길래?");

        return view;
    }

    private  String[] pageTitle = {"물에 대해서", "물의 아름다움"};

    private class PagerAdapter extends FragmentPagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return pageTitle[position];
        }

        /**
         * View Pager의 Fragment 들은 각각 Index를 가진다.
         * Android OS로 부터 요청된 Pager의 Index를 보내주면,
         * 해당되는 Fragment를 리턴시킨다.
         *
         * @param position
         * @return
         */
        @Override
        public Fragment getItem(int position) {

            if (position == 0) {
                return WaterImgFragment;
            } else {
                return WaterTextFragment;
            }
        }

        /**
         * View Pager에 몇개의 Fragment가 들어가는지 설정
         *
         * @return
         */
        @Override
        public int getCount() {
            return 2;
        }
    }

}
