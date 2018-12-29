package cn.wangj.calcpro.business;

import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import cn.wangj.calcpro.BaseActivity;
import cn.wangj.calcpro.R;

public class CreditCashActivity extends BaseActivity {

    /** 套现金额 */
    @BindView(R.id.et_amount)
    EditText etAmount;

    // TODO 划款日期
    /** 套现日期 */

    /** 套现手续费，单位% */
    @BindView(R.id.et_fee)
    EditText etFee;

    /** “从卡包中选择”按钮 */
    @BindView(R.id.btn_creditChoose)
    Button btnChooseCard;

    @Override
    protected int setContentResID() {
        return R.layout.activity_credit_cash;
    }

    @Override
    protected void runOnCreate() {

    }

    @Override
    protected void runOnResume() {
        btnChooseCard.setText("");
    }

}
