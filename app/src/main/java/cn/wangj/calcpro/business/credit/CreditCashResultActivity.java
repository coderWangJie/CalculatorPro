package cn.wangj.calcpro.business.credit;

import android.widget.TextView;

import butterknife.BindView;
import cn.wangj.calcpro.BaseActivity;
import cn.wangj.calcpro.R;

public class CreditCashResultActivity extends BaseActivity {

    /**  */
    @BindView(R.id.tv_fee)
    TextView tvFee;

    /**  */
    @BindView(R.id.tv_result)
    TextView tvResult;

    /**  */
    @BindView(R.id.tv_days)
    TextView tvDays;
    /**  */
    @BindView(R.id.tv_interest)
    TextView tvTrueInterest;

    @Override
    protected int setContentResID() {
        return R.layout.activity_credit_cash_result;
    }

    @Override
    protected void runOnCreate() {

    }

    @Override
    protected void runOnResume() {

    }

}
