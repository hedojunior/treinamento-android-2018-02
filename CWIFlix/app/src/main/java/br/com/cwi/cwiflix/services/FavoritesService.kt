package br.com.cwi.cwiflix.services

import br.com.cwi.cwiflix.services.api.models.MediaType
import br.com.cwi.cwiflix.services.models.Favorite
import com.google.firebase.database.*

/**
 * @author hedo
 */
object FavoritesService : ChildEventListener {
    private val database: FirebaseDatabase by lazy {
        FirebaseDatabase.getInstance()
    }

    private val favorites = ArrayList<Favorite>()

    private lateinit var reference: DatabaseReference

    fun initialize(userId: String) {
        favorites.clear()
        reference = database.getReference("favorites").child(userId)
        reference.addChildEventListener(this)
    }

    fun isFavorite(mediaId: Int, mediaType: MediaType)
            = favorites.any { it.mediaId == mediaId && it.mediaType == mediaType }

    fun add(favorite: Favorite) {
        val reference = reference.push()
        reference.key?.let { favorite.id = it }
        reference.setValue(favorite)
    }

    fun remove(mediaId: Int, mediaType: MediaType) {
        val id = favorites.first { it.mediaId == mediaId && it.mediaType == mediaType }.id
        reference.child(id).removeValue()
    }

    override fun onChildAdded(snapshot: DataSnapshot, p1: String?) {
        val favorite = snapshot.getValue(Favorite::class.java)

        favorite?.run {
            favorites.add(this)
        }
    }

    override fun onChildRemoved(snapshot: DataSnapshot) {
        favorites.removeAt(favorites.indexOfFirst { it.id == snapshot.key })
    }


    override fun onChildMoved(p0: DataSnapshot, p1: String?) {}

    override fun onChildChanged(p0: DataSnapshot, p1: String?) {}

    override fun onCancelled(p0: DatabaseError) {}


}















