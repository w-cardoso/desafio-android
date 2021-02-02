package com.picpay.desafio.android.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.databinding.ListItemUserBinding
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.extensions.loadImage

class UserListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var users = listOf<User>()
        set(value) {
            val result = DiffUtil.calculateDiff(
                UserListDiffCallback(
                    field,
                    value
                )
            )
            result.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewDataBinding = ListItemUserBinding.inflate(inflater, parent, false)
        return UserListItemViewHolder(viewDataBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as UserListItemViewHolder).bind(users[position])

    override fun getItemCount(): Int = users.size

    inner class UserListItemViewHolder(private val viewDataBinding: ListItemUserBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root){

        fun bind(user: User) {
            viewDataBinding.user = user
            itemView.loadImage(user.img)
        }
    }
}