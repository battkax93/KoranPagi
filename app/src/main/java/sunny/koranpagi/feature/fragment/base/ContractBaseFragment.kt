package sunny.koranpagi.feature.fragment.base

import android.content.Context
import android.view.View
import sunny.koranpagi.entity.NewsGames
import sunny.koranpagi.rest.NewsApi

/**
 * Created by Wayan-MECS on 11/26/2018.
 */
interface ContractBaseFragment {

    interface mainGameView {
        fun init(v: View)
        fun action()
        fun updateUI(it: NewsGames)
        fun showLoading()
        fun hideLoading()
    }

    interface mainHiburanView {
        fun init(v: View)
        fun action()
        fun updateUI()
        fun showLoading()
        fun hideLoading()
    }

    interface mainMusikView {
        fun init(v: View)
        fun action()
        fun updateUI()
        fun showLoading()
        fun hideLoading()
    }

    interface mainOlahragaView {
        fun init(v: View)
        fun action()
        fun updateUI()
        fun showLoading()
        fun hideLoading()
    }

    interface mainTeknologiView {
        fun init(v: View)
        fun action()
        fun updateUI()
        fun showLoading()
        fun hideLoading()
    }

    interface mainPresent {
        fun getNewsGames(api: NewsApi, ctx: Context, country: String, category: String)
        fun getHiburanNews(api: NewsApi, ctx: Context, country: String, category: String)
        fun getMusikNews(api: NewsApi, ctx: Context, country: String, category: String)
        fun getTechNews(api: NewsApi, ctx: Context, country: String, category: String)
        fun getSportNews(api: NewsApi, ctx: Context, country: String, category: String)
    }
}