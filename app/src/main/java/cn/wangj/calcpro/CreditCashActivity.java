package cn.wangj.calcpro;

import android.widget.EditText;

import butterknife.BindView;

public class CreditCashActivity extends BaseActivity {

    @BindView(R.id.et_amount)
    EditText etAmount;

    @BindView(R.id.et_fee)
    EditText etFee;

//    @BindView(R.id.et_amount)
//    EditText etAmount;
//
//    @BindView(R.id.et_amount)
//    EditText etAmount;
//
//    @BindView(R.id.et_amount)
//    EditText etAmount;
//
//    @BindView(R.id.et_amount)
//    EditText etAmount;

    @Override
    protected int setContentResID() {
        return R.layout.activity_credit_cash;
    }

    @Override
    protected void runOnCreate() {

    }

    @Override
    protected void runOnResume() {

    }

}
