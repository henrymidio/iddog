package idwall.iddog.data;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import idwall.iddog.data.local.prefs.PreferencesHelper;
import idwall.iddog.data.model.AuthRequest;
import idwall.iddog.data.model.AuthResponse;
import idwall.iddog.data.model.User;
import idwall.iddog.data.remote.ApiEndpoint;
import idwall.iddog.data.remote.services.AuthService;
import idwall.iddog.ui.signin.SignInViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthRepository {

    private Retrofit retrofit;
    private Context context;

    public AuthRepository(Context context) {
        this.context = context;
        retrofit = NetworkServiceLocator.getRetrofit();
    }

    public MutableLiveData<SignInViewModel.SignInEvent> doSignInApiCall(String email) {

        final MutableLiveData<SignInViewModel.SignInEvent> event = new MutableLiveData<SignInViewModel.SignInEvent>();

        AuthService service = retrofit.create(AuthService.class);
        Call<AuthResponse> repos = service.signin(new AuthRequest(email));

        repos.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {

                try {
                    User user = response.body().getUser();

                    PreferencesHelper.setUserToken(context, user.getToken());

                    event.setValue(SignInViewModel.SignInEvent.SignInOk);

                } catch(Exception e) {
                    e.printStackTrace();
                    event.setValue(SignInViewModel.SignInEvent.SignInError);
                }

            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                event.setValue(SignInViewModel.SignInEvent.SignInError);
                t.printStackTrace();
            }
        });

        return event;
    }

}
