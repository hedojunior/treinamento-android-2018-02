package br.com.cwi.cwiflix

import br.com.cwi.cwiflix.services.api.MovieDatabaseService
import junit.framework.Assert.*
import okhttp3.mockwebserver.MockWebServer
import org.junit.*

/**
 * @author hedo
 */
class MovieDatabaseServiceTest {

    companion object {
        private lateinit var server: MockWebServer

        @BeforeClass
        @JvmStatic
        fun beforeClass() {
            server = MockWebServer()
            server.setDispatcher(ApiDispatcher())
            server.start()

            MovieDatabaseService.setBaseUrl(server.url("").toString())
        }

        @AfterClass
        @JvmStatic
        fun afterClass() {
            server.shutdown()
        }
    }

    @Test
    fun createService_succeeds() {
        assertNotNull(MovieDatabaseService.service)
    }

    @Test
    fun getPopularMovies_andIsFirstPage_succeeds() {
        val result = MovieDatabaseService.service.getPopularMovies(1).execute()

        assertNotNull(result)
        assertTrue(result.isSuccessful)
        assertEquals(348350, result.body()?.results?.first()?.id)
    }
}
























