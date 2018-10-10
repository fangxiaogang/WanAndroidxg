package com.xiaogang.com.wanandroid_xg.ui.project;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.xiaogang.com.wanandroid_xg.R;
import com.xiaogang.com.wanandroid_xg.base.BaseFragment;
import com.xiaogang.com.wanandroid_xg.bean.Project;
import com.xiaogang.com.wanandroid_xg.ui.home.HomeFragment;
import com.xiaogang.com.wanandroid_xg.ui.main.MainFragment;
import com.xiaogang.com.wanandroid_xg.ui.search.SearchFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author: fangxiaogang
 * date: 2018/9/19
 */

public class ProjectFragment extends BaseFragment<ProjectPresenter> implements ProjectContract.View {

    @BindView(R.id.mTabLayout)
    TabLayout mTabLayout;

    @BindView(R.id.mViewpager)
    ViewPager mViewpager;

    @BindView(R.id.homesearch)
    ImageView mhomesearch;

    private ProjectAdapter mprojectAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project;
    }

    @Override
    protected void initInjector() {
      mFragmentComponent.inject(this);
    }

    @Override
    protected void initView(View view) {
        mPresenter.getProjectDate();
        mhomesearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainFragment) getParentFragment()).startBrotherFragment(SearchFragment.newInstance());
            }
        });
    }

    @Override
    public void setProjectDate(List<Project> projects) {
        List<Integer> ids = new ArrayList<>();
        List<String> names = new ArrayList<>();
        if (projects.size()> 0) {
            for (Project project: projects){
                ids.add(project.getId());
                names.add(project.getName());
            }
        }

        mprojectAdapter = new ProjectAdapter(getChildFragmentManager(), names,ids);
        mViewpager.setAdapter(mprojectAdapter);
        mViewpager.setOffscreenPageLimit(1);
        mViewpager.setCurrentItem(0, false);
        mTabLayout.setupWithViewPager(mViewpager, true);
    }


    public static ProjectFragment newInstance() {
        Bundle args = new Bundle();
        ProjectFragment fragment = new ProjectFragment();
        fragment.setArguments(args);
        return fragment;
    }



    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;
    /**
     * 处理回退事件
     *
     * @return
     */
    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            _mActivity.finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(_mActivity, "再按一次退出", Toast.LENGTH_SHORT).show();
        }
        return true;
    }


    public class ProjectAdapter extends FragmentStatePagerAdapter {
        private List<String> titles;
        private List<Integer> ids;
        private List<ArticleFragment> articleFragments = new ArrayList<>();
        public ProjectAdapter(FragmentManager fm, List<String> titles,List<Integer> ids) {
            super(fm);
            this.titles = titles;
            this.ids = ids;

            for(Integer id : ids) {
                articleFragments.add( ArticleFragment.newInstance(id));
            }
        }

        @Override
        public BaseFragment getItem(int position) {
            return articleFragments.get(position);
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


}
