package sunny.koranpagi.feature.fragment.base

import android.content.Context
import android.view.View
import sunny.koranpagi.rest.NewsApi

/**
 * Created by Wayan-MECS on 11/26/2018.
 */
interface ContractBaseFragment {

    interface mainView {
        fun init(v: View)
        fun action()
        fun updateUI()
        fun showLoading()
        fun hideLoading()
    }

    interface mainPresent {
        fun getNews(api: NewsApi, ctx: Context, country: String, category: String)
//        fun getHiburanNews(api: NewsApi, ctx: Context, country: String, category: String)
//        fun getMusikNews(api: NewsApi, ctx: Context, country: String, category: String)
//        fun getTechNews(api: NewsApi, ctx: Context, country: String, category: String)
//        fun getSportNews(api: NewsApi, ctx: Context, country: String, category: String)
    }
}