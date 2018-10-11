package idwall.iddog.data.remote.services;

import idwall.iddog.data.model.AuthRequest;
import idwall.iddog.data.model.AuthResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {

    @POST("signup")
    Call<AuthResponse> signin(@Body AuthRequest authRequest);

}