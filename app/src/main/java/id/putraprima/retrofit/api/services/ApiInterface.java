package id.putraprima.retrofit.api.services;


import java.util.List;

import id.putraprima.retrofit.api.models.AppVersion;
import id.putraprima.retrofit.api.models.Data;
import id.putraprima.retrofit.api.models.DataRecipe;
import id.putraprima.retrofit.api.models.Envelope;
import id.putraprima.retrofit.api.models.LoginRequest;
import id.putraprima.retrofit.api.models.LoginResponse;
import id.putraprima.retrofit.api.models.PasswordRequest;
import id.putraprima.retrofit.api.models.ProfileRequest;
import id.putraprima.retrofit.api.models.ProfileResponse;
import id.putraprima.retrofit.api.models.RegisterRequest;
import id.putraprima.retrofit.api.models.RegisterResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface{
    @GET("/")
    Call<AppVersion> getAppVersion();

    @POST("/api/auth/login")
    Call<LoginResponse> doLogin(@Body LoginRequest loginRequest);

    @POST("/api/auth/register")
    Call<RegisterResponse> doRegister(@Body RegisterRequest registerRequest);

    @GET("/api/auth/me")
    Call<Data<ProfileResponse>> getProfile(@Header("Authorization") String token);

    @PATCH("/api/account/profile")
    Call<Data<ProfileResponse>> updateProfile(@Header("Authorization") String token, @Body ProfileRequest profileRequest);

    @PATCH("/api/account/password")
    Call<Data<ProfileResponse>> updatePassword(@Header("Authorization") String token, @Body PasswordRequest passwordRequest);

    @GET("/api/recipe")
    Call<Envelope<List<DataRecipe>>> doRecipe();

    @GET("/api/recipe?page=2")
    Call<Envelope<List<DataRecipe>>> doPageTwo();

    @GET("/api/recipe")
    Call<Envelope<List<DataRecipe>>> doLoadMore(@Query("page") int page);
}
