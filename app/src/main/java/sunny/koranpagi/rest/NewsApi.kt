package sunny.koranpagi.rest

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import sunny.koranpagi.utils.Constant
import java.util.concurrent.TimeUnit

/**
 * Created by Wayan-MECS on 11/26/2018.
 */
class NewsApi {

    private val gson = GsonBuilder().create()!!
    var retrofit: Retrofit = getClient()

    fun getClient(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        if (retrofit == null) {

            val ok = OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .build()

            retrofit = Retrofit.Builder()
                    .baseUrl(Constant.NEWS_API_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(ok)
                    .build()
        }
        return retrofit
    }

    val services: NewsServices = retrofit.create(NewsServices::class.java)
}