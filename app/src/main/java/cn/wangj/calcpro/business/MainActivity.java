package cn.wangj.calcpro.business;

import android.content.Intent;
import android.view.View;

import butterknife.OnClick;
import cn.wangj.calcpro.BaseActivity;
import cn.wangj.calcpro.R;
import cn.wangj.calcpro.business.credit.CreditCashActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {


//    TextToSpeech textToSpeech;

    @Override
    protected int setContentResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void runOnCreate() {
//        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
//            @Override
//            public void onInit(int status) {
//                if (status == TextToSpeech.SUCCESS) {
//                    int result = textToSpeech.setLanguage(Locale.CHINA);
//                    if (result == TextToSpeech.LANG_MISSING_DATA
//                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
//                        Toast.makeText(MainActivity.this, "暂不支持中文",Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//        });
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

//                String text = "易收宝到账105.50元";
//                textToSpeech.setPitch(1.5f); // 在系统设置里也可以修改音调
//                textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                break;
        }
    }

}
