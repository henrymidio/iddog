package idwall.iddog.data.remote.services;

import idwall.iddog.data.model.DogsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface DogsService {
    @GET("feed")
    Call<DogsResponse> feed(@Header("Authorization") String authorization, @Query("category") String category);
}
