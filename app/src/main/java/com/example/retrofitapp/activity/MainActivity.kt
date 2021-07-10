package com.example.retrofitapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitapp.ApiInterface
import com.example.retrofitapp.PostItem
import com.example.retrofitapp.PostRecyclerViewAdapter
import com.example.retrofitapp.R
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var rvPosts:RecyclerView
    var BASE_URL:String = "https://jsonplaceholder.typicode.com"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getPost()
    }
    fun getPost(){
        rvPosts = findViewById(R.id.rvPosts)
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val  request = retrofit.getPosts()
         request.enqueue(object : Callback<List<PostItem>?> {
             override fun onResponse(call: Call<List<PostItem>?>, response: Response<List<PostItem>?>) {
                 val posts = response.body()!!

                 var postAdapter = PostRecyclerViewAdapter(posts as ArrayList<PostItem>)
                 rvPosts.adapter = postAdapter
                 rvPosts.layoutManager = LinearLayoutManager(baseContext)
             }

             override fun onFailure(call: Call<List<PostItem>?>, t: Throwable) {
                 TODO("Not yet implemented")
             }
         })
    }
}