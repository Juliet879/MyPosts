package com.example.retrofitapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("posts")
    fun getPosts(): Call<List<Posts>>

    @GET("posts/{postId}")
    fun getPost(@Path("postId") postsId:Int) :Call<Posts>

    @GET("posts/{postId}/comments")
    fun getComments(@Path("postId") postsId:Int) :Call<List<Comment>>

}