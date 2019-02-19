package cn.wangj.calcpro.business.setting;

import cn.wangj.calcpro.BaseActivity;
import cn.wangj.calcpro.R;

public class SettingActivity extends BaseActivity {

    @Override
    protected int setContentResID() {
        return R.layout.activity_setting;
    }

    @Override
    protected void runOnCreate() {
        setTitle("应用设置");
    }

    @Override
    protected void runOnResume() {

    }

}
