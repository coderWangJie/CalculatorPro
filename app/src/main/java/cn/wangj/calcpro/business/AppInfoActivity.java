package cn.wangj.calcpro.business;

import android.widget.TextView;

import butterknife.BindView;
import cn.wangj.calcpro.BaseActivity;
import cn.wangj.calcpro.R;
import cn.wangj.calcpro.util.AndroidUtil;

public class AppInfoActivity extends BaseActivity {

    @BindView(R.id.tv_appVersion)
    TextView tvAppVersion;

    @Override
    protected int setContentResID() {
        return R.layout.activity_app_info;
    }

    @Override
    protected void runOnCreate() {
    }

    @Override
    protected void runOnResume() {
        tvAppVersion.setText(AndroidUtil.getAppVersionName(this));
    }
}
