package cn.wangj.calcpro.business.credit;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import cn.wangj.calcpro.BaseActivity;
import cn.wangj.calcpro.R;
import cn.wangj.calcpro.util.Logger;
import cn.wangj.calcpro.util.StringUtil;

public class CreditCashActivity extends BaseActivity {

    /** 提现金额 */
    @BindView(R.id.et_amount)
    EditText etAmount;

    /** 提现日期 */
    @BindView(R.id.tv_lieDay)
    TextView tvLieDay;

    /** 提现手续费，单位% */
    @BindView(R.id.et_fee)
    EditText etFee;

    /** 信用卡账单日 */
    @BindView(R.id.tv_billDay)
    TextView tvBillDay;

    /** 最后还款日 */
    @BindView(R.id.tv_deadLine)
    TextView tvDeadLine;

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

        Calendar today = Calendar.getInstance();
        lieYear = today.get(Calendar.YEAR);
        lieMonth = today.get(Calendar.MONTH);
        lieDay = today.get(Calendar.DAY_OF_MONTH);
        modifyValue(tvLieDay, "今天");
    }

    @Override
    protected void runOnResume() {
    }

    @OnClick({ R.id.tv_lieDay, R.id.tv_billDay, R.id.tv_deadLine, R.id.img_creditChoose, R.id.btn_calculate})
    public void handlerClick(View v) {
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
                                modifyValue(tvLieDay, String.format(Locale.getDefault(), "%d-%d-%d", lieYear, lieMonth + 1, lieDay));
                            }
                        }, lieYear, lieMonth, lieDay);
                dialog.show();
                break;

            case R.id.tv_billDay:
                showSingleSelectDialog(this, "请选择每月账单日", temp,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                billDay = which + 1;
                                modifyValue(tvBillDay, String.format(Locale.getDefault(), "每月%d日", billDay));
                            }
                        });
                break;

            case R.id.tv_deadLine:
                showSingleSelectDialog(this, "请选择每月最后还款日", temp,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deadLine = which + 1;
                                modifyValue(tvDeadLine, String.format(Locale.getDefault(), "每月%d日", deadLine));
                            }
                        });
                break;

            case R.id.img_creditChoose:
                toastShort("正在开发中...请稍作等待！");
                break;

            case R.id.btn_calculate:
                if (StringUtil.isEmpty(etAmount.getText())) {
                    toastShort("请输入计划划款金额");
                    return;
                }

                if (StringUtil.isEmpty(etFee.getText())) {
                    toastShort("请输入提现手续费");
                    return;
                }

                if (billDay == 0) {
                    toastShort("请选择您的信用卡账单日");
                    return;
                }

                if (deadLine == 0) {
                    toastShort("请选择您的信用卡最后还款日");
                    return;
                }

                if (lieDay == billDay) {
                    final AlertDialog alertDialog = new AlertDialog.Builder(this)
                            .setTitle("温馨提示")
                            .setMessage("您计划在账单日当日进行划款，有些银行会将账单日的消费算入当期账单，所以建议您在账单日第二天进行消费。")
                            .setNegativeButton("修改一下", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .setPositiveButton("已知晓", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(CreditCashActivity.this, CreditCashResultActivity.class);
                                    intent.putExtra("amount", etAmount.getText().toString());
                                    intent.putExtra("feeRate", etFee.getText().toString());
                                    intent.putExtra("lieYear", lieYear);
                                    intent.putExtra("lieMonth", lieMonth);
                                    intent.putExtra("lieDay", lieDay);
                                    intent.putExtra("billDay", billDay);
                                    intent.putExtra("deadLine", deadLine);
                                    startActivityForResult(intent, 200);
                                }
                            })
                            .create();
                    alertDialog.show();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200) {
            if (resultCode == 201) {
                Logger.d(TAG, "!!!");
            } else {
                finish();
            }
        }
    }

    private void showSingleSelectDialog(Context context, CharSequence title,
                                        CharSequence[] items, DialogInterface.OnClickListener listener) {
        AlertDialog deadLineSelector = new AlertDialog.Builder(context)
                .setTitle(title)
                .setItems(items, listener).create();

        deadLineSelector.show();

        WindowManager.LayoutParams params2 = deadLineSelector.getWindow().getAttributes();
        params2.width = 900;
        params2.height = 1500;
        deadLineSelector.getWindow().setAttributes(params2);
    }

    /**
     * 更新值后改变外观
     */
    private void modifyValue(TextView textView, String value) {
        textView.setTextColor(getResources().getColor(R.color.text_done));
        textView.setText(value);
    }

}
