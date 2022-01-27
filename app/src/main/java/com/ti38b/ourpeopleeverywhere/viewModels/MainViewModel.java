package com.ti38b.ourpeopleeverywhere.viewModels;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.databinding.BaseObservable;

import com.ti38b.ourpeopleeverywhere.ui.LogInActivity;
import com.ti38b.ourpeopleeverywhere.ui.SignInActivity;

public class MainViewModel extends BaseObservable {

    public void onLoginClicked(View view){
        Context context = view.getContext();
        Intent intent = new Intent(context, LogInActivity.class);
        context.startActivity(intent);
    }

    public void onSigninClicked(View view){
        Context context = view.getContext();
        Intent intent = new Intent(context, SignInActivity.class);
        context.startActivity(intent);
    }

}
