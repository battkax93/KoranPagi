package sunny.koranpagi.feature.fragment.base

import android.content.Context
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import sunny.koranpagi.rest.NewsApi
import sunny.koranpagi.utils.Constant

class PresentBaseFragment(val mViewGame: ContractBaseFragment.mainGameView) : ContractBaseFragment.mainPresent {

    private val API_KEYS = Constant.NEWS_KEY

    override fun getNewsGames(api: NewsApi, ctx: Context, country: String, category: String) {
        api.services.getNewsGame(country, category, API_KEYS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                })
    }

    override fun getHiburanNews(api: NewsApi, ctx: Context, country: String, category: String) {
    }

    override fun getMusikNews(api: NewsApi, ctx: Context, country: String, category: String) {
    }

    override fun getTechNews(api: NewsApi, ctx: Context, country: String, category: String) {
    }

    override fun getSportNews(api: NewsApi, ctx: Context, country: String, category: String) {
    }

}