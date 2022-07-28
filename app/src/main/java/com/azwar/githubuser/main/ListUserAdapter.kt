package com.azwar.githubuser.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azwar.githubuser.databinding.ItemRowUserBinding
import com.azwar.githubuser.model.Users
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class ListUserAdapter : RecyclerView.Adapter<ListUserAdapter.UserViewHolder>(){

    private val list = ArrayList<Users>()

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback (onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    fun setList(users: ArrayList<Users>){
        list.clear()
        list.addAll(users)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(val binding: ItemRowUserBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(userResponse: Users){
            binding.root.setOnClickListener {
                onItemClickCallback?.OnItemClick(userResponse)
            }
            binding.apply {
                Glide.with(itemView)
                    .load(userResponse.avatar_url)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .centerCrop()
                    .into(ivAvatar)
                tvUsername.text = userResponse.login
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder((view))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickCallback{
        fun OnItemClick(data: Users)
    }
}