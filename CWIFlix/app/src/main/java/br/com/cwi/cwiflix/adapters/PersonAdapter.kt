package br.com.cwi.cwiflix.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.cwi.cwiflix.R
import br.com.cwi.cwiflix.api.models.ListPerson
import br.com.cwi.cwiflix.utils.ImageURLProvider
import com.squareup.picasso.Picasso

/**
 * @author hedo
 */
class PersonAdapter(private val items: List<ListPerson>) :
        RecyclerView.Adapter<PersonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        LayoutInflater.from(parent.context).run {
            return ViewHolder(itemView = inflate(
                    R.layout.view_person,
                    parent,
                    false))
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        item.image?.let {
            Picasso.with(holder.itemView.context)
                    .load(ImageURLProvider.small(it))
                    .into(holder.photoImageView)
        }

        holder.personNameTextView.text = item.name
    }


    class ViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView) {

        val photoImageView: ImageView =
                itemView.findViewById(R.id.photoImageView)

        val personNameTextView: TextView =
                itemView.findViewById(R.id.nameTextView)

    }
}