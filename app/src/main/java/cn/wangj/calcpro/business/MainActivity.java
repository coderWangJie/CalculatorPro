package cn.wangj.calcpro.business;

import android.content.Intent;
import android.view.View;

import butterknife.OnClick;
import cn.wangj.calcpro.BaseActivity;
import cn.wangj.calcpro.R;
import cn.wangj.calcpro.business.cards.CardPackageActivity;
import cn.wangj.calcpro.business.credit.CreditCashActivity;
import cn.wangj.calcpro.business.setting.SettingActivity;
import cn.wangj.calcpro.business.trivia.TriviaListActivity;

public class MainActivity extends BaseActivity {


    @Override
    protected int setContentResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void runOnCreate() {

    }

    @Override
    protected void runOnResume() {
    }

    @OnClick({R.id.btn_creditLie, R.id.btn_cardPackage, R.id.btn_trivia, R.id.btn_setting, R.id.btn_about})
    public void handlerOnClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_creditLie:
                intent = new Intent(this, CreditCashActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_cardPackage:
                intent = new Intent(this, CardPackageActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_trivia:
                intent = new Intent(this, TriviaListActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_setting:
                intent = new Intent(this, SettingActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_about:
                intent = new Intent(this, AppInfoActivity.class);
                startActivity(intent);
                break;
        }
    }

}
