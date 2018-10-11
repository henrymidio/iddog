package idwall.iddog.data;

import idwall.iddog.data.remote.ApiEndpoint;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkServiceLocator {

    public static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(ApiEndpoint.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
