package com.happytrees.githubapitirgul.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.happytrees.githubapitirgul.R;
import com.happytrees.githubapitirgul.model.GitHubUser;
import com.happytrees.githubapitirgul.rest.APIClient;
import com.happytrees.githubapitirgul.rest.GitHubUserEndPoints;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {

    ImageView avatarImg;
    TextView userNameTV;
    TextView followersTV;
    TextView followingTV;
    TextView logIn;
    TextView email;
    Button ownedrepos;


    Bundle extras;
    String newString;//variable for keeping Sting passed from Login Activity




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        avatarImg = (ImageView) findViewById(R.id.avatar);
        userNameTV = (TextView) findViewById(R.id.username);
        followersTV = (TextView) findViewById(R.id.followers);
        followingTV = (TextView) findViewById(R.id.following);
        logIn = (TextView) findViewById(R.id.logIn);
        email = (TextView) findViewById(R.id.email);
        ownedrepos = (Button) findViewById(R.id.ownedReposBtn);

        extras = getIntent().getExtras();//we keep String passed from Login Activity inside Bundle instead of using "Intent incomingIntent= getIntent();"
        newString = extras.getString("STRING_I_NEED");//now keep Sting from passed from Login Activity under newString variable
        System.out.println(newString);
        loadData();

        ownedrepos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this,Repositories.class);
                intent.putExtra("username",newString);
                startActivity(intent);
            }
        });



    }
    public void loadData() {

        final GitHubUserEndPoints apiService =
                APIClient.getClient().create(GitHubUserEndPoints.class);//"getClient" method we defined in APIClient class
        //we call GitHubUser and ask it to invoke getUser method ."Call<GitHubUser> call" is call object
        Call<GitHubUser> call = apiService.getUser(newString);//getUser method we defined in GitHubUserEndPoints interface.newString is String variable passed from LoginActivity
        call.enqueue(new Callback<GitHubUser>() {

            @Override
            public void onResponse(Call<GitHubUser> call, Response<GitHubUser>//if there was response from server
                    response) {
                if(response.body().getName() == null){
                    userNameTV.setText("No name provided");
                } else {
                    userNameTV.setText("Username: " + response.body().getName());
                }

                followersTV.setText("Followers: " + response.body().getFollowers());
                followingTV.setText("Following: " + response.body().getFollowing());
                logIn.setText("LogIn: " + response.body().getLogin());

                if(response.body().getEmail() == null){
                    email.setText("No email provided");
                } else{
                    email.setText("Email: " + response.body().getEmail());
                }
                //IMAGE
                String newAvatarUrl =  response.body().getAvatar()  ;
                Picasso.with(UserActivity.this).load(newAvatarUrl).into(avatarImg);
            }

            @Override
            public void onFailure(Call<GitHubUser> call, Throwable t) {//if there was failure in response

            }
        });
    }
    }
