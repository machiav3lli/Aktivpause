package org.secuso.aktivpause.activities;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import org.secuso.aktivpause.activities.tutorial.FirstLaunchManager;
import org.secuso.aktivpause.activities.tutorial.TutorialActivity;

import static androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode;

/**
 * @author yonjuni
 * @version 1.0
 * @since 22.10.16
 * created 22.10.16
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setDayNightTheme(PreferenceManager.getDefaultSharedPreferences(this).getInt("theme", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM));
        super.onCreate(savedInstanceState);

        Intent mainIntent = null;

        FirstLaunchManager firstStartPref = new FirstLaunchManager(this);

        if(firstStartPref.isFirstTimeLaunch()) {
            firstStartPref.initFirstTimeLaunch();
            mainIntent = new Intent(this, TutorialActivity.class);
        } else {
            mainIntent = new Intent(this, TimerActivity.class);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }

        SplashActivity.this.startActivity(mainIntent);
        SplashActivity.this.finish();
    }

    private void setDayNightTheme(int theme) {
        switch (theme) {
            case AppCompatDelegate.MODE_NIGHT_NO:
                setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            case AppCompatDelegate.MODE_NIGHT_YES:
                setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            default:
                setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        }
    }

}
