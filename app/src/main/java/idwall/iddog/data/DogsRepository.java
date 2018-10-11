package idwall.iddog.data;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import java.util.List;

import idwall.iddog.data.local.prefs.PreferencesHelper;
import idwall.iddog.data.model.DogsResponse;
import idwall.iddog.data.remote.ApiEndpoint;
import idwall.iddog.data.remote.services.DogsService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DogsRepository {

    private Retrofit retrofit;
    private Context context;
    private String token;

    public DogsRepository(Context context) {
        this.context = context;
        retrofit = NetworkServiceLocator.getRetrofit();

        token = PreferencesHelper.getUserToken(context);
    }

    public MutableLiveData<List<String>> getDogs(String breed) {
        final MutableLiveData<List<String>> event = new MutableLiveData<>();

        DogsService service = retrofit.create(DogsService.class);
        Call<DogsResponse> repos = service.feed(token, breed);

        repos.enqueue(new Callback<DogsResponse>() {
            @Override
            public void onResponse(Call<DogsResponse> call, Response<DogsResponse> response) {

                try {
                    List<String> dogsList = response.body().getList();

                    event.setValue(dogsList);
                } catch(Exception e) {
                    e.printStackTrace();
                    event.setValue(null);
                }

            }

            @Override
            public void onFailure(Call<DogsResponse> call, Throwable t) {
                event.setValue(null);
                t.printStackTrace();
            }
        });

        return event;
    }

}
