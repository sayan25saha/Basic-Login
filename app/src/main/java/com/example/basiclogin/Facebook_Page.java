package com.example.basiclogin;
import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class Facebook_Page extends AppCompatActivity {

    private ImageView imageView;

    private TextView txt_name,txt_email,Id;
    private LoginButton b;
    private CallbackManager c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_page);



        imageView=findViewById(R.id.profilePic);
        txt_name=findViewById(R.id.name);
        txt_email=findViewById(R.id.email);
        Id=findViewById(R.id.idea);
        b=findViewById(R.id.login_button);

        c = CallbackManager.Factory.create();

        b.setPermissions(Arrays.asList("email","public_profile"));
        b.registerCallback(c, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        c.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
    AccessTokenTracker accessTokenTracker=new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

            if(currentAccessToken==null){
                txt_name.setText("");
                txt_email.setText("");
                Id.setText("");
                imageView.setImageResource(0);
                Toast.makeText(Facebook_Page.this,"User logged out",Toast.LENGTH_SHORT).show();
            }
            else{
                loaduserprofile(currentAccessToken);
            }
        }
    };

    private void loaduserprofile(AccessToken currentAccessToken) {
        GraphRequest request=GraphRequest.newMeRequest(currentAccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    String first_name=object.getString("first_name");
                    String last_name=object.getString("last_name");
                    String email=object.getString("email");
                    String id=object.getString("id");
                    String image_url="https://graph.facebook.com/"+id+"/picture?type=normal";


                    txt_email.setText("Email: "+email);
                    txt_name.setText("Name: "+first_name+" "+last_name);
                    Id.setText("Id: "+id);

                    Glide.with(Facebook_Page.this).load(image_url).into(imageView);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields","first_name,last_name,email,id");
        request.setParameters(parameters);
        request.executeAsync();
    }


}