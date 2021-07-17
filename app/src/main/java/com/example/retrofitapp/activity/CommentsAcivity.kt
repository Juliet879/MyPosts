package com.example.retrofitapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitapp.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentsAcivity : AppCompatActivity() {
    var postId = 0
    lateinit var tvPostTitle:TextView
    lateinit var tvPostBody:TextView
    lateinit var rvComments:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments_acivity)

        postId = intent.getIntExtra("post_id",0)
        castViews()
        getPost()
        getComment()

    }
    fun castViews(){
        tvPostTitle = findViewById(R.id.tvPostTitle)
        tvPostBody = findViewById(R.id.tvPostBody)
    }
    fun getPost(){
        if (postId == 0) {
            Toast.makeText(baseContext,"Post not found",Toast.LENGTH_LONG).show()
            finish()
        }
        val retrofit = ApiClient.buildApiClient(ApiInterface::class.java)
        val  request = retrofit.getPost(postId)


        request.enqueue(object : Callback<Posts?> {
            override fun onResponse(call: Call<Posts?>, response: Response<Posts?> ) {
                if (response.isSuccessful){
                    val post = response.body()
                    tvPostTitle.text=post?.title
                    tvPostBody.text=post?.my_body
                }
            }

            override fun onFailure(call: Call<Posts?>, t: Throwable) {
                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
            }

        })


    }

    fun getComment(){
        rvComments = findViewById(R.id.rvComments)
        val retrofit = ApiClient.buildApiClient(ApiInterface::class.java)
        val  request = retrofit.getComments(postId)

        request.enqueue(object : Callback<List<Comment>?> {
            override fun onResponse(call: Call<List<Comment>?>, response: Response<List<Comment>?>) {
                val commentList = response.body()

                val commentAdapter = CommentsRVAdapter(commentList!!)
                rvComments.adapter = commentAdapter
                rvComments.layoutManager = LinearLayoutManager(baseContext)


            }

            override fun onFailure(call: Call<List<Comment>?>, t: Throwable) {
                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
            }
        })
        getPost()
    }
}



