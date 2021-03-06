package cn.wangj.calcpro.business.credit;

import android.view.View;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;
import cn.wangj.calcpro.BaseActivity;
import cn.wangj.calcpro.R;
import cn.wangj.calcpro.util.Calculator;
import cn.wangj.baslib.utils.MoneyUtil;

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

    private double amount, feeRate;
    private int lieYear, lieMonth, lieDay; // 记录选择的刷卡日期
    private int billDay, deadLine; // 账单日、最后还款日

    @Override
    protected int setContentResID() {
        return R.layout.activity_credit_cash_result;
    }

    @Override
    protected void runOnCreate() {
        setTitle("测算结果");

        amount = Double.valueOf(getIntent().getStringExtra("amount"));
        feeRate = Double.valueOf(getIntent().getStringExtra("feeRate"));
        lieYear = getIntent().getIntExtra("lieYear", 0);
        lieMonth = getIntent().getIntExtra("lieMonth", 0);
        lieDay = getIntent().getIntExtra("lieDay", 0);
        billDay = getIntent().getIntExtra("billDay", 0);
        deadLine = getIntent().getIntExtra("deadLine", 0);

        // 计算手续费
        BigDecimal feeBigD = new BigDecimal(amount)
                .multiply(new BigDecimal(feeRate))
                .divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
        double fee = feeBigD.doubleValue();
        tvFee.setText(MoneyUtil.formatMoney2Show(fee));

        // 计算到手金额
        double result = new BigDecimal(amount).subtract(feeBigD).setScale(2, RoundingMode.HALF_UP).doubleValue();
        tvResult.setText(MoneyUtil.formatMoney2Show(result));

        // 计算免息天数
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(lieYear, lieMonth, lieDay);
        int days = Calculator.countDaysFromPaymentToDeadLine(billDay, deadLine, calendar);
        tvDays.setText(String.valueOf(days));

        // 计算用款成本
        BigDecimal costBigD = new BigDecimal(feeRate)
                .multiply(new BigDecimal(365))
                .divide(new BigDecimal(days), 2, RoundingMode.HALF_UP);

        tvTrueInterest.setText(String.format("%s%%", costBigD.doubleValue()));
    }

    @Override
    protected void runOnResume() {

    }

    @OnClick({R.id.btn_tryAgain, R.id.btn_finish})
    public void handleClick(View view) {
        switch (view.getId()) {
            case R.id.btn_tryAgain:
                // 再算一次
                setResult(RESULT_OK);
                finish();
                break;

            case R.id.btn_finish:
                // 完成
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        setResult(RESULT_CANCELED);
        finish();
    }
}
