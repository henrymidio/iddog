package idwall.iddog.data;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import idwall.iddog.data.model.AuthRequest;
import idwall.iddog.data.model.AuthResponse;
import idwall.iddog.data.model.User;
import idwall.iddog.data.remote.services.AuthService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AuthRepository {

    private Retrofit retrofit;
    private Context context;

    public AuthRepository(Context context) {
        this.context = context;
        retrofit = DataServiceLocator.getRetrofit();
    }

    public MutableLiveData<User> doSignInApiCall(String email) {

        final MutableLiveData<User> event = new MutableLiveData<>();

        AuthService service = retrofit.create(AuthService.class);
        Call<AuthResponse> repos = service.signin(new AuthRequest(email));

        repos.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {

                try {

                    User user = response.body().getUser();
                    event.setValue(user);

                } catch(Exception e) {
                    e.printStackTrace();
                    event.setValue(null);
                }

            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                event.setValue(null);
                t.printStackTrace();
            }
        });

        return event;
    }


}
