package sunny.koranpagi.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.net.Uri
import android.support.customtabs.CustomTabsIntent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_item.view.*
import sunny.koranpagi.R
import sunny.koranpagi.entity.NewsSport
import sunny.koranpagi.feature.custome_chrome_tab.CustomTabActivityHelper
import sunny.koranpagi.feature.custome_chrome_tab.WebviewFallback

class NewsSportAdapter(val ctx: Context,val act: Activity, var newsList: List<NewsSport.Article>) : RecyclerView.Adapter<NewsSportAdapter.ViewHolder>() {

    inner class ViewHolder(itView: View) : RecyclerView.ViewHolder(itView) {
        var ImagesNews = itView.iv_news
        var NewsTitles = itView.news_title
        var NewsDate = itView.news_date
        var NewsDesc = itView.news_desc

        fun bind(news: NewsSport.Article) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.news_item, parent, false))
    }

    override fun getItemCount(): Int = newsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(newsList[position])
    }

    private fun goToChromeTabs(url: String) {
        val customTabsIntent = CustomTabsIntent.Builder().build()
        CustomTabActivityHelper.openCustomTab(act, // activity
                customTabsIntent,
                Uri.parse(url),
                WebviewFallback()
        )
    }
}