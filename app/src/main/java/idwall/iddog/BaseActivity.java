package idwall.iddog;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

import idwall.iddog.utils.CommonUtils;

public abstract class BaseActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;

    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this);
    }

}