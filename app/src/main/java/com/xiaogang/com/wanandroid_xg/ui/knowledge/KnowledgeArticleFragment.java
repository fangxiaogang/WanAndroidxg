package com.xiaogang.com.wanandroid_xg.ui.knowledge;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaogang.com.wanandroid_xg.R;
import com.xiaogang.com.wanandroid_xg.base.BaseFragment;
import com.xiaogang.com.wanandroid_xg.bean.Knowledge;
import com.xiaogang.com.wanandroid_xg.ui.login.LoginFragment;
import com.xiaogang.com.wanandroid_xg.ui.main.MainFragment;
import com.xiaogang.com.wanandroid_xg.ui.project.ArticleFragment;
import com.xiaogang.com.wanandroid_xg.ui.search.SearchFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author: fangxiaogang
 * date: 2018/9/29
 */

public class KnowledgeArticleFragment extends BaseFragment{

    @BindView(R.id.mTabLayout)
    TabLayout mTabLayout;

    @BindView(R.id.mViewpager)
    ViewPager mViewpager;

    @BindView(R.id.knowledgeName)
    TextView knowledgeName;

    @BindView(R.id.homesearch)
    ImageView mhomesearch;

    private KnowledgeArticleAdapter mknowledgeArticleAdapter;

    List<Integer> ids = new ArrayList<>();
    List<String> names = new ArrayList<>();
    String knowledgename;

    private static final String KNOWLEDGE = "knowledge";
    private static final String KNOWLEDGENAME = "knowledgename";

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_knowledgearticle;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        names = getArguments().getStringArrayList("names");
        ids = getArguments().getIntegerArrayList("ids");
        knowledgename = getArguments().getString(KNOWLEDGENAME);
    }

    @Override
    protected void initView(View view) {
        mknowledgeArticleAdapter = new KnowledgeArticleAdapter(getChildFragmentManager(), names,ids);
        mViewpager.setAdapter(mknowledgeArticleAdapter);
        mViewpager.setOffscreenPageLimit(1);
        mViewpager.setCurrentItem(0, false);
        mTabLayout.setupWithViewPager(mViewpager, true);

        knowledgeName.setText(knowledgename);
        mhomesearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainFragment) getParentFragment()).startBrotherFragment(SearchFragment.newInstance());
            }
        });
    }


    public static KnowledgeArticleFragment newInstance(List<String> names,List<Integer> ids,String knowledgename) {
        Bundle args = new Bundle();
        KnowledgeArticleFragment fragment = new KnowledgeArticleFragment();
        args.putStringArrayList("names", (ArrayList<String>) names);
        args.putIntegerArrayList("ids", (ArrayList<Integer>) ids);
        args.putString(KNOWLEDGENAME,knowledgename);
        fragment.setArguments(args);
        return fragment;
    }

    public class KnowledgeArticleAdapter extends FragmentStatePagerAdapter {
        private List<String> titles;
        private List<Integer> ids;
        private List<KnowledgeLIstFragment> knowledgeLIstFragments = new ArrayList<>();
        public KnowledgeArticleAdapter(FragmentManager fm, List<String> titles, List<Integer> ids) {
            super(fm);
            this.titles = titles;
            this.ids = ids;

            for(Integer id : ids) {
                knowledgeLIstFragments.add( KnowledgeLIstFragment.newInstance(id));
            }
        }

        @Override
        public BaseFragment getItem(int position) {
            return knowledgeLIstFragments.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }

        @Override
        public int getCount() {
            return titles.size();
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

    }

    public void startBrotherFragment(com.xiaogang.com.wanandroid_xg.SupportFragment targetFragment) {
        start(targetFragment);
    }

}
