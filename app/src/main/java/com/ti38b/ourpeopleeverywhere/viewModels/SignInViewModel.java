package com.ti38b.ourpeopleeverywhere.viewModels;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.BaseObservable;

import com.ti38b.ourpeopleeverywhere.dataBase.ApiClient;
import com.ti38b.ourpeopleeverywhere.dataBase.ApiResponse;
import com.ti38b.ourpeopleeverywhere.dataBase.users.UserDAO;
import com.ti38b.ourpeopleeverywhere.ui.LogInActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInViewModel extends BaseObservable {

    private String username;
    private String email;
    private String password;

    private String toastMessage = "default";

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setToastMessage(String toastMessage) {
        this.toastMessage = toastMessage;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getToastMessage() {
        return toastMessage;
    }

    public void onSignInButtonClick(View view){
        Context context = view.getContext();
        if(!getUsername().isEmpty()
                && !getEmail().isEmpty()
                && !getPassword().isEmpty()){
            signIn(context);
        }else{
            setToastMessage("All fields are required");
            displaySignInInformation(context);
        }
    }

    public void signIn(Context context){
        Call<ApiResponse> call = ApiClient.getApiClient()
                .create(UserDAO.class)
                .performUserSignIn(getUsername(), getEmail(), getPassword());
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if(response.code() == 200){
                    if(response.body().getStatus().equals("ok")){
                        Intent intent = new Intent(context, LogInActivity.class);
                        context.startActivity(intent);
                    }else{
                        switch (response.body().getStatus()){
                            case "Error: Sign up Failed":
                                setToastMessage("Sign up Failed");
                                displaySignInInformation(context);
                                break;
                            case "Error: Database connection":
                                setToastMessage("Database connection failed");
                                displaySignInInformation(context);
                                break;
                            case "Error: All fields are required":
                                setToastMessage("All fields are required");
                                displaySignInInformation(context);
                                break;
                        }
                    }
                }else{
                    setToastMessage("response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                setToastMessage("SignIn onFailure: " + t);
                displaySignInInformation(context);
            }
        });
    }

    public void displaySignInInformation(Context context){
        Toast.makeText(context, getToastMessage(), Toast.LENGTH_LONG).show();
    }
}
