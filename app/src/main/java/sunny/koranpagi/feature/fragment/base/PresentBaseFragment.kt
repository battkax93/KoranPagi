package sunny.koranpagi.feature.fragment.base

import android.content.Context
import sunny.koranpagi.rest.NewsApi

/**
 * Created by Wayan-MECS on 11/26/2018.
 */
class PresentBaseFragment (val mView: ContractBaseFragment.mainView): ContractBaseFragment.mainPresent {

    override fun getNews(api: NewsApi, ctx: Context, country: String, category: String) {

    }

//    override fun getHiburanNews(api: NewsApi, ctx: Context, country: String, category: String) {
//    }
//
//    override fun getMusikNews(api: NewsApi, ctx: Context, country: String, category: String) {
//    }
//
//    override fun getTechNews(api: NewsApi, ctx: Context, country: String, category: String) {
//    }
//
//    override fun getSportNews(api: NewsApi, ctx: Context, country: String, category: String) {
//    }

}