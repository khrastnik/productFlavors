package com.dependencymanager;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;

import java.util.ConcurrentModificationException;

import io.fabric.sdk.android.Fabric;


public class ShareActivity extends AppCompatActivity {

    private String TWITTER_KEY = "";
    private String TWITTER_SECRET = "";
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new TwitterCore(authConfig), new TweetComposer());
    }

    public void onClickTweet(View view) {

        TweetComposer.Builder builder = new TweetComposer.Builder(context).text("Demo text");
        builder.show();

    }
}
