package com.example.retrofitapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PostRecyclerViewAdapter(var postList: ArrayList<PostItem>):RecyclerView.Adapter<postItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): postItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.post_item_list,parent,false)
        return postItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: postItemViewHolder, position: Int) {
        val currentPost = postList.get(position)
        holder.tvUserId.text = currentPost.userId.toString()
        holder.tvId.text = currentPost.id.toString()
        holder.tvTitle.text = currentPost.title.toString()
        holder.tvBody.text = currentPost.my_body.toString()


    }

    override fun getItemCount(): Int {
        return postList.size
    }
}
class postItemViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    var tvUserId = itemView.findViewById<TextView>(R.id.tvUserId)
    var tvId = itemView.findViewById<TextView>(R.id.tvId)
    var tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
    var tvBody = itemView.findViewById<TextView>(R.id.tvBody)


}