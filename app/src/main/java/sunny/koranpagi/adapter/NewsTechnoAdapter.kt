package sunny.koranpagi.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_item.view.*
import sunny.koranpagi.R
import sunny.koranpagi.entity.NewsHiburan
import sunny.koranpagi.entity.NewsTechno

/**
 * Created by Wayan-MECS on 11/27/2018.
 */
class NewsTechnoAdapter(private val ctx: Context, private var newsList: List<NewsTechno.Article>) : RecyclerView.Adapter<NewsTechnoAdapter.ViewHolder>() {

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
                //                sendData(news.urlToImage, news.description, news.url, news.title)
                Log.d("FLOW", "cv clicked")
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun convertDate(s: String): String {
        var sdf = s.substring(0,9).split("-")
        return sdf[2] + "/" + sdf[1] + "/" + sdf[0]
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