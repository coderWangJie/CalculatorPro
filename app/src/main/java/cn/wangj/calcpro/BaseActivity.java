package cn.wangj.calcpro;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.wangj.calcpro.util.Logger;

public abstract class BaseActivity extends AppCompatActivity {
    protected String TAG;

    private Toast toast;

    private Unbinder unbinder;

    protected abstract int setContentResID();

    protected abstract void runOnCreate();

    protected abstract void runOnResume();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getClass().getSimpleName();
        Logger.i(TAG, TAG + ">>onCreate");

        if (setContentResID() > 0) {
            setContentView(setContentResID());
        } else {
            Logger.e(TAG, "This page have not set content layout-resource!");
            setContentView(R.layout.layout_null);
        }

        // ButterKnife注册
        unbinder = ButterKnife.bind(this);

        runOnCreate();
    }

    @Override
    protected void onResume() {
        super.onResume();

        runOnResume();
    }

    /**
     * 自定义外观Toast
     * @param toastMsg Toast文字
     */
    protected void toastShort(@NonNull CharSequence toastMsg) {
        if (toast == null) {
            RelativeLayout view = (RelativeLayout) getLayoutInflater().inflate(R.layout.view_toast, null);
            toast = new Toast(this);
            toast.setView(view);
        }
        toast.setText(toastMsg); // R.layout.view_toast中控件ID设置为系统控件一样的ID，可以使用setText()方法，否则不能使用该方法设置文字
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
