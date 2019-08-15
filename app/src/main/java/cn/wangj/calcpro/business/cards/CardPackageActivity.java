package cn.wangj.calcpro.business.cards;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import butterknife.BindView;
import cn.wangj.calcpro.BaseActivity;
import cn.wangj.calcpro.R;

/**
 * 卡包
 */
public class CardPackageActivity extends BaseActivity {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.viewPage)
    ViewPager viewPager;

    FragmentManager fragmentManager;

    @Override
    protected int setContentResID() {
        return R.layout.activity_card_package;
    }

    @Override
    protected void runOnCreate() {
        setTitle("我的卡包");

        fragmentManager = getSupportFragmentManager();
    }

    @Override
    protected void runOnResume() {
        int mode = getIntent().getIntExtra(CardPkgOpenMode.ModeName, -1);

        if (mode == CardPkgOpenMode.Normal) {

        } else if (mode == CardPkgOpenMode.Choose) {

        } else if (mode == CardPkgOpenMode.ChooseDebit) {

        } else if (mode == CardPkgOpenMode.ChooseCredit) {

        }
    }

}
