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
import br.com.cwi.cwiflix.utils.loadImage
import kotlinx.android.synthetic.main.view_person.view.*

/**
 * @author hedo
 */
class PersonAdapter(private val items: List<ListPerson>,
                    private val onClick: (person: ListPerson) -> Unit) :
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
        items[position].run {
            holder.photoImageView.loadImage(ImageURLProvider.small(image))
            holder.personNameTextView.text = name

            holder.itemView.setOnClickListener {
                onClick(this)
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val photoImageView: ImageView = itemView.photoImageView
        val personNameTextView: TextView = itemView.nameTextView
    }
}