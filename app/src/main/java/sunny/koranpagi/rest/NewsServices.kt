package sunny.koranpagi.rest

import retrofit2.http.GET
import retrofit2.http.Query
import rx.Single
import sunny.koranpagi.entity.news
import sunny.koranpagi.utils.Constant

/**
 * Created by Wayan-MECS on 11/26/2018.
 */
interface NewsServices {
    @GET(Constant.NEWS_API_KEYS_COUNTRY)
    fun getNews(@Query("country") country: String,
                @Query("category") category: String,
                @Query("apiKey") apiKey: String): Single<news>
}