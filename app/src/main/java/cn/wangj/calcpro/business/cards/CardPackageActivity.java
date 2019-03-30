package cn.wangj.calcpro.business.cards;

import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import cn.wangj.calcpro.BaseActivity;
import cn.wangj.calcpro.R;


public class CardPackageActivity extends BaseActivity {

    @BindView(R.id.recycleView)
    RecyclerView recyclerView;

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
        int mode = getIntent().getIntExtra(CardPkgOpenMode.ModeName, -1);

        if (mode == CardPkgOpenMode.Normal) {

        } else if (mode == CardPkgOpenMode.Choose) {

        } else if (mode == CardPkgOpenMode.ChooseDebit) {

        } else if (mode == CardPkgOpenMode.ChooseCredit) {

        }

        recyclerView.setAdapter(null);
    }

}
