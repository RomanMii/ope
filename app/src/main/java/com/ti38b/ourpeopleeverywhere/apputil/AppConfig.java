package com.ti38b.ourpeopleeverywhere.apputil;

import android.content.Context;
import android.content.SharedPreferences;

import com.ti38b.ourpeopleeverywhere.R;

public class AppConfig {

    private Context context;
    private SharedPreferences sharedPreferences;

    public AppConfig(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("com.ti38b.ourpeopleeverywhere",
                Context.MODE_PRIVATE);
    }

    public boolean isUserLogin(){
        return sharedPreferences.getBoolean(context.getString(R.string.pref_is_user_login),false);
    }

    public void updateUserLoginStatus(boolean status){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(context.getString(R.string.pref_is_user_login),status);
        editor.apply();
    }

    public void setUserUsername(String username){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_user_username),username);
        editor.apply();
    }

    public void setUserEmail(String email){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_user_email),email);
        editor.apply();
    }

    public String getUserUsername(){
        return sharedPreferences.getString(context.getString(R.string.pref_user_username),"Unknown");
    }

    public String getUserEmail(){
        return sharedPreferences.getString(context.getString(R.string.pref_user_email),"Unknown");
    }

    public void setUserId(int id){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(context.getString(R.string.pref_user_id),id);
        editor.apply();
    }

    public Integer getUserId(){
        return sharedPreferences.getInt(context.getString(R.string.pref_user_id),0);
    }
}
