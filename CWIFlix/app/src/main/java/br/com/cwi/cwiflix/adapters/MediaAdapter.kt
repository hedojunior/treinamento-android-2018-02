package br.com.cwi.cwiflix.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import br.com.cwi.cwiflix.R
import br.com.cwi.cwiflix.services.api.models.Media
import br.com.cwi.cwiflix.utils.ImageURLProvider
import br.com.cwi.cwiflix.utils.loadImage

import br.com.cwi.cwiflix.adapters.PosterViewHolder as ViewHolder

/**
 * @author hedo
 */
class MediaAdapter(private val items: ArrayList<Media>,
                   private val onClick: (media: Media) -> Unit)
    : RecyclerView.Adapter<ViewHolder>() {

    private var lastItemPosition: Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_media, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items[position].run {
            image?.let {
                holder.posterImageButton.loadImage(ImageURLProvider.medium(it))
            }

            holder.posterImageButton.setOnClickListener { onClick(this) }
        }
    }

    override fun getItemCount() = items.size

    fun addItems(list: List<Media>) {

        items.addAll(list)
        notifyItemRangeInserted(lastItemPosition, list.size)
        lastItemPosition = items.size
    }
}















