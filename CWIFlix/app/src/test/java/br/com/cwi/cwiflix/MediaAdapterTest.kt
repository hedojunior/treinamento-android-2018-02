package br.com.cwi.cwiflix

import android.content.Context
import android.widget.ImageButton
import android.widget.RelativeLayout
import br.com.cwi.cwiflix.adapters.MediaAdapter
import br.com.cwi.cwiflix.services.api.models.Media
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import java.util.*

/**
 * @author hedo
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [23])
class MediaAdapterTest {

    private lateinit var context: Context
    private lateinit var target: MediaAdapter
    private lateinit var onClick: ((media: Media) -> Unit)

    @Before
    fun setUp() {
        context = RuntimeEnvironment.application as Context

        val list = ArrayList<Media>()

        list.add(getMedia(0))
        list.add(getMedia(1))
        list.add(getMedia(2))

        onClick = {
            assertEquals(list[0], it)
        }

        target = MediaAdapter(list, onClick)
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

        assertTrue(viewHolder.posterImageButton.hasOnClickListeners())
        assertTrue(viewHolder.posterImageButton.callOnClick())
    }

    @Test
    fun addItems_withTwoItems_succeeds() {
        val item = getMedia(3)
        val secondItem = getMedia(4)

        assertEquals(3, target.itemCount)
        target.addItems(ArrayList(Arrays.asList(item, secondItem)))
        assertEquals(5, target.itemCount)
    }


    private fun getMedia(position: Int): Media {
        return Media(position,
                "title$position",
                "12-02-2002",
                6.9,
                "imagem$position",
                "overview$position")
    }

}