package com.ti38b.ourpeopleeverywhere.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.ti38b.ourpeopleeverywhere.R;
import com.ti38b.ourpeopleeverywhere.databinding.ActivitySignInBinding;
import com.ti38b.ourpeopleeverywhere.viewModels.SignInViewModel;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySignInBinding activitySignInBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_sign_in);
        activitySignInBinding.setViewModel(new SignInViewModel());
        activitySignInBinding.executePendingBindings();
    }
}