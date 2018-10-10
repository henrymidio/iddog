package idwall.iddog.ui.signin;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

public class SignInViewModel extends ViewModel {

    protected MutableLiveData<SignInEvent> performSignInEvent = new MutableLiveData<SignInEvent>();

    enum SignInEvent {
        RequestSignIn
    }

    public void onSignIn() {
        performSignInEvent.postValue(SignInEvent.RequestSignIn);
    }

}
