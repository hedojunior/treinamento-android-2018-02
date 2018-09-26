package br.com.cwi.cwiflix

import android.content.Context
import android.widget.ImageButton
import android.widget.RelativeLayout
import br.com.cwi.cwiflix.adapters.FavoriteAdapter
import br.com.cwi.cwiflix.services.api.models.MediaType
import br.com.cwi.cwiflix.services.models.Favorite
import junit.framework.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

/**
 * @author hedo
 */

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [23])
class FavoriteAdapterTest {

    private lateinit var context: Context
    private lateinit var target: FavoriteAdapter

    @Before
    fun setUp() {
        context = RuntimeEnvironment.application

        val list = ArrayList<Favorite>()

        list.add(getFavorite(0))
        list.add(getFavorite(1))
        list.add(getFavorite(2))

        target = FavoriteAdapter(list)
    }

    @Test
    fun onCreateViewHolder_succeeds() {
        val viewHolder = target.onCreateViewHolder(RelativeLayout(context), 0)

        assertNotNull(viewHolder)
        assertNotNull(viewHolder.posterImageButton)
        assertTrue(viewHolder.posterImageButton is ImageButton)

    }

    @Test
    fun getItemCount_isCorrect() {
        assertEquals(3, target.itemCount)
    }

    @Test
    fun onBindViewHolder_succeeds() {
        val viewHolder = target.onCreateViewHolder(RelativeLayout(context), 0)
        target.onBindViewHolder(viewHolder, 0)
    }

    private fun getFavorite(position: Int) = Favorite(position, "image$position", MediaType.MOVIE)


}







