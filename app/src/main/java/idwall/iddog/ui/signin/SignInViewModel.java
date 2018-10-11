package idwall.iddog.ui.signin;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;

import idwall.iddog.data.AuthRepository;

public class SignInViewModel extends ViewModel {

    private AuthRepository authRepository;

    public enum SignInEvent {
        SignInOk, SignInError
    }

    public void init(Context context) {
        authRepository = new AuthRepository(context);
    }

    public MutableLiveData<SignInEvent> onSignIn(String email) {

        return authRepository.doSignInApiCall(email);

    }

}
