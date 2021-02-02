package com.picpay.desafio.android.extensions

import android.view.View
import com.picpay.desafio.android.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_user.view.*

fun View.loadImage(
    endereco: String,
    imagemDeErro: Int = R.drawable.ic_round_account_circle
) {
    Picasso.get()
        .load(endereco)
        .error(imagemDeErro)
        .into(this.picture, object : Callback {
            override fun onSuccess() {
                this@loadImage.progressBar.visibility = View.GONE
            }

            override fun onError(e: Exception?) {
                this@loadImage.progressBar.visibility = View.GONE
            }
        })
}