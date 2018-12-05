package sunny.koranpagi.feature.base

import android.content.Context
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import sunny.koranpagi.rest.NewsApi
import sunny.koranpagi.utils.RxBus


class PresentBaseFragment : ContractBaseFragment.mainPresent {

    override fun getNewsGames(api: NewsApi, ctx: Context, country: String, category: String, busEventKey: String) {
    }

    override fun getHiburanNews(api: NewsApi, country: String, category: String, busEventKey: String) {
        RxBus.publish("loading")
        api.services.getNewsHiburan(country, category)
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

    override fun getTechNews(api: NewsApi, country: String, category: String, busEventKey: String) {
        RxBus.publish("loading")
        api.services.getTechnoNews(country, category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    RxBus.publish(busEventKey)
                    RxBus.publish(it)
                }, {
                    it.printStackTrace()
                })

    }

    override fun getSportNews(api: NewsApi, country: String, category: String, busEventKey: String) {
        RxBus.publish("loading")
        api.services.getSportNews(country, category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    RxBus.publish(busEventKey)
                    RxBus.publish(it)
                }, {
                    it.printStackTrace()
                })
    }

}