package cn.wangj.calcpro.business;

import android.content.Intent;
import android.view.View;

import butterknife.OnClick;
import cn.wangj.calcpro.BaseActivity;
import cn.wangj.calcpro.R;
import cn.wangj.calcpro.business.credit.CreditCashActivity;

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

    @OnClick(R.id.btn_creditLie)
    public void handlerOnClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_creditLie:
                intent = new Intent(this, CreditCashActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_trivia:
                intent = null;
                break;
        }
    }

}
