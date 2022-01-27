package com.ti38b.ourpeopleeverywhere.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.ti38b.ourpeopleeverywhere.R;
import com.ti38b.ourpeopleeverywhere.apputil.AppConfig;
import com.ti38b.ourpeopleeverywhere.databinding.ActivityMainBinding;
import com.ti38b.ourpeopleeverywhere.databinding.BlockLogInButtonsBinding;
import com.ti38b.ourpeopleeverywhere.databinding.NavHeaderMainBinding;
import com.ti38b.ourpeopleeverywhere.databinding.BlockUsernameBinding;
import com.ti38b.ourpeopleeverywhere.viewModels.MainViewModel;

import androidx.databinding.DataBindingUtil;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ViewFlipper viewFlipper;
    private AppConfig appConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding activityMainBinding = DataBindingUtil
                .setContentView(this, R.layout.activity_main);
                //ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(activityMainBinding.getRoot());

        appConfig = new AppConfig(this);

        setSupportActionBar(activityMainBinding.appBarMain.toolbar);

        DrawerLayout drawer = activityMainBinding.drawerLayout;
        NavigationView navigationView = activityMainBinding.navView;

        NavHeaderMainBinding bindHeader = DataBindingUtil.inflate(getLayoutInflater(),
                R.layout.nav_header_main, activityMainBinding.navView, true);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_convertor, R.id.nav_alarmnumbers)
                .setDrawerLayout(drawer)
                .build();

        if(appConfig.isUserLogin()){
            BlockUsernameBinding usernameBlockBinding = DataBindingUtil.inflate(getLayoutInflater(),
                    R.layout.block_username, bindHeader.container, true);
            TextView usernameHeader = usernameBlockBinding
                    .usernameBlockLinearLayout
                    .findViewById(R.id.usernameHeaderTextField);
            usernameHeader.setText(appConfig.getUserUsername());

        }else{
            BlockLogInButtonsBinding logInButtonsBlockBinding = DataBindingUtil.inflate(getLayoutInflater(),
                    R.layout.block_log_in_buttons, bindHeader.container, true);

            logInButtonsBlockBinding.setViewModel(new MainViewModel());
            logInButtonsBlockBinding.executePendingBindings();
        }


        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_logout:
                appConfig.updateUserLoginStatus(false);
                appConfig.setUserUsername("Unknown");
                appConfig.setUserId(0);
                appConfig.setUserEmail("Unknown");
                recreate();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}