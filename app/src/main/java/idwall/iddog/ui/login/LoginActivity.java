package idwall.iddog.ui.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import idwall.iddog.R;
import idwall.iddog.ui.BreedsActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void signin(View view) {
        Intent intent = new Intent(this, BreedsActivity.class);
        startActivity(intent);
    }
}
