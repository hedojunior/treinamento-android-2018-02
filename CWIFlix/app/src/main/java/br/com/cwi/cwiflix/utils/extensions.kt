package br.com.cwi.cwiflix.utils

import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * @author hedo
 */

fun ImageView.loadImage(url: String?) {
    url?.let {
        Picasso.with(context).load(it).into(this)
    }
}