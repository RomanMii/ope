package com.ti38b.ourpeopleeverywhere.dataBase.users;

import com.ti38b.ourpeopleeverywhere.dataBase.ApiResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserDAO {

    @FormUrlEncoded
    @POST("login.php")
    Call<ApiResponse> performUserLogin(@Field("username") String username,
                                       @Field("password") String password);

    @FormUrlEncoded
    @POST("signup.php")
    Call<ApiResponse> performUserSignIn(@Field("username") String username,
                                       @Field("email") String email,
                                       @Field("password") String password);
}
