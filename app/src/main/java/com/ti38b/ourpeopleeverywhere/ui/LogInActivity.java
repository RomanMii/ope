package com.ti38b.ourpeopleeverywhere.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.ti38b.ourpeopleeverywhere.R;
import com.ti38b.ourpeopleeverywhere.databinding.ActivityLogInBinding;
import com.ti38b.ourpeopleeverywhere.viewModels.LogInViewModel;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLogInBinding activityLogInBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_log_in);
        activityLogInBinding.setViewModel(new LogInViewModel());
        activityLogInBinding.executePendingBindings();
    }
}