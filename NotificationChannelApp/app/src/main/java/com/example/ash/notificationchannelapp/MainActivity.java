package com.example.ash.notificationchannelapp;

import android.app.Notification;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int WATCH_MOVIE_NOTIFICATION_ID = 1000;
    private EditText edtWatchMovie;
    private NotificationHandler notificationHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationHandler = new NotificationHandler(this);

        edtWatchMovie = findViewById(R.id.edtWatchMovie);
        findViewById(R.id.btnWatchMovie).setOnClickListener(this);
        findViewById(R.id.btnMovieSettings).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnWatchMovie:

                postNotificationToUSerDevice(WATCH_MOVIE_NOTIFICATION_ID, edtWatchMovie.getText().toString());
                break;
            case R.id.btnMovieSettings:

                openNotificationSettingsForWatchMovieChannel(notificationHandler.WATCH_MOVIE_NOTIFICATION_CHANNEL_ID);
                break;
        }

    }

    private void postNotificationToUSerDevice(int notificationId, String titleText) {

        Notification.Builder notificationBuilder = null;
        switch (notificationId) {

            case WATCH_MOVIE_NOTIFICATION_ID:

                notificationBuilder = notificationHandler.createAndReturnWatchMovieNotification(titleText, "This is a great movie");
                break;

        }

        if (notificationBuilder != null) {

            notificationHandler.notifyTheUser(WATCH_MOVIE_NOTIFICATION_ID, notificationBuilder);
        }
    }

    private void openNotificationSettingsForWatchMovieChannel(String watchMovieChannelId){

        Intent settingsIntent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
        settingsIntent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
        settingsIntent.putExtra(Settings.EXTRA_CHANNEL_ID, watchMovieChannelId);
        startActivity(settingsIntent);
    }
}
