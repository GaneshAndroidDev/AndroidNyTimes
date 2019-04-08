package com.android.nytimes.util.extensions

import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.android.nytimes.R
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

fun ImageView.loadUrl(url: String?) {
    Glide.with(context).load(url)
            .transition(GenericTransitionOptions.with(R.anim.zoom_in_center))
            .apply(
                    RequestOptions
                        .bitmapTransform(CircleCrop())
                        .placeholder(ContextCompat.getDrawable(context, R.drawable.ic_place_holder)
                    )
            )
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: com.bumptech.glide.load.DataSource?,
                        isFirstResource: Boolean
                ): Boolean {
                    return false
                }

            })
            .into(this)
}

