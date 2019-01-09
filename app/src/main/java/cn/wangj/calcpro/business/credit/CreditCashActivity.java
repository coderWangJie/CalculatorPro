package cn.wangj.calcpro.business.credit;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;
import cn.wangj.calcpro.BaseActivity;
import cn.wangj.calcpro.R;

public class CreditCashActivity extends BaseActivity implements View.OnClickListener {

    /** 套现金额 */
    @BindView(R.id.et_amount)
    EditText etAmount;

    /** 套现日期 */
    @BindView(R.id.tv_lieDay)
    TextView tvLieDay;

    /** 套现手续费，单位% */
    @BindView(R.id.et_fee)
    EditText etFee;

    /** 信用卡账单日 */
    @BindView(R.id.tv_billDay)
    TextView tvBillDay;

    /** 最后还款日 */
    @BindView(R.id.tv_deadLine)
    TextView tvDeadLine;

    @BindView(R.id.tv_tips)
    TextView tvTips;

//    /** “从卡包中选择”按钮 */
//    @BindView(R.id.btn_creditChoose)
//    Button btnChooseCard;

    private String[] temp;
    private int lieYear, lieMonth, lieDay; // 记录选择的刷卡日期
    private int billDay, deadLine; // 账单日、最后还款日

    @Override
    protected int setContentResID() {
        return R.layout.activity_credit_cash;
    }

    @Override
    protected void runOnCreate() {
        temp = getResources().getStringArray(R.array.billDays);
    }

    @Override
    protected void runOnResume() {
    }

    @OnClick({ R.id.tv_lieDay, R.id.tv_billDay, R.id.tv_deadLine, R.id.btn_calculate})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_lieDay:
                Calendar today = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(this,
                        android.app.AlertDialog.THEME_DEVICE_DEFAULT_LIGHT,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                lieYear = year;
                                lieMonth = month;
                                lieDay = dayOfMonth;
                                tvLieDay.setText(lieYear + "-" + (lieMonth + 1) + "-" + lieDay);
                            }
                        }, today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH));
                dialog.show();
                break;

            case R.id.tv_billDay:
                AlertDialog billDaySelector = new AlertDialog.Builder(this)
                        .setTitle("请选择每月账单日")
                        .setItems(temp, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                billDay = which + 1;
                                tvBillDay.setText("每月" + billDay + "日");
                            }
                        }).create();


                billDaySelector.show();

                WindowManager.LayoutParams params1 = billDaySelector.getWindow().getAttributes();
                params1.width = 900;
                params1.height = 1500;
                billDaySelector.getWindow().setAttributes(params1);
                break;

            case R.id.tv_deadLine:
                AlertDialog deadLineSelector = new AlertDialog.Builder(this)
                        .setTitle("请选择每月最后还款日")
                        .setItems(temp, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deadLine = which + 1;
                                tvDeadLine.setText("每月" + deadLine + "日");
                            }
                        }).create();


                deadLineSelector.show();

                WindowManager.LayoutParams params2 = deadLineSelector.getWindow().getAttributes();
                params2.width = 900;
                params2.height = 1500;
                deadLineSelector.getWindow().setAttributes(params2);
                break;

            case R.id.btn_calculate:
                Intent intent = new Intent(this, CreditCashResultActivity.class);
                intent.putExtra("amount", etAmount.getText().toString());
                intent.putExtra("feeRate", etFee.getText().toString());
                intent.putExtra("lieYear", lieYear);
                intent.putExtra("lieMonth", lieMonth);
                intent.putExtra("lieDay", lieDay);
                intent.putExtra("billDay", billDay);
                intent.putExtra("deadLine", deadLine);
                startActivity(intent);
                break;
        }
    }

}
