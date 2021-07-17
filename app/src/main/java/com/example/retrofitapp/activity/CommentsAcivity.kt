package com.example.retrofitapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.retrofitapp.ApiClient
import com.example.retrofitapp.ApiInterface
import com.example.retrofitapp.Posts
import com.example.retrofitapp.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentsAcivity : AppCompatActivity() {
    var postId = 0
    lateinit var tvPostTitle:TextView
    lateinit var tvPostBody:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments_acivity)

        postId = intent.getIntExtra("post_id",0)
        castViews()
        getPost()

    }
    fun castViews(){
         tvPostTitle = findViewById()
    }
    fun getPost(){
        if (postId == 0) {
            Toast.makeText(baseContext,"Post not found",Toast.LENGTH_LONG).show()
            finish()
        }
        var retrofit = ApiClient.buildApiClient(ApiInterface::class.java)
        val  request = retrofit.getPosts()
        request.enqueue(object : Callback<List<Posts>?> {
            override fun onResponse(call: Call<List<Posts>?>, response: Response<List<Posts>?>) {
//                status code between 200-300 is okay 300-400 your code
                if (response.isSuccessful){
                    var post = response.body()
                    tvPostTitle.text = post.title
                }
            }

            override fun onFailure(call: Call<List<Posts>?>, t: Throwable) {
                Toast
            }

        })
    }
}