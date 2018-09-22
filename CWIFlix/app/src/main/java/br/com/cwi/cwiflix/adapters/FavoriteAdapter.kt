package br.com.cwi.cwiflix.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.cwi.cwiflix.R
import br.com.cwi.cwiflix.services.models.Favorite
import br.com.cwi.cwiflix.utils.ImageURLProvider
import br.com.cwi.cwiflix.utils.loadImage

/**
 * @author hedo
 */
class FavoriteAdapter(private val items: ArrayList<Favorite>) : RecyclerView.Adapter<PosterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PosterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_media, parent, false)

        return PosterViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: PosterViewHolder, position: Int) {
        val favorite = items[position]
        holder.posterImageButton.loadImage(ImageURLProvider.medium(favorite.image))
    }

}