package cn.wangj.calcpro.business.credit;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import cn.wangj.calcpro.BaseActivity;
import cn.wangj.calcpro.R;

public class CreditCashActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 套现金额
     */
    @BindView(R.id.et_amount)
    EditText etAmount;

    /**
     * 套现日期
     */
    @BindView(R.id.tv_lieDay)
    TextView tvLieDay;

    /**
     * 套现手续费，单位%
     */
    @BindView(R.id.et_fee)
    EditText etFee;

    /**
     * 信用卡账单日
     */
    @BindView(R.id.tv_billDay)
    TextView tvBillDay;

    /**
     * 最后还款日
     */
    @BindView(R.id.tv_deadLine)
    TextView tvDeadLine;

    /**
     * “从卡包中选择”按钮
     */
    @BindView(R.id.btn_creditChoose)
    Button btnChooseCard;

    private String[] temp;
    private int lieYear, lieMonth, lieDay; // 记录选择的刷卡日期

    @Override
    protected int setContentResID() {
        return R.layout.activity_credit_cash;
    }

    @Override
    protected void runOnCreate() {

    }

    @Override
    protected void runOnResume() {
        temp = getResources().getStringArray(R.array.billDays);
    }

    @OnClick({ R.id.tv_lieDay, R.id.tv_billDay, R.id.tv_deadLine, R.id.btn_calculate})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_lieDay:
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
                        }, 2019, 0, 3);
                dialog.show();
                break;

            case R.id.tv_billDay:
                AlertDialog listDialog = new AlertDialog.Builder(this)
                        .setTitle("请选择每月最后还款日")
                        .setItems(temp, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(CreditCashActivity.this,
                                        "你点击了" + temp[which],
                                        Toast.LENGTH_SHORT).show();
                            }
                        }).create();


                listDialog.show();

                WindowManager.LayoutParams params = listDialog.getWindow().getAttributes();
                params.width = 900;
                params.height = 1500;
                listDialog.getWindow().setAttributes(params);
                break;

            case R.id.btn_calculate:

//                Calendar cal = Calendar.getInstance();
//                cal.clear();
//                String today = etToday.getText().toString();
//                cal.set(Integer.parseInt(today.substring(0, 4)),
//                        Integer.parseInt(today.substring(4, 6)) - 1,
//                        Integer.parseInt(today.substring(6, 8)));
//
//                Calculator.countDaysFromPaymentToDeadLine(Integer.parseInt(etBillDay.getText().toString()),
//                        Integer.parseInt(etDeadLine.getText().toString()),
//                        cal);

                break;
        }
    }

}
