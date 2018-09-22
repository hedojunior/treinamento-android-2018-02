package br.com.cwi.cwiflix.listeners

/**
 * @author hedo
 */
interface OnFavoritesChangeListener {
    fun onFavoriteAdded()
    fun onFavoriteRemoved(index: Int)
}