package com.ti38b.ourpeopleeverywhere.viewModels;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.BaseObservable;

import com.ti38b.ourpeopleeverywhere.dataBase.ApiClient;
import com.ti38b.ourpeopleeverywhere.dataBase.ApiResponse;

import com.ti38b.ourpeopleeverywhere.dataBase.users.User;
import com.ti38b.ourpeopleeverywhere.dataBase.users.UserDAO;
import com.ti38b.ourpeopleeverywhere.ui.MainActivity;
import com.ti38b.ourpeopleeverywhere.apputil.AppConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogInViewModel extends BaseObservable {

    private User user = null;
    private String username;
    private String password;

    private String toastMessage = "default";

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getToastMessage() {
        return toastMessage;
    }

    public void setToastMessage(String toastMessage) {
        this.toastMessage = toastMessage;
    }

    public void onLogInButtonClick(View view){
        Context context = view.getContext();
        if(!getUsername().isEmpty() || !getPassword().isEmpty()){
            login(context);
        }else{
            setToastMessage("All fields are required");
            displayLoginInformation(context);
        }
    }

    public void login(Context context){

        Call<ApiResponse> call = ApiClient.getApiClient()
                .create(UserDAO.class)
                .performUserLogin(getUsername(), getPassword());
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if(response.code()==200){
                    if(response.body().getStatus().equals("ok")){
                        user = response.body().getUser();
                        AppConfig appConfig = new AppConfig(context);
                        if(!saveUserInformation(appConfig)){
                            setToastMessage("cannot save user");
                            displayLoginInformation(context);
                        }else{
                            Intent intent = new Intent(context, MainActivity.class);
                            context.startActivity(intent);
                        }
                    }else{
                        switch (response.body().getStatus()){
                            case "Error: wrong password":
                                setToastMessage("wrong password");
                                displayLoginInformation(context);
                                break;
                            case "Error: there are more than one user with such username":
                                setToastMessage("there are more than one user with such username");
                                displayLoginInformation(context);
                                break;
                            case "Error: No such user":
                                setToastMessage("No such user");
                                displayLoginInformation(context);
                                break;
                            case "Error: Database connection":
                                setToastMessage("Database connection failed");
                                displayLoginInformation(context);
                                break;
                            case "Error: All fields are required":
                                setToastMessage("All fields are required");
                                displayLoginInformation(context);
                                break;
                            default:
                                setToastMessage("something went wrong");
                                displayLoginInformation(context);
                                break;
                        }
                    }
                }else{
                    setToastMessage("response code: " + response.code());
                    displayLoginInformation(context);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                setToastMessage("Login onFailure: " + t);
                displayLoginInformation(context);
            }
        });
    }

    private boolean saveUserInformation(AppConfig appConfig){
        if(user != null){
            appConfig.updateUserLoginStatus(true);
            appConfig.setUserUsername(user.getUsername());
            appConfig.setUserId(user.getId());
            appConfig.setUserEmail(user.getEmail());
            return true;
        }else{
            return false;
        }
    }

    private void displayLoginInformation(Context context){
        Toast.makeText(context, getToastMessage(), Toast.LENGTH_LONG).show();
    }
}
