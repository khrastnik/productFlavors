package com.dependencymanager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;


public class ShareActivity extends AppCompatActivity {

    private ShareDialog shareDialog;
    private CallbackManager callbackManager;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        FacebookSdk.sdkInitialize(this);
        shareDialog = new ShareDialog(this);
        callbackManager = CallbackManager.Factory.create();
    }

    private void shareToFacebook() {
        try {
            if (ShareDialog.canShow(ShareLinkContent.class)) {
                ShareLinkContent linkContent = new ShareLinkContent.Builder()
                        //.setContentTitle("Content Title")
                        //.setContentDescription("Content Description")
                        .setContentUrl(Uri.parse("Content uri"))
                        .build();

                shareDialog.show(linkContent);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Error with facebook sharing", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 64207) {
            if (resultCode == RESULT_OK)
                Toast.makeText(context, "Success facebook share", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(context, "Failure facebook share", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickFacebookShare(View view) {
        shareToFacebook();
    }
}
