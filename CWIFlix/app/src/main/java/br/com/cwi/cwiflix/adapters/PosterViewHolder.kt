package br.com.cwi.cwiflix.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageButton
import kotlinx.android.synthetic.main.view_media.view.*

/**
 * @author hedo
 */
class PosterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val posterImageButton: ImageButton = itemView.posterImageButton
}