package com.example.retrofitapp

import com.google.gson.annotations.SerializedName

data class PostItem(
    @SerializedName("body")
    val my_body: String,
    val id: Int,
    val title: String,
    val userId: Int
)