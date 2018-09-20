package br.com.cwi.cwiflix.listeners

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * @author hedo
 */
abstract class PaginatedScrollListener(private val gridLayoutManager: GridLayoutManager)
    : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = gridLayoutManager.childCount / 2 //2 colunas
        val totalItemCount = gridLayoutManager.itemCount / 2
        val firstVisiblePosition = gridLayoutManager.findFirstVisibleItemPosition()

        if (!isLoading() && !isLastPage()) {
            if ((visibleItemCount + firstVisiblePosition) >= totalItemCount
                    && firstVisiblePosition >= 0
                    && totalItemCount >= getTotalPageCount()) {

                loadMoreItems()
            }
        }

    }

    abstract fun loadMoreItems()
    abstract fun getTotalPageCount(): Int
    abstract fun isLoading(): Boolean
    abstract fun isLastPage(): Boolean
}