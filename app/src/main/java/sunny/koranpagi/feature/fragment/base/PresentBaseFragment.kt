package sunny.koranpagi.feature.fragment.base

import android.content.Context
import org.greenrobot.eventbus.EventBus
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import sunny.koranpagi.rest.NewsApi
import sunny.koranpagi.utils.Constant
import sunny.koranpagi.utils.RxBus

class PresentBaseFragment : ContractBaseFragment.mainPresent {

    private val APIKEYS = Constant.NEWS_KEY

    override fun getNewsGames(api: NewsApi, ctx: Context, country: String, category: String, busEventKey: String) {
    }

    override fun getHiburanNews(api: NewsApi, ctx: Context, country: String, category: String, busEventKey: String) {
        RxBus.publish("loading")
        api.services.getNewsHiburan(country, category, APIKEYS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    RxBus.publish(busEventKey)
                    RxBus.publish(it)
                }, {
                    it.printStackTrace()
                })
    }

    override fun getMusikNews(api: NewsApi, ctx: Context, country: String, category: String, busEventKey: String) {
    }

    override fun getTechNews(api: NewsApi, ctx: Context, country: String, category: String, busEventKey: String) {
        RxBus.publish("loading")
        api.services.getTechnoNews(country, category, APIKEYS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    RxBus.publish(busEventKey)
                    RxBus.publish(it)
                }, {
                    it.printStackTrace()
                })
    }

    override fun getSportNews(api: NewsApi, ctx: Context, country: String, category: String, busEventKey: String) {
    }

}