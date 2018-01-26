package com.happytrees.githubapitirgul.rest;

import com.happytrees.githubapitirgul.model.GitHubUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;



public interface GitHubUserEndPoints {
    @GET("/users/{user}")//inside @GET is endpoint of our url --> https://api.github.com/users/{user} .We will "GET" info about specific user from it
    Call<GitHubUser> getUser(@Path("user") String name);//@Path("user") refers to {user} . This is  part of url which will be swapped according to value of String variable name
}
 /*     when we have single JSON object  -> JSON {}
        its enough  to use single call . Call<GitHubUser> getUser
        but when we have array of JSON objects -> JSON []
        our call requires list Call<List<GitHubRepo>> */