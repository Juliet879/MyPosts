package com.example.retrofitapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitapp.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var rvPosts:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getPost()
    }
    fun getPost(){
        rvPosts = findViewById(R.id.rvPosts)

        var retrofit = ApiClient.buildApiClient(ApiInterface::class.java)
        val  request = retrofit.getPosts()
         request.enqueue(object : Callback<List<PostItem>?> {
             override fun onResponse(call: Call<List<PostItem>?>, response: Response<List<PostItem>?>) {
                 val posts = response.body()!!

                 var postAdapter = PostRecyclerViewAdapter(posts as ArrayList<PostItem>)
                 rvPosts.adapter = postAdapter
                 rvPosts.layoutManager = LinearLayoutManager(baseContext)
             }

             override fun onFailure(call: Call<List<PostItem>?>, t: Throwable) {
                 Log.d("MainActivity","OnFailure" + t.message)
             }
         })
    }
}