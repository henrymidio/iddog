package idwall.iddog.ui.signin;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import idwall.iddog.R;
import idwall.iddog.databinding.ActivitySigninBinding;
import idwall.iddog.ui.BreedsActivity;

public class SignInActivity extends AppCompatActivity {

    private SignInViewModel signinViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySigninBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_signin);

        // Inicializa View Model
        signinViewModel = new SignInViewModel();

        // Realiza Bindings
        binding.setViewModel(signinViewModel);
    }


}
