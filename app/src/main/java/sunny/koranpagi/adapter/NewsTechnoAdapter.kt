package sunny.koranpagi.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.net.Uri
import android.support.customtabs.CustomTabsIntent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_item.view.*
import sunny.koranpagi.R
import sunny.koranpagi.entity.NewsTechno
import sunny.koranpagi.feature.custome_chrome_tab.CustomTabActivityHelper
import sunny.koranpagi.feature.custome_chrome_tab.WebviewFallback

class NewsTechnoAdapter(val ctx: Context,val act: Activity, var newsList: List<NewsTechno.Article>) : RecyclerView.Adapter<NewsTechnoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.news_item, p0, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, p1: Int) {
        holder.bind(newsList[p1])
    }

    override fun getItemCount(): Int = newsList.size

    inner class ViewHolder(itView: View) : RecyclerView.ViewHolder(itView) {

        var ImagesNews = itView.iv_news
        var NewsTitles = itView.news_title
        var NewsDate = itView.news_date
        var NewsDesc = itView.news_desc

        fun bind(news: NewsTechno.Article) {
            Picasso.get().load(news.urlToImage).into(ImagesNews)
            NewsTitles.text = news.title
            NewsDate.text = convertDate(news.publishedAt)
            NewsDesc.text = news.description

            itemView.setOnClickListener {
                goToChromeTabs(news.url)
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun convertDate(s: String): String {
        var sdf = s.substring(0, 9).split("-")
        return sdf[2] + "/" + sdf[1] + "/" + sdf[0]
    }

    private fun goToChromeTabs(url: String) {
        val customTabsIntent = CustomTabsIntent.Builder().build()
        CustomTabActivityHelper.openCustomTab(act, // activity
                customTabsIntent,
                Uri.parse(url),
                WebviewFallback()
        )
    }

    fun sendData(urlNewsImage: String, newsDesc: String, sourceNews: String, titleNews: String) {
        Log.d("Flow", "adapter.SendData")

//        val bundle = Bundle()
//        val i = Intent(ctx, NewsDetailActivity::class.java)
//
//        bundle.putString("ivPic", urlNewsImage)
//        bundle.putString("newsDesc", newsDesc)
//        bundle.putString("sourceNews", sourceNews)
//        bundle.putString("titleNews", titleNews)
//
//        i.putExtras(bundle)
//        ContextCompat.startActivity(ctx, i, bundle)
    }
}