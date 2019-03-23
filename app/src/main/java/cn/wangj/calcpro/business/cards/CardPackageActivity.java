package cn.wangj.calcpro.business.cards;

import cn.wangj.calcpro.BaseActivity;
import cn.wangj.calcpro.R;


public class CardPackageActivity extends BaseActivity {

    @Override
    protected int setContentResID() {
        return R.layout.activity_card_package;
    }

    @Override
    protected void runOnCreate() {
        setTitle("我的卡包");
    }

    @Override
    protected void runOnResume() {
        int type = getIntent().getIntExtra(Type.TypeName, -1);

    }

}
