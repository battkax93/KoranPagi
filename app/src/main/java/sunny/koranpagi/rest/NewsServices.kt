package sunny.koranpagi.rest

import retrofit2.http.GET
import retrofit2.http.Query
import rx.Single
import sunny.koranpagi.entity.*
import sunny.koranpagi.utils.Constant

interface NewsServices {

    @GET(Constant.NEWS_API_KEYS_COUNTRY)
    fun getNewsGame(@Query("country") country: String,
                    @Query("category") category: String,
                    @Query("apiKey") apiKey: String): Single<NewsGames>

    @GET(Constant.NEWS_API_KEYS_COUNTRY)
    fun getNewsHiburan(@Query("country") country: String,
                       @Query("category") category: String,
                       @Query("apiKey") apiKey: String): Single<NewsHiburan>

    @GET(Constant.NEWS_API_KEYS_COUNTRY)
    fun getMusicNews(@Query("country") country: String,
                     @Query("category") category: String,
                     @Query("apiKey") apiKey: String): Single<NewsMusic>

    @GET(Constant.NEWS_API_KEYS_COUNTRY)
    fun geSportNews(@Query("country") country: String,
                    @Query("category") category: String,
                    @Query("apiKey") apiKey: String): Single<NewsSport>

    @GET(Constant.NEWS_API_KEYS_COUNTRY)
    fun getTechnoNews(@Query("country") country: String,
                      @Query("category") category: String,
                      @Query("apiKey") apiKey: String): Single<NewsTechno>
}