package cn.wangj.calcpro;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import cn.wangj.calcpro.util.Logger;

public abstract class BaseActivity extends AppCompatActivity {
    protected String TAG;

    protected abstract int setContentResID();

    protected abstract void runOnCreate();

    protected abstract void runOnResume();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getClass().getSimpleName();

        if (setContentResID() > 0) {
            setContentView(setContentResID());
        } else {
            Logger.e(TAG, "This page have not set content layout-resource!");
            setContentView(R.layout.layout_null);
        }

        // ButterKnife注册
        ButterKnife.bind(this);

        runOnCreate();
    }

    @Override
    protected void onResume() {
        super.onResume();

        runOnResume();
    }
}
