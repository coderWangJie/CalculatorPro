package cn.wangj.calcpro;

import android.content.Intent;
import android.view.View;

import java.math.BigDecimal;

import butterknife.OnClick;

public class MainActivity extends BaseActivity implements View.OnClickListener {

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
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_creditLie:
                Intent intent = new Intent(this, CreditCashActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void calculator(int amount, double fee) {
        BigDecimal bigAmount = new BigDecimal(amount);
        BigDecimal bigFee = new BigDecimal(fee);

        bigAmount = bigAmount.multiply(bigFee);
    }

}
