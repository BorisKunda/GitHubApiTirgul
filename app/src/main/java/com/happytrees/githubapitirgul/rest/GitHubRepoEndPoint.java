package com.happytrees.githubapitirgul.rest;

import com.happytrees.githubapitirgul.model.GitHubRepo;

import java.util.List;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubRepoEndPoint {

    @GET("/users/{user}/repos")
    Call<List<GitHubRepo>> getRepo(@Path("user") String name);
 /*     when we have single JSON object  -> JSON {}
        its enough  to use single call . Call<GitHubUser> getUser
        but when we have array of JSON objects -> JSON []
        our call requires list Call<List<GitHubRepo>> */
}


