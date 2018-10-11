package idwall.iddog.ui.signin;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import idwall.iddog.BaseActivity;
import idwall.iddog.R;
import idwall.iddog.databinding.ActivitySigninBinding;
import idwall.iddog.ui.BreedsActivity;
import idwall.iddog.utils.Validator;

public class SignInActivity extends BaseActivity {

    private SignInViewModel signinViewModel;

    private MutableLiveData<SignInViewModel.SignInEvent> signInEventMutableLiveData;

    private TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySigninBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_signin);

        // Inicializa View Model
        signinViewModel = new SignInViewModel();
        signinViewModel.init(this);

        // Binding views
        email = binding.email;
    }

    public void signIn(View view) {
        if(!Validator.isEmailValid(email.getText().toString())) {
            showErrorMessage("Email inválido");
            return;
        }

        showLoading();

        signInEventMutableLiveData = signinViewModel.onSignIn(email.getText().toString());
        signInEventMutableLiveData.observe(this, new Observer<SignInViewModel.SignInEvent>() {
            @Override
            public void onChanged(@Nullable SignInViewModel.SignInEvent signInEvent) {
                if(signInEvent == SignInViewModel.SignInEvent.SignInOk) {
                    hideLoading();

                    Intent intent = new Intent(SignInActivity.this, BreedsActivity.class);
                    startActivity(intent);

                    finish();
                } else {
                    hideLoading();

                    showErrorMessage("Erro de autenticação");
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        signInEventMutableLiveData.removeObservers(this);
    }

    private void showErrorMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

}
