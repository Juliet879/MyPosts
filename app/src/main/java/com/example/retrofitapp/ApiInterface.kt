package com.example.retrofitapp

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("posts")
    fun getPosts(): Call<List<Posts>>

    @GET("posts/{postId}")
    fun getPost(postId:Int):Call<Posts>
}