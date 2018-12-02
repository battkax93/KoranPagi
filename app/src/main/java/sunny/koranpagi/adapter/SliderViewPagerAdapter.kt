package sunny.koranpagi.adapter

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.support.customtabs.CustomTabsIntent
import android.support.v4.view.PagerAdapter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import com.squareup.picasso.Picasso
import sunny.koranpagi.R
import sunny.koranpagi.entity.NewsHiburan
import sunny.koranpagi.entity.NewsSport
import sunny.koranpagi.entity.NewsTechno
import sunny.koranpagi.feature.custome_chrome_tab.CustomTabActivityHelper
import sunny.koranpagi.feature.custome_chrome_tab.WebviewFallback
import sunny.koranpagi.utils.RxBus

class SliderViewPagerAdapter(val ctx: Context, val act: Activity) : PagerAdapter() {

    private var isMultiScr: Boolean? = null

    lateinit var urlImage1: String
    lateinit var urlImage2: String
    lateinit var urlImage3: String
    lateinit var urlImage4: String
    lateinit var urlImage5: String
    lateinit var url1: String
    lateinit var url2: String
    lateinit var url3: String

    fun ViewPagerAdapter(isMultiScr: Boolean) {
        this.isMultiScr = isMultiScr
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return 3
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val frameLayout: FrameLayout = LayoutInflater.from(container.context).inflate(R.layout.layout_child, null) as FrameLayout
        try {
            FrameLayout(container.context)

            listen()
            val imgView: ImageView = frameLayout.findViewById(R.id.iv_slider_news)
            imgView.setOnClickListener { Log.d("FLOW","clicked at $position")
                when (position) {
                    0 -> goToChromeTabs(url1)
                    1 -> goToChromeTabs(url2)
                    2 -> goToChromeTabs(url3)
                }
            }
            when (position) {
                0 -> Picasso.get().load(urlImage1).into(imgView)
                1 -> Picasso.get().load(urlImage2).into(imgView)
                2 -> Picasso.get().load(urlImage3).into(imgView)
            }
            container.addView(frameLayout)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return frameLayout
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as FrameLayout
        container.removeView(view)
    }

    fun loadUrl(s: String, ctx: Context): String {
        val prefs = ctx.getSharedPreferences(ctx.packageName, 0)
        return prefs.getString(s, "")
    }

    fun listen() {
        RxBus.listen(NewsHiburan::class.java).subscribe({
            url1 = it.articles[0].url
            urlImage1 = it.articles[2].urlToImage
        })

        RxBus.listen(NewsTechno::class.java).subscribe({
            url2 = it.articles[0].url
            urlImage2 = it.articles[2].urlToImage
        })

        RxBus.listen(NewsSport::class.java).subscribe({
            url3 = it.articles[0].url
            urlImage3 = it.articles[2].urlToImage
        })
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