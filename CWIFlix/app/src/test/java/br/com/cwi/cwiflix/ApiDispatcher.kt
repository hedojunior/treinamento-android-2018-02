package br.com.cwi.cwiflix

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

/**
 * @author hedo
 */
class ApiDispatcher : Dispatcher() {

    override fun dispatch(request: RecordedRequest?): MockResponse {
        val response = MockResponse()

        when (request?.path) {
            "/movie/popular?page=1&api_key=0f1f3f41b3e4b74c25fe20141a3480cc&language=pt-BR" -> {
                response.setResponseCode(200)
                response.setBody("{\"page\":1,\"total_results\":19816,\"total_pages\":991,\"results\":[{\"vote_count\":1804,\"id\":348350,\"video\":false,\"vote_average\":6.7,\"title\":\"Han Solo: Uma História Star Wars\",\"popularity\":214.308,\"poster_path\":\"\\/lJChjsNR7O1waqsOIqlkePoGtZJ.jpg\",\"original_language\":\"en\",\"original_title\":\"Solo: A Star Wars Story\",\"genre_ids\":[28,12,878],\"backdrop_path\":\"\\/96B1qMN9RxrAFu6uikwFhQ6N6J9.jpg\",\"adult\":false,\"overview\":\"Embarque na Millennium Falcon e viaje para uma galáxia distante em uma nova aventura com o mais amado canalha da galáxia. Através de uma série de ousadas aventuras no obscuro e perigoso submundo do crime, Han Solo encontra seu poderoso futuro copiloto Chewbacca e encontra o famoso jogador Lando Calrissian, em uma jornada que definirá o curso de um dos heróis mais improváveis da saga Star Wars.\",\"release_date\":\"2018-05-15\"}]}")
            }

            else -> {
                response.setResponseCode(404)
            }
        }

        return response
    }
}










