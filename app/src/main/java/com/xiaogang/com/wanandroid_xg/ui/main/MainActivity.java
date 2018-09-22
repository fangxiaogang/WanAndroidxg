package com.xiaogang.com.wanandroid_xg.ui.main;

import android.view.KeyEvent;

import com.blankj.utilcode.util.ToastUtils;
import com.chaychan.library.BottomBarItem;
import com.chaychan.library.BottomBarLayout;
import com.xiaogang.com.wanandroid_xg.R;
import com.xiaogang.com.wanandroid_xg.SupportFragment;
import com.xiaogang.com.wanandroid_xg.base.BaseActivity;
import com.xiaogang.com.wanandroid_xg.ui.home.HomeFragment;
import com.xiaogang.com.wanandroid_xg.ui.knowledge.KnowledgeFragment;
import com.xiaogang.com.wanandroid_xg.ui.mine.MineFragment;
import com.xiaogang.com.wanandroid_xg.ui.project.ProjectFragment;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

//    @BindView(R.id.bottombar)
//    BottomBarLayout mbottomBarLayout;
//
//    private SupportFragment[] mFragments = new SupportFragment[4];

    private long mExitTime;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        if (findFragment(MainFragment.class) == null) {
            loadRootFragment(R.id.mhoneframeLayout, MainFragment.newInstance());
        }
//        SupportFragment homeFragment = findFragment(HomeFragment.class);
//        if (homeFragment == null) {
//            mFragments[0] = HomeFragment.newInstance();
//            mFragments[1] = KnowledgeFragment.newInstance();
//            mFragments[2] = ProjectFragment.newInstance();
//            mFragments[3] = MineFragment.newInstance();
//            loadMultipleRootFragment(R.id.layout_fragment, 0,
//                    mFragments[0],
//                    mFragments[1],
//                    mFragments[2],
//                    mFragments[3]
//            );
//        } else {
//            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题
//
//            // 这里我们需要拿到mFragments的引用
//            mFragments[0] = homeFragment;
//            mFragments[1] = findFragment(KnowledgeFragment.class);
//            mFragments[2] = findFragment(ProjectFragment.class);
//            mFragments[3] = findFragment(MineFragment.class);
//        }
//
//
//        mbottomBarLayout.setOnItemSelectedListener(new BottomBarLayout.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(BottomBarItem bottomBarItem, int before, int current) {
//                showHideFragment(mFragments[current], mFragments[before]);
//
////                ToastUtils.showShort("ss"+ before +  "sss" + current );
//            }
//        });
    }

    @Override
    protected void initInjector() {

    }





}
