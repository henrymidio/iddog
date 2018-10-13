package idwall.iddog.ui.signin;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;

import idwall.iddog.data.AuthRepository;
import idwall.iddog.data.local.prefs.PreferencesHelper;
import idwall.iddog.data.model.User;

public class SignInViewModel extends ViewModel {

    private AuthRepository authRepository;

    private Context context;

    public enum SignInEvent {
        SignInOk, SignInError
    }

    public void init(Context context) {
        this.context = context;
        authRepository = new AuthRepository(context);
    }

    public LiveData<SignInEvent> onSignIn(String email) {

        // Faz o request de login
        MutableLiveData<User> signInEventMutableLiveData = authRepository.doSignInApiCall(email);

        /*
            Usa o LiveData retornado pelo request de login para abstrair regras de negócio (salvar o token no caso)
            e retorna à view apenas um LiveData contendo o SignInEvent
          */
        return Transformations.map(signInEventMutableLiveData, user -> {
            if(user != null) {
                PreferencesHelper.setUserToken(context, user.getToken());
                return SignInEvent.SignInOk;
            }
            return SignInEvent.SignInError;
        });

    }

}
