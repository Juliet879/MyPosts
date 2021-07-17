package com.example.retrofitapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitapp.activity.CommentsAcivity

class PostRecyclerViewAdapter(var postList: MutableList<Posts>,var context:Context) : RecyclerView.Adapter<PostItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.post_item_list, parent, false)
        return PostItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PostItemViewHolder, position: Int) {
        val currentPost = postList.get(position)
        holder.tvTitle.text = currentPost.title
        holder.tvBody.text = currentPost.my_body


        holder.cvPosts.setOnClickListener {
            val intent = Intent(context,CommentsAcivity::class.java)
            intent.putExtra("post_id",currentPost.id)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return postList.size

    }
}


class PostItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
    var tvBody = itemView.findViewById<TextView>(R.id.tvBody)
    var cvPosts = itemView.findViewById<CardView>(R.id.cvPosts)


}