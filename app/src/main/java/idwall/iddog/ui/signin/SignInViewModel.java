package idwall.iddog.ui.signin;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import idwall.iddog.data.AuthRepository;
import idwall.iddog.data.local.prefs.PreferencesHelper;
import idwall.iddog.data.model.User;

public class SignInViewModel extends ViewModel {

    private AuthRepository authRepository;

    private Context context;

    public MediatorLiveData<SignInEvent> signInState = new MediatorLiveData<>();

    public enum SignInEvent {
        SignInOk, SignInError
    }

    public void init(Context context) {
        this.context = context;
        authRepository = new AuthRepository(context);
    }

    public void onSignIn(String email) {

        // Faz o request de login
        MutableLiveData<User> signInEventMutableLiveData = authRepository.doSignInApiCall(email);

        // Salva token
        signInState.addSource(signInEventMutableLiveData, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {

                // Remove source para evitar duplicação
                signInState.removeSource(signInEventMutableLiveData);

                if(user != null) {
                    PreferencesHelper.setUserToken(context, user.getToken());
                    signInState.postValue(SignInEvent.SignInOk);
                } else {
                    signInState.postValue(SignInEvent.SignInError);
                }
            }
        });

    }

}
