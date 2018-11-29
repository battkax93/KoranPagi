package sunny.koranpagi.feature.fragment.base

import android.content.Context
import org.greenrobot.eventbus.EventBus
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import sunny.koranpagi.feature.fragment.game_fragment.EntertainmentFragment
import sunny.koranpagi.feature.fragment.teknologi_fragment.TechnologyFragment
import sunny.koranpagi.rest.NewsApi
import sunny.koranpagi.utils.Constant
import sunny.koranpagi.utils.MessageEventHiburan
import sunny.koranpagi.utils.MessageEventTechno
import sunny.koranpagi.utils.RxBus
import android.R.id.edit
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences



class PresentBaseFragment : ContractBaseFragment.mainPresent {

    private val APIKEYS = Constant.NEWS_KEY

    override fun getNewsGames(api: NewsApi, ctx: Context, country: String, category: String, busEventKey: String) {
    }

    override fun getHiburanNews(api: NewsApi, country: String, category: String, busEventKey: String) {
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

    override fun getTechNews(api: NewsApi, country: String, category: String, busEventKey: String) {
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

    override fun getSportNews(api: NewsApi, country: String, category: String, busEventKey: String) {
        RxBus.publish("loading")
        api.services.getSportNews(country, category, APIKEYS)
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