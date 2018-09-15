package br.com.cwi.cwiflix.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.cwi.cwiflix.R
import br.com.cwi.cwiflix.api.models.Media
import br.com.cwi.cwiflix.utils.ImageURLProvider
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_media.view.*

/**
 * @author hedo
 */
class MediaAdapter(val items: List<Media>, val onClick: (media: Media) -> Unit)
    : RecyclerView.Adapter<MediaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_media, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        Picasso
                .with(holder.itemView.context)
                .load(ImageURLProvider.small(item.image))
                .into(holder.posterImageButton)

        holder.posterImageButton.setOnClickListener {
            onClick(item)
        }
    }

    override fun getItemCount() = items.size


    class ViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView) {

        val posterImageButton = itemView.posterImageButton
    }
}















